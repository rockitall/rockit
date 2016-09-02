/**
 * zhandc 2016年8月6日
 */
package com.rockit.core.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author zhandc 2016年8月6日
 */
public class Pager<T> {
    private Long userId;
    private int page = 1;
    private int rp = 15;
    private int total;
    private Meta meta;//将sort组合成order by
    private List<T> rows;
    private String sort;//field sc

    private List chartData;


    /**
     * @return the chartData
     */
    public List getChartData() {
        return chartData;
    }

    /**
     * @param chartData the chartData to set
     */
    public void setChartData(List chartData) {
        this.chartData = chartData;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        int allPage = (int) Math.ceil((float) total / rp);
        if (allPage < page && allPage > 0) {
            setPage(allPage);
        }
    }

    public int getOffset() {
        return (page - 1) * rp;
    }

    public int getPage() {
        return page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    public void setPage(int page) {
        this.page = page;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStart() {
        if (page > 0)
            return (page - 1) * rp;
        else
            return 0;
    }

    public int getEnd() {
        if (page == 0) {
            page = 1;
        }
        int end = page * rp;
        if (end > total) {
            end = total;
        }
        return end;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
