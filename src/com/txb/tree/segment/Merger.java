package com.txb.tree.segment;

public interface Merger<E> {

    /**
     * 把两个E对象融合到一起，可以是相加，相减，取最大/小值等等
     * @param a
     * @param b
     * @return
     */
    E merge(E a, E b);
}
