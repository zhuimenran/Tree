package com.txb.trie.test;

import java.util.TreeMap;

public class Leetcode_211 {
    class WordDictionary {

        private class Node {
            //当前这个节点，是否是某一个单词
            boolean isWord;

            //每一个节点有若干个指向下个节点的指针，next就是字符和Node的映射
            TreeMap<Character, Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        public WordDictionary() {
            root = new Node();
        }


        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                //如果不包含该字符对应的节点，就添加进去
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                //指向下一个节点
                cur = cur.next.get(c);
            }
            //遍历完，也就添加上去了，查看当节点是否是单词，如果不是，标记为true，
            // 并且size++,否则，说明添加了一个重复的，不需要添加
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }

        //和添加很相似，都需要遍历，不同的是，如果遍历到节点不包括某个字符，直接返回false即可，
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node root, String word, int index) {
            if (index == word.length()) {//说明找到了这样的字符串，然后返回这个节点是否是单词
                return root.isWord;
            }
            char c = word.charAt(index);//匹配的字符
            if (c != '.') {//不是通配符的情况下,
                //就看这个字符是啥，是否匹配，
                if (root.next.get(c) == null) {//没有找到字符，直接返回false
                    return false;
                } else {
                    //匹配下一个节点
                    return match(root.next.get(c), word, index + 1);
                }
            } else {//对于root的下一个字符都可以匹配这个点
                //循环遍历这个节点下面所有的节点，看是否有一个能匹配成功的，
                for (Character nextChar : root.next.keySet()) {
                    if (match(root.next.get(nextChar), word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
