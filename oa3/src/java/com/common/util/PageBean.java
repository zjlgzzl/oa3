/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.PageBean
 */
package com.common.util;

public class PageBean {
    private int pageNow = 1;
    private int pageSize = 20;
    private int pageCount;
    private int beginPageIndex;
    private int endPageIndex;
    private int rowCount;

    public PageBean() {
    }

    public PageBean(int pageCount, int pageNow) {
        this.pageCount = pageCount == 0 ? 1 : pageCount;
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return this.pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBeginPageIndex() {
        return this.beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return this.endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}

