package com.txb.tree.segment.test;

public class Leetcode_303 {

    class NumArray {

        private int[] sum;//里面存的是数组中前n项的和

        //sum[0]=0
        //sum[1]存入的是前1项的和，即sum[1]=nums[1]
        //sum[2]存入的是前2项的和即 sum[2]=nums[0]+nums[1]
        //sum[i]存入的是前i项的和，即sum[i]=nums[0]+nums[1]+...+nums[i-1]
        //所以，sum 的长度是nums.lenght+1
        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            sum[0] = 0;

            for (int i = 1; i < sum.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }

        }

        //区间[i..j]直接的和就是前j+1项的和减去前i项的和
        public int sumRange(int i, int j) {
            return sum[j+1]-sum[i];
        }
    }
}
