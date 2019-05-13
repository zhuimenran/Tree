package com.txb.tree.avl;



import java.util.ArrayList;

/**
 * AVL树: 自平衡机制的二分搜索树
 * 平衡的定义就是每一个节点的左右子树的高度差不超过1
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K key;//键
        V value;//键值
        Node left;//指向左子树
        Node right;//指向右子树
        int height;//每一个节点对应的高度值

        //节点构造函数
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;//默认的高度都是1

        }
    }

    //AVL的根节点
    private Node root;
    //节点数量
    private int size;


    /**
     * AVL的构造函数
     */
    public AVLTree() {
        root = null;
        size = 0;
    }

    //得到节点数量
    @Override
    public int getSize() {
        return size;
    }

    //是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    //判断是否是二叉搜索树。二叉树有一个特性就是中序遍历后，是一个按照顺序的集合
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        //判断前一个元素是否小于后一个元素
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                //前一个元素大于后一个元素，不是BST
                return false;
            }
        }
        return true;
    }

    //进行中序遍历，(左中右)并且把遍历结果存入到集合中
    private void inOrder(Node root, ArrayList<K> keys) {
        if (root == null) {
            return;
        }
        inOrder(root.left, keys);
        keys.add(root.key);
        inOrder(root.right, keys);
    }

    //是否是平衡二叉树
    //平衡二叉树的定义就是节点的左右子树高度差不超过1
    //需要递归调用
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //是否为平衡二叉树
    private boolean isBalanced(Node node) {
        if (node == null) {//节点为null，属于平衡二叉树
            return true;
        }
        //根据平衡因子判断
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {//平衡因子大于1，不是平衡二叉树
            return false;
        }
        //到目前为止，当前节点是平衡二叉树，那么需要判断左右子树是否也是平衡二叉树，
        // 所以循环递归调用左右子树
        return isBalanced(node.left) && isBalanced(node.right);

    }

    //得到节点的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //得到节点的平衡因子，平衡因子等于左右子树的高度差
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                                               x
    //       / \                                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->             / \   / \
    //    z   T3                                 T1  T2 T3 T4
    //   / \
    // T1   T2
    //RR
    private Node rightRotate(Node y) {
        //暂存y的左右节点
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        //更新height 先更新y 的值，然后再更新x的值，因为x的height和y的height有关
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                                             x
    //  /  \                                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->           / \   / \
    //   T2  z                              T1 T2 T3 T4
    //      / \
    //     T3 T4
    //左旋
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        //坐旋转，就是节点y向左旋转一个，那么根节点就变成了y的右节点
        return x;
    }

    //添加节点
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //添加节点，返回添加节点后的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        //向左子树添加节点
        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
        	 //向右子树添加节点
            node.right = add(node.right, key, value);
        } else {
        	//更新节点
            node.value = value;
        }

        //更新高度，node 节点的高度就等于左右子树中最大高度加上yi
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //插入的元素在不平衡的节点的左侧的左侧 LL类型
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //需要右旋转
            return rightRotate(node);
        }
        
        //插入的元素在不平衡的节点的右侧的右侧 RR类型
        //平衡因子小于-1，就说明右子树比左子树高，
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        //插入的元素在不平衡的节点的左侧的右侧  LR类型
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            //对节点的左节点进行坐旋转,旋转后指向节点的左节点，就变成了LL类型的
            node.left = leftRotate(node.left);
            //再进行LL类型的右旋转
            return rightRotate(node);
        }

        //插入的元素在不平衡的节点的右侧的左侧  LR类型
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //得到node
    private Node getNode(K key) {
        return getNode(root, key);
    }

    //得到node
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

    //是否包含
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    //得到键值
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

    //删除节点
    @Override
    public V remove(K key) {
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除节点，返回根节点
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode = null;//用来保存删除后的根节点
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {//找到要删除的节点

            if (node.left == null) {
                Node rightNode = node.right;
                size--;
                node.right = null;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //删除节点左右节点都存在
                //找到后继结点
                Node successor = minimum(node.right);
                //删除最小节点，并且后继结点的右节点指向该删除后的节点
                successor.right = remove(node.right, successor.key);
                //要删除的做节点赋值给后继结点
                successor.left = node.left;
                //删除节点
                node.left = node.right = null;
                //返回这个后继结点
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        //进行平衡的维护
        //更新高度，node 节点的高度就等于左右子树中最大高度加上yi
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //插入的元素在不平衡的节点的左侧的左侧 LL类型
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            //需要右旋转
            return rightRotate(retNode);
        }
        
        //插入的元素在不平衡的节点的右侧的右侧 RR类型
        //平衡因子小于-1，就说明右子树比左子树高，
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        //插入的元素在不平衡的节点的左侧的右侧  LR类型
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            //对节点的左节点进行坐旋转,旋转后指向节点的左节点，就变成了LL类型的
            retNode.left = leftRotate(retNode.left);
            //再进行LL类型的右旋转
            return rightRotate(retNode);
        }

        //插入的元素在不平衡的节点的右侧的左侧  LR类型
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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


}
