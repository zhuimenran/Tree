package com.txb.trie.test;

public interface Set<E> {
	public void add(E e);
	
	public boolean isEmpty();
	
	public boolean contains(E e);
	
	public int getSize();
	
	public void remove(E e);
}
