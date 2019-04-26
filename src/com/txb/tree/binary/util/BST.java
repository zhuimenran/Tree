package com.txb.tree.binary.util;

/**
 * 二分搜素树实现
 * @author 13125
 *
 */
public class BST<E extends Comparable<E>> {
	
	public class Node{
		public E e;
		public Node left;
		public Node right;
		
		public Node(E e) {
			this.e = e;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	private int size;
	
	//构造函数
	public BST() {
		root = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	
	//添加新节点
	public void add(E e) {
			root = add(root,e);//不要忘记了给根节点赋值
	}
	
	//向二分搜素树插入节点，递归算法
	//返回插入新节点的二分搜素树的根
	private Node add(Node node, E e) {
		
		//递归终止条件
		/*
		if(e.equals(node.e)) {
			return;
		}else  if(e.compareTo(node.e) < 0 &&node.left == null) {
			node.left = new Node(e);
			size++;
			return;
		}else if(e.compareTo(node.e) > 0 && node.right == null) {
			node.right = new Node(e);
			size++;
			return;
		}*/
		if(node == null) {
			size++;
			return new Node(e);
		}
		
		
		//递归调用
		if(e.compareTo(node.e)<0) {
			node.left = add(node.left, e);//接住左子树的变化
		}else if(e.compareTo(node.e)>0){
			node.right= add(node.right, e);
		}
		
		return node;
	}
	
	//在树中查询元素
	public boolean contains(E e) {
		
		return contains(root, e);
	}
	
	//看以node为根节点的树有无e,递归算法
	private boolean contains(Node node,E e) {
			if(node == null) {
				return false;
			}
			
			if(e.compareTo(node.e) == 0) {
				return true;
			}else if(e.compareTo(node.e) < 0){
				return contains(node.left,e);
			}else {
				return contains(node.right,e);
			}
	}
	
	//前序遍历
	public void preOrder() {
		preOrder(root);
	}
	
	//前序遍历以node为根节点的树,递归算法
	private void preOrder(Node node) {
		if(node == null) {
			return;
		}
		
		//访问给节点
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	
		//中序遍历
		public void inOrder() {
			inOrder(root);
		}
		
		//中序遍历以node为根节点的树,递归算法
		private void inOrder(Node node) {
			if(node == null) {
				return;
			}
			
			
			inOrder(node.left);
			//访问给节点
			System.out.println(node.e);
			inOrder(node.right);
		}
		
		//后序遍历
		public void postOrder() {
				postOrder(root);
		}
				
		//后序遍历以node为根节点的树,递归算法
		private void postOrder(Node node) {
			if(node == null) {
					return;
			}
					
					
					postOrder(node.left);
					
					postOrder(node.right);
					//访问给节点
					System.out.println(node.e);
			}
		
		
	
	//打印树的结构
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if(node == null) {
			res.append(generateDepthString(depth)+"null\n");
			return;
		}
		
		res.append(generateDepthString(depth) +  node.e + "\n");
		generateBSTString(node.left, depth+1, res);
		generateBSTString(node.right, depth+1, res);
		
	}
	//
	private String  generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < depth; i++) {
			res.append("--");
		}
	   return res.toString();
	}
	//
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root,0,res);
		
		return res.toString();
	}
}
