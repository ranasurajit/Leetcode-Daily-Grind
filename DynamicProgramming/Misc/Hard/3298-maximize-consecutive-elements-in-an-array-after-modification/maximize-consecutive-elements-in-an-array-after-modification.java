class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int maxValue = nums[nums.length - 1];
        int[] dp = new int[maxValue + 2];
        int answer = 0;

        for (int num : nums) {
            // Save the old state before updating
            int previous = dp[num];

            // Option 1: Use num without modification
            dp[num] = Math.max(dp[num], dp[num - 1] + 1);

            // Option 2: Increase num by 1
            dp[num + 1] = Math.max(dp[num + 1], previous + 1);

            answer = Math.max(answer, Math.max(dp[num], dp[num + 1]));
        }

        return answer;
    }
}
