package DSA;
import java.util.*;

public class dfs_node {

    static List<Integer>[] graph;
    static int[] val;
    static boolean[] vis;
    static int totalSum = 0;
    static int minDiff = Integer.MAX_VALUE;

    static int dfs(int node) {
        vis[node] = true;

        int sum = val[node];

        for (int nei : graph[node]) {
            if (!vis[nei]) {
                int sub = dfs(nei);

                // treat this edge cut
                int diff = Math.abs(totalSum - 2 * sub);
                minDiff = Math.min(minDiff, diff);

                sum += sub;
            }
        }

        return sum;
    }

    public static int minDifference(int[] arr, int[][] edges) {
        int n = arr.length;

        graph = new ArrayList[n + 1];
        val = new int[n + 1];
        vis = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            val[i] = arr[i - 1];
            totalSum += val[i];
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        dfs(1);

        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30};

        int[][] edges = {
            {1, 2},
            {2, 3},
            {3, 4}
        };

        System.out.println(minDifference(arr, edges)); // 10
    }
}