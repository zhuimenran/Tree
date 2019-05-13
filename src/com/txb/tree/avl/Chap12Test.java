package com.txb.tree.avl;



import java.util.ArrayList;
import java.util.Random;

import com.txb.trie.test.FileOperation;

public class Chap12Test {

    public static void main(String[] args) {
        testAVL();
        //testBST();
        //testRBTree();
        //testHashTable();
//        testHashTable_0();
    }

    private static void testAVL() {
        System.out.println("pride-and-prejudice: ");
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);
        System.out.println("Total words: " + words.size());
        long startTime = System.currentTimeMillis();

        AVLTree<String, Integer> map = new AVLTree<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }

        for (String word : words) {
            map.contains(word);
        }
        System.out.println("Frequency of prejudice : AVL  time  " + ((System.currentTimeMillis() - startTime) / 1000.0));
        for (String word : words) {

            map.remove(word);
            if (!map.isBalanced() || !map.isBST()) {
                System.out.println("false.......");
                return;
            }
        }
    }
/*
    private static void testBST() {
        System.out.println("pride-and-prejudice: ");
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);
        System.out.println("Total words: " + words.size());
        long startTime = System.currentTimeMillis();

        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        for (String word : words) {
            map.contains(word);
        }

        System.out.println("Frequency of prejudice : BST time  " + ((System.currentTimeMillis() - startTime) / 1000.0));

    }

*/
    /*
    private static void testRBTree() {
        System.out.println("pride-and-prejudice: ");
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);
        System.out.println("Total words: " + words.size());
        long startTime = System.currentTimeMillis();

        RBTree<String, Integer> map = new RBTree<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        for (String word : words) {
            map.contains(word);
        }

        System.out.println("Frequency of prejudice : RBTree time  " + ((System.currentTimeMillis() - startTime) / 1000.0));
    }
*/
    /*
    private static void testHashTable() {
        System.out.println("pride-and-prejudice: ");
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);
        System.out.println("Total words: " + words.size());
        long startTime = System.currentTimeMillis();

        HashTable<String, Integer> hashTable = new HashTable<>();
        for (String word : words) {
            if (hashTable.contains(word)) {
                hashTable.set(word, hashTable.get(word) + 1);
            } else {
                hashTable.add(word, 1);
            }
        }
        for (String word : words) {
            hashTable.contains(word);
        }

        System.out.println("Frequency of prejudice : HashTable time  " + ((System.currentTimeMillis() - startTime) / 1000.0));
    }
*/
//    private static void testHashTable_0() {
//        System.out.println("pride-and-prejudice: ");
//        ArrayList<String> words = new ArrayList<>();
//        FileOperation.readFile("pride-and-prejudice.txt", words);
//        System.out.println("Total words: " + words.size());
//        long startTime = System.currentTimeMillis();
//
//        HashTable<String, Integer> hashTable = new HashTable<>(131071);
//        for (String word : words) {
//            if (hashTable.contains(word)) {
//                hashTable.set(word, hashTable.get(word) + 1);
//            } else {
//                hashTable.add(word, 1);
//            }
//        }
//        for (String word : words) {
//            hashTable.contains(word);
//        }
//
//        System.out.println("Frequency of prejudice :  testHashTable_0 time  " + ((System.currentTimeMillis() - startTime) / 1000.0));
//    }
}
