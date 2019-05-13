package com.txb.tree.avl;

public interface Map<K,V> {
	public void add(K key, V value);
	
	public V remove(K key);
	
	public boolean contains(K key);
	
	public boolean isEmpty();
	
	public int getSize();
	
	public V get(K key);
	
	public void set(K key, V value);
}
