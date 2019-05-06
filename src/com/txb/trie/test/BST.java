package com.txb.trie.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	
	//寻找最小节点
	public E mininum() {
		if(size == 0) {
			throw new IllegalArgumentException("为空");
		}
		return mininum(root).e;
	}
	
	//寻找最小节点所在节点的位置，递归算法
	private Node mininum(Node node) {
		if(node.left == null) {
			return node;
		}
		
		return mininum(node.left);
	}
	
	//寻找最大节点
		public E maxinum() {
			if(size == 0) {
				throw new IllegalArgumentException("为空");
			}
			return maxinum(root).e;
		}
		
		//寻找最da节点所在节点的位置，递归算法
		private Node maxinum(Node node) {
			if(node.right == null) {
				return node;
			}
			
			return maxinum(node.right);
		}
	
	//删除最小节点
	public E removeMin() {
		E ret = mininum();
		root = removeMin(root);//不要忘了
		return ret;
	}
	
	//删除以node为更的最小节点
	//返回删除节点后的新的二分搜素树的根
	private Node removeMin(Node node) {
		if(node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			return rightNode;
		}
		//说明node还有左子树
		node.left = removeMin(node.left);
		
		return node;
	}
	
	//删除最大节点
		public E removeMax() {
			E ret = maxinum();
			root = removeMax(root);//不要忘了
			return ret;
		}
		
		//删除以node为更的最大节点
		//返回删除节点后的新的二分搜素树的根
		private Node removeMax(Node node) {
			if(node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			//说明node还有右子树
			node.right = removeMin(node.right);
			
			return node;
		}
		
		//删除节点
		public void remove(E e) {
			root = remove(root, e);
		}
		
		//返回删除节点后的根
		private Node remove(Node node,E e) {
			if(node == null) {
				return null;
			}
			
			if(e.compareTo(node.e)<0) {
				node.left = remove(node.left, e);
				return node;
			}else if(e.compareTo(node.e)>0) {
				node.right = remove(node.right, e);
				return node;
			}else {//删除node
				
				//如果node.left =null
				if(node.left == null) {
					Node rightNode = node.right;
					node.right = null;
					size--;
					return rightNode;
				}
				if(node.right == null) {
					Node leftNode = node.left;
					node.left = null;
					size--;
					return leftNode;
				}
					
				//待删除节点的左右节点都不为空
				//找到比待删节点小的最大节点
				Node successor = mininum(node.right); 
				successor.right = removeMin(node.right);
				successor.left = node.left;
				node.left = node.right =  null;
				return successor;
			}
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
	
	
	//非递归的前序遍历
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.e);
			if(cur.right != null) {
				stack.push(cur.right);
			}
			if(cur.left != null) {
				stack.push(cur.left);
			}
		}
		
	}
	
	//非递归实现层序遍历
	public void levelOrder() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node cur = queue.remove();
			System.out.println(cur.e);
			if(cur.left != null) {
				queue.add(cur.left);
			}
			if(cur.right != null) {
				queue.add(cur.right);
			}
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
