package com.txb.trie.util;

import java.util.TreeMap;

/**
 * Trie,字典树
 * @author 13125
 *@time 2019.5.6
 */
public class Trie {

    private class Node {
        //当前这个节点，是否是某一个单词
        boolean isWord;

        //每一个节点有若干个指向下个节点的指针，next就是字符和Node的映射
        TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
       //构造函数
        public Node() {
            this(false);
        }
    }

    //成员变量
    private Node root;
    private int size;

    //构造函数
    public Trie() {
        root = new Node();
        size = 0;
    }

    //得到数量
    public int getSize() {
        return size;
    }

    //向Trie中添加一个单词
    public void add(String word) {
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
            size++;
        }
    }

    //和添加很相似，都需要遍历，不同的是，如果遍历到节点不包括某个字符，直接返回false即可，
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果不包含该字符对应的节点，就说明不含有
            if (cur.next.get(c) == null) {
                return false;
            }
            //指向下一个节点
            cur = cur.next.get(c);
        }
        //到这个位置，也不一定存在这个单词，因为有可能查询的这个单词是存入的某个单词的前缀，例如查询pan，但是字典树中只有panda这个单词
        //所以需要根据isWord判断当前节点是否是一个单词
        return cur.isWord;
    }

    //查询是否在Trie中存在有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            //如果不包含该字符对应的节点，就添加进去
            if (cur.next.get(c) == null) {
                return false;
            }
            //指向下一个节点
            cur = cur.next.get(c);
        }
        return true;
    }


}
