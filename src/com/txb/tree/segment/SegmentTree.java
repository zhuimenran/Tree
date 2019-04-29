package com.txb.tree.segment;

/**
 * 线段树 使用数组实现，数组的长度是传递过来线段数组长度的4倍
 */

public class SegmentTree<E> {

    //线段树区间
    private E[] data;
    //用数组表示线段树
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 在treeIndex的位置，创建表示区间[l...r]的线段树
     *
     * @param treeIndex 要创建线段树的根节点所对应的索引
     * @param l         创建线段的左区间
     * @param r         创建线段的右区间
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //递归的终止条件，当区间中只有一个元素的时候
        if (l == r) {//说明区间中只有一个节点，那么直接创建即可
            tree[treeIndex] = data[l];
            return;
        }
        
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        //计算左节点的区间，其实就是left到right中间的位置
        //不直接写成（l+r）/2，是因为如果left 和right都很大的时候，如果大于int 的最大值，那么就内存溢出了
        int mid = l + (r - l) / 2;

        //再循环递归调用创建左右子节点的线段树
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1 , r);//记得右区间是从mid+1开始

        //把得到的左右节点融合到一起就是父节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is Illegal");
        }
        return data[index];
    }

    //返回完全二叉树的数组表示形式中，一个索引所表示的元素的左孩子节点的索引。
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树的数组表示形式中，一个索引所表示的元素的右孩子节点的索引。
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL > data.length || queryR < 0 || queryR > data.length || queryR < queryL) {
            throw new IllegalArgumentException("argument is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);

    }

    //在以treeIndex 为根节点的线段树中[l...r]区间内，搜索区间[query...queryR] 的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //递归终止条件
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        //得到线段树区间中间索引
        int mid = l + (r - l) / 2;

        //得到节点的左右子节点
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryR < mid) {//说明查找的区间在左节点内
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else if (queryL >= mid ) {//查找的区间在右节点内
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        } else {//查找的区间左右区间都有
            //先查找左区间部分，查找的范围就是[queryl...mid]
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);

            //查找右区间部分,查找范围就是【mid+1,queryR】
            E rightResult = query(rightTreeIndex, mid+1 , r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    public void set(int index, E e) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("index is Illega");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    //在以treeIndex为根节点的线段树区间[l...r]中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index > mid) {//说明更新的index在右节点
            set(rightTreeIndex, mid+1 , r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }
        //更新完index对应的值，那么index的所有父节点的值也需要更新，
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("[ ");

        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i] + " ");
            } else {
                res.append("null ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
