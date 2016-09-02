/**
 * zhandc 2016年8月6日
 */
package com.rockit.core.pojo;

import java.util.List;


/**
 * @author zhandc 2016年8月6日
 */
public class Meta {
    private MetaSetting metaSetting;
    private List<Sort> sorts;

    public String serializedSorts() {
        String result = "";
        if (sorts == null || sorts.isEmpty()) return result;
        result = "order by ";
        for (int i = 0; i < sorts.size(); i++) {
            Sort sort = sorts.get(i);
            result += (i != 0) ? "," : "";
            result += sort.getField();
            result += " ";
            result += sort.getSc();
            ;
        }
        return result;
    }

    public String getSort() {
        return serializedSorts();
    }

    public MetaSetting getMetaSetting() {
        return metaSetting;
    }

    public void setMetaSetting(MetaSetting metaSetting) {
        this.metaSetting = metaSetting;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }


}
