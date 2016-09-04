package com.rockit.core.pojo;

import java.io.Serializable;

public class Pagination implements Serializable {
    private long pageNo = 1;
    private long pageSize = 10;
    private long total = 0;
    private long liseSize = 5;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getOffset() {
        return (pageNo - 1) * pageSize;
    }

    public long getLimit() {
        return pageSize;
    }

    public ListRange getRange() {
        if (total <= 0) {
            return ListRange.EMPTY;
        }
        long size = this.liseSize > 0 ? this.liseSize : 5;
        long totalPage = getTotalPage();
        if (totalPage <= size) {
            return new ListRange(1, totalPage);
        }
        long start, end;
        long pivot = (size - 1) / 2;
        start = this.pageNo - pivot;
        if (start <= 1) {
            return new ListRange(1, size);
        }
        end = (start - 1) + size;
        if (end > totalPage) {
            return new ListRange(totalPage - size + 1, totalPage);
        }
        return new ListRange(start, end);
    }

    public long getTotalPage() {
        return (total - 1) / pageSize + 1;
    }

    public long getFirst() {
        if (total == 0) {
            return 0;
        }
        return (pageNo - 1) * pageSize + 1;
    }

    public long getLast() {
        long end = pageNo * pageSize;
        if (end > total) {
            end = total;
        }
        return end;
    }

    public long getNext() {
        if (isLast()) {
            return pageNo;
        }
        return pageNo + 1;
    }

    public long getPrev() {
        if (isFirst()) {
            return 1;
        }
        return pageNo - 1;
    }

    public boolean isFirst() {
        return pageNo <= 1;
    }

    public boolean isLast() {
        return pageNo * pageSize >= total;
    }

    public long getLiseSize() {
        return liseSize;
    }

    public void setLiseSize(long liseSize) {
        this.liseSize = liseSize;
    }

    public static class ListRange {
        public static final ListRange EMPTY = new ListRange(0, -1);
        private long start;
        private long end;

        public ListRange() {
        }

        public ListRange(long start, long end) {
            this.end = end;
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }
    }
}
