package com.txb.trie.test;




import java.util.ArrayList;

import com.txb.trie.util.Trie;

public class Main {

    public static void main(String[] args) {
        testBSTSet();
        testTrie();
    }

    private static void testBSTSet() {
        System.out.println("pride-and-prejudice  BSTSet .....");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            BSTSet<String> bstSet = new BSTSet<>();

            for (String word : words) {
                bstSet.add(word);
            }

            for (String word : words) {
                bstSet.contains(word);
            }

            System.out.println("time :" + (System.nanoTime() - startTime) );
            System.out.println("长度 :" + bstSet.getSize());
        }
    }

    private static void testTrie() {
        System.out.println("pride-and-prejudice  MapSum .....");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            Trie trie = new Trie();

            for (String word : words) {
                trie.add(word);
            }

            for (String word : words) {
                trie.contains(word);
            }

            System.out.println("time :" + (System.nanoTime() - startTime) );
            System.out.println("长度 :" + trie.getSize());
        }
    }


}
