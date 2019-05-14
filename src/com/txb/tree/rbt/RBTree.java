package com.txb.tree.rbt;

import com.txb.tree.avl.Map;

//基于BST实现映射类
public class RBTree<K extends Comparable<K>, V> implements Map<K, V> {

    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;//节点的颜色，红色或者黑色

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            //保持添加节点都是红色
            color = RED;//默认是红色，因为新添加的节点需要去融合

        }

        public Node() {
            this(null, null, null, null);
        }
    }

    private Node root;
    private int size;

//构造函数
    public RBTree() {
        root = null;
        size = 0;
    }

    //添加节点
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;//保持最终根节点为黑色
    }

    //向红黑树中添加一个元素
    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
    	//添加第一个节点
        if (node == null) {
            size++;
            return new Node(key, value);
        }
    //添加节点
        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else {
        	//更新节点
            node.value = value;
        }

        //维护红黑树

        //情况一： 左节点不是红色，右节点是红色，那么需要左旋转
        if (!isRed(node.left) && isRed(node.right)) {
            node = leftRotate(node);
        }
        //可能情况一处理就满足了情况二，所以需要继续处理
        if (isRed(node.left) && isRed(node.left.left)) {
            //情况二： 左节点是红色，但是左节点的左节点也是红色，需要进行右旋转
            node = rightRotae(node);
        }
        //情况二处理完，可能就满足了情况三，所以继续处理。
        if (isRed(node.left) && isRed(node.right)) {
            //情况三： 左右节点都是红色的，那么就需要进行颜色翻转
            filpColor(node);
        }


        return node;

    }

    //节点是否为红色
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \             			 /   \
    //    T2 T3            T1   T2
//左旋转，什么时候需要左旋转，依旧形成一个三节点
    private Node leftRotate(Node node) {
        Node x = node.right;//记录节点的右节点
        node.right = x.left;
        x.left = node;
        //维护红黑树的颜色
        //原来的根节点是什么颜色，现在x变成了根节点，也就和原来的保持一致
        x.color = node.color;//根节点的颜色就和之前的一样，
        //新添加的节点和node行程了一个新的三节点，左旋转后为了表明这个三节点的颜色，旋转后的左节点也就是node ,就得改成相应的红色
        node.color = RED;

        return x;
    }


    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                      	 /  \
    // y  T1                     T1  T2
    //右旋转，返回旋转后新的根节点
    private Node rightRotae(Node node) {

        Node x = node.left;
        node.left = x.right;
        x.right = node;

        //维护颜色
        x.color = node.color;
        //虽然这个时候node是在x 的右侧，但是也先把节点颜色设置为红色，最后再进行翻转就可以了
        node.color = RED;
        return x;
    }

    /**
     * 颜色翻转，
     * 当一个节点的左右节点都是红色的时候，就表明这个节点是一个三节点，那么就需要改成一个二节点，
     * 改的原理就是把左右子节点变成黑色，这样就变成了一个二节点树，
     * 而这个时候，这个节点需要向上融合，所以就需要把该节点的颜色改为红色
     *
     * @param node
     */
    private void filpColor(Node node) {

        node.left.color = node.right.color = BLACK;
        node.color = RED;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除操作可能会触发旋转操作
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {//找到要删除的节点

            if (node.left == null) {
                Node rightNode = node.right;
                size--;
                node.right = null;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //删除节点左右节点都存在
            //找到后继结点
            Node successor = minimum(node.right);
            //删除最小节点，并且后继结点的右节点指向该删除后的节点
            successor.right = removeMin(node.right);
            //要删除的做节点赋值给后继结点
            successor.left = node.left;
            //删除节点
            node.left = node.right = null;
            //返回这个后继结点
            return successor;
        }
    }


    //返回以node为根节点的最小值的e节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            //找到最小值，保留最小值右节点
            Node rightNode = node.right;
            node.right = null;
            size--;
            //并且返回这个右节点
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
//判断是否含有
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    
    //得到value
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    //更新
    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException(key + " does not exist ");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        return getNode(root, key);
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return node;
        }
    }
}
