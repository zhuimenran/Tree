package com.txb.trie.test;

import java.util.TreeMap;
/**
 * 使用insert和sum方法实现MapSum类。

对于该方法insert，您将获得一对（字符串，整数）。字符串表示键，整数表示值。如果密钥已存在，则原始键值对将被覆盖到新键值对。

对于该方法sum，您将获得一个表示前缀的字符串，并且您需要返回其键以前缀开头的所有对的值的总和。

例1：
输入：insert（“apple”，3），输出：Null
输入：sum（“ap”），输出：3
输入：insert（“app”，2），Output：Null
输入：sum（“ap”），输出：5
 * @author 13125
 *
 */
public class Leetcode_677 {
    public static class MapSum {

        private class Node {
            //使用这个保存值，如果值为0，就说明这个不是一个前缀/单词
            int value;
            //每一个节点有若干个指向下个节点的指针，next就是字符和Node的映射
            TreeMap<Character, Node> next;

            public Node(int isWord) {
                this.value = isWord;
                next = new TreeMap<>();
            }

            public Node() {
                this(0);
            }
        }

        private Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int value) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                //如果不包含该字符对应的节点，就添加进去
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                //指向下一个节点
                cur = cur.next.get(c);
            }
            cur.value = value;//把添加上的值存起来
        }

        //和添加很相似，都需要遍历，不同的是，如果遍历到节点不包括某个字符，直接返回false即可，
        public int sum(String prefix) {
            //遍历整个节点，得到前缀
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null) {//说明不包含这个前缀
                    return 0;
                }
                cur = cur.next.get(c);
            }//到此cur 就是指向的word最后一个字符的节点，

            //查找当前这个节点的所有子节点，只要是单词的，就需要求和
            return sum(cur);
        }

        private int sum(Node node) {
            int res = node.value;
            //循环遍历cur 的所有节点，如果子节点有value值，，加起来。
            for (Character c : node.next.keySet()) {
                //递归求得以node子节点为根节点的值
                res += sum(node.next.get(c));//
            }
            return res;
        }
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("apple", 3);
        System.out.println(obj.sum("ap"));
        obj.insert("app", 2);
        System.out.println(obj.sum("ap"));


    }
}
