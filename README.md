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
