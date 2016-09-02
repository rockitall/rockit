/**
 * zhandc 2016年8月6日
 */
package com.rockit.core.pojo;

/**
 * @author zhandc 2016年8月6日
 */
public class MetaSetting {
    /*
     * backstageSort:true|false
     * true:后台sql排序
     * false:前台model排序
    */
    private boolean backstageSort;
    /*
     * backstageStat:true|false
     * true:后台sql统计
     * false:前台model统计
    */
    private boolean backstageCal;


    public boolean isBackstageSort() {
        return backstageSort;
    }

    public void setBackstageSort(boolean backstageSort) {
        this.backstageSort = backstageSort;
    }

    public boolean isBackstageCal() {
        return backstageCal;
    }

    public void setBackstageCal(boolean backstageCal) {
        this.backstageCal = backstageCal;
    }


}