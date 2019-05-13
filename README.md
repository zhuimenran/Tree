# Tree
树的各种结构及其实现，应用，解决方案


1，二分搜索树的实现
	（1）add  contains  前序，中序，后序遍历
	打印出树的结构
	
2，线段树的实现
    //线段树区间
    private E[] data;
    //用数组表示线段树
    private E[] tree;
    private Merger<E> merger;
2.1，创建线段树
	/**
     * 在treeIndex的位置，创建表示区间[l...r]的线段树
     *
     * @param treeIndex 要创建线段树的根节点所对应的索引
     * @param l         创建线段的左区间
     * @param r         创建线段的右区间
     */
    private void buildSegmentTree(int treeIndex, int l, int r) 
query和set操作

3，trie字典树
//当前这个节点，是否是某一个单词
        boolean isWord;

        //每一个节点有若干个指向下个节点的指针，next就是字符和Node的映射
        TreeMap<Character, Node> next;

 //查询是否在Trie中存在有单词以prefix为前缀
    public boolean isPrefix(String prefix) 
 
   //和添加很相似，都需要遍历，不同的是，如果遍历到节点不包括某个字符，直接返回false即可，
    public boolean contains(String word)
    
    
    add操作
    
    
 AVL树是根据它的发明者G.M. Adelson-Velsky和E.M. Landis命名的。
它是最先发明的自平衡二叉查找树，也被称为高度平衡树。相比于"二叉查找树"，它的特点是：AVL树中任何节点的两个子树的高度最大差别为1

AVL树的查找、插入和删除在平均和最坏情况下都是O(logn)。
如果在AVL树中插入或删除节点后，使得高度之差大于1。此时，AVL树的平衡状态就被破坏，它就不再是一棵二叉树；为了让它重新维持在一个平衡状态，就需要对其进行旋转处理。学AVL树，重点的地方也就是它的旋转算法
