package com.txb.tree.segment.test;

import com.txb.tree.segment.SegmentTree;
import com.txb.tree.segment.*;
public class Leetcode_307 {

    class NumArray {

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                Integer[] data = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    data[i] = nums[i];
                }

                segmentTree = new SegmentTree<>(data, new Merger<Integer>() {
                    @Override
                    public Integer merge(Integer a, Integer b) {
                        return a + b;
                    }
                });
            }

        }

        public void update(int i, int val) {
            if (segmentTree == null) {
                throw new NullPointerException("Segment tree is null");
            }
            segmentTree.set(i, val);
        }

        public int sumRange(int i, int j) {
            if (segmentTree == null) {
                throw new NullPointerException("Segment tree is null");
            }
            return segmentTree.query(i, j);
        }
    }
}
