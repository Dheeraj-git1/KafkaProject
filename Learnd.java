import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Stack;

public class Learnd {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if (hm.containsKey(res)) {
                return new int[] { hm.get(res), i };
            }
            hm.put(nums[i], i);

        }
        return new int[] {};
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // closing brackets
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);

            // if (price < minPrice) {
            // minPrice = price;
            // }
            // int profit = price - minPrice;
            // if (profit > maxProfit) {
            // maxProfit = profit;
            // }
        }
        return maxProfit;
    }

    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .count() != nums.length;
    }

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    public boolean isAnagramHash(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char c : s.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        return map1.equals(map2);
    }

    public boolean isAnagramhashcompare(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }

            map.put(c, map.get(c) - 1);

            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        return map.isEmpty();
    }

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public int[] twoSumSorted(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[] { left, right };
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[] {};
    }

    public void moveZeroes(int[] nums) {

        int insertPos = 0;

        // move all non-zero elements forward
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        // fill remaining with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public int maxProfitII(int[] prices) {

        int profit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    public int[] productExceptSelf(int[] nums) {

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            res[i] = product;
        }
        return res;
    }

    public int[] buildLeft(int[] nums) {
        int initial = 1;
        int[] res = new int[nums.length];
        res[0] = initial;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];

        }
        return res;
    }

    public int[] buildRight(int[] nums) {
        int end = 1;
        int[] res = new int[nums.length];
        res[nums.length] = end;
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i] * nums[i];

        }
        return res;
    }

    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;

        while (left < right) {

            int area = Math.min(height[left], height[right])
                    * (right - left);

            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                result.add(
                    Arrays.asList(nums[i],nums[left],nums[right])
                );
                left++;
                right--;
                while (left < right &&
                       nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right &&
                       nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
    }
    return result;
}

    public static void main(String args[]) {
        int[] li = new int[] { 1, 7, 9, 3, 2 };
        int target = 5;
        Learnd l = new Learnd();
        int[] a = l.twoSum(li, target);
        System.out.println(a[0] + " " + a[1]);

        String s = "[()]";
        System.out.println(l.isValid(s));

        int[] prices = new int[] { 1, 4, 3, 7, 2, 1, 6, 20 };
        System.out.println(l.maxProfit(prices));

        String[] ss = { "sweje", "swesw", "sweded" };
        System.out.println(l.longestCommonPrefix(ss));

        System.out.println(l.maxProfitII(prices));
    }
}
