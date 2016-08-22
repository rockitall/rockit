package com.rockit.core.pojo;

/**
 * Created by Allen on 2016/8/21.
 */
public class Pagination {
    private int pageNo = 0;
    private int pageSize = 20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
