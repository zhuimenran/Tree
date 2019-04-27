package com.txb.tree.binary.test;

import java.util.ArrayList;
import java.util.Random;

import com.txb.tree.binary.util.BST;

public class MainD {

	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		int count = 1000;
		Random random = new Random();
		
		for(int i = 0; i < count; i++) {
			bst.add(random.nextInt(10000));
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		int t = 0;
		while(!bst.isEmpty()) {
			list.add(bst.removeMin());
			t++;
		}

		System.out.println(list);
		System.out.println(t);
		}

}
