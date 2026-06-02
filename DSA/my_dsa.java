package DSA;
import java.util.*;

public class my_dsa {

    // public static void main(String[] args) {
    // List<Integer> list = new ArrayList<>();
    // list.add(1);
    // list.add(2);
    // list.add(3);
    // list.add(4);
    // int t = 5;
    // for(int i=0;i<=list.size();i++){
    // for(int j=1;j<=list.size();j++){
    // if(t == list.get(i) + list.get(j)){
    // System.out.println(list.get(i));
    // System.out.println(list.get(j));
    // }
    // }
    // }
    // }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement))
                return new int[] { map.get(complement), i };
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    
        public static ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;

            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    
        public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }


    public static void maxcSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {

            // Decide whether to start new subarray
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum = currentSum + nums[i];
            }

            // Update max
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        System.out.println("Max Sum: " + maxSum);
        System.out.print("Subarray: ");
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i] + " ");
        }
    }


    public static void duplicateA(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : arr) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }

        System.out.println(duplicates);
    }

  
    public static int longCon(int[] arr) {
        if (arr.length == 0) return 0;

        Arrays.sort(arr);

        int maxLen = 1;
        int currentLen = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == arr[i - 1]) {
                continue; // skip duplicates
            }

            if (arr[i] == arr[i - 1] + 1) {
                currentLen++;
            } else {
                currentLen = 1;
            }

            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }

    public static String revWords(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            char[] arr = word.toCharArray();
            int left = 0, right = arr.length - 1;

            // reverse each word
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }

            result.append(new String(arr)).append(" ");
        }

        return result.toString().trim();
    }

    public static String reWord(String s){
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
            int len = ss.length -1;
            int first = 0;
            while(first<len){
                sb.append(ss[len]);
                len--;
                first ++;
            }
        
        return sb.toString();
    }



    public static void remDup(int[] a){
        Set<Integer> s = new HashSet<>();
        Set<Integer> d = new HashSet<>();
        for(int i=0; i<a.length;i++){
if(!s.add(a[i])){
    d.add(a[i]);
        }
        
        }
        System.out.println( s.toArray());

    }

    public static void countEachVowel(String str){

        HashMap<Character,Integer> res = new HashMap<>();
        HashSet<Character> vow = new HashSet<>(
            Arrays.asList('a','e','i','o','u')
        );
        for(char c : str.trim().toCharArray()){
            if(vow.contains(c)){
                res.put(c, res.getOrDefault(c, 0)+1);
            }
        }
        str.trim().toLowerCase().chars();
        System.out.println(res.toString());
    }
    public static void main(String[] args) {
        // test t = new test();
        // int[] i = { 4, 1, 2, 3, 3, 4, 5 };

        // int[] numb = t.twoSum(i, 7);
        // for (int num : numb) {
        //     System.out.println(i[num]);
        // }

        // System.out.println(t.findDuplicate(i));


        // String s = "abcefeejjbce";
        // System.out.println(lengthOfLongestSubstring(s));

        // int[] j = {-2, -3, 4, -1, -2, 1, 5, -3};
        // System.out.println(maxSubArray(j));
        // maxcSubArray(j);

        int[] k = {12,1,4,5,8,1};
        longCon(k);
        
        countEachVowel("antbbccee ant");
    }


}

