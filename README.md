Learning about kafka
version: "3.8"

services:
  kafka-1:
    image: apache/kafka:4.1.1
    ports:
      - "9092:9092"
      - "9091:9091"
    environment:
      KAFKA_KRAFT_MODE: "true"
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: "broker,controller"
      KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka-1:9091"
      KAFKA_LISTENERS: "PLAINTEXT://:9092,CONTROLLER://:9091"
      KAFKA_ADVERTISED_LISTENERS: "PLAINTEXT://localhost:9092"
      KAFKA_CONTROLLER_LISTENER_NAMES: "CONTROLLER"
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: "CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT"
      KAFKA_LOG_DIRS: "/kafka-logs"
    volumes:
      - ./volumes/server-1:/kafka-logs

Great, this is a clean single-node Kafka (KRaft mode) setup 👍
I’ll explain every line, every port, and how traffic flows, step by step.

1️⃣ Compose file version
version: "3.8"


Docker/Podman Compose file format

3.8 is fully supported by podman-compose

2️⃣ Service definition
services:
  kafka-1:


Service name = kafka-1

Inside the Podman network, this name becomes a DNS hostname

kafka-1

3️⃣ Image
image: apache/kafka:4.1.1


Official Apache Kafka image

Kafka 4.1.1

Uses KRaft mode (no ZooKeeper)

4️⃣ Ports (VERY IMPORTANT)
ports:
  - "9092:9092"
  - "9091:9091"

🔹 Syntax
HOST_PORT : CONTAINER_PORT

🔸 Port 9092 → CLIENT/BROKER PORT
- "9092:9092"


Used by:

Producers

Consumers

kafka-topics.sh

Any external client

Example:

localhost:9092

🔸 Port 9091 → CONTROLLER PORT
- "9091:9091"


Used by:

KRaft controller

Metadata quorum

NOT for clients

🧠 Even in single-node mode, Kafka requires a controller listener.

5️⃣ KRaft mode switch
KAFKA_KRAFT_MODE: "true"


Enables KRaft mode

No ZooKeeper

Metadata stored in Kafka logs

6️⃣ Node ID
KAFKA_NODE_ID: 1


Unique ID of this Kafka node

Required for KRaft

In multi-node clusters: 1, 2, 3…

7️⃣ Process roles
KAFKA_PROCESS_ROLES: "broker,controller"


This node runs:

🧱 Broker → stores topics, serves clients

🧠 Controller → manages metadata

Single-node cluster = both roles on same node

8️⃣ Controller quorum voters
KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka-1:9091"

Format
nodeId@hostname:controllerPort


Meaning:

Node 1

Hostname kafka-1 (service name)

Port 9091

🧠 This is internal Kafka traffic, not external.

9️⃣ Listeners
KAFKA_LISTENERS: "PLAINTEXT://:9092,CONTROLLER://:9091"

This defines:

“On which ports Kafka listens”

Listener	Purpose	Port
PLAINTEXT	Client + inter-broker	9092
CONTROLLER	KRaft controller	9091

: means bind to all interfaces inside container

🔟 Advertised listeners (CRITICAL)
KAFKA_ADVERTISED_LISTENERS: "PLAINTEXT://localhost:9092"


This tells clients:

“Connect to Kafka using THIS address”

Why this matters

Kafka does not auto-detect host networking.

If wrong → clients fail with DNS errors.

When to use what
Client location	Value
Host machine	localhost:9092
Another container	kafka-1:9092
Another VM	VM IP
1️⃣1️⃣ Controller listener name
KAFKA_CONTROLLER_LISTENER_NAMES: "CONTROLLER"


Tells Kafka:

Which listener handles controller traffic

Required in KRaft mode

1️⃣2️⃣ Security protocol map
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: "CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT"


Maps:

listener-name : security-type


Both listeners:

No TLS

No SASL

Plain TCP

1️⃣3️⃣ Log directories (TOPICS LIVE HERE)
KAFKA_LOG_DIRS: "/kafka-logs"


Kafka stores:

Topics

Partitions

Offsets

Metadata

📦 This is THE MOST IMPORTANT PATH

1️⃣4️⃣ Volume (PERSISTENCE)
volumes:
  - ./volumes/server-1:/kafka-logs

Meaning
Host folder           → Container folder
./volumes/server-1    → /kafka-logs

Result

Topics survive restarts

Topics survive down / up

Topics survive hostname changes

❌ Deleted only if you delete ./volumes/server-1

🔄 Full traffic flow (mental model)
Your Laptop
   |
   | kafka-topics.sh --bootstrap-server localhost:9092
   |
Podman Port Mapping
   |
Container kafka-1
   |
PLAINTEXT listener (9092)
   |
Kafka Broker
   |
Logs stored in ./volumes/server-1

✅ Final summary
Component	Purpose
9092	Client traffic
9091	Controller (KRaft)
advertised.listeners	How clients find Kafka
listeners	Where Kafka listens
volumes	Where topics live
service name	Internal DNS