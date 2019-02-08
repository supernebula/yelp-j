package evol.common;

import lombok.*;

/**
 * 搜索参数基类
 */
@Data
public abstract class BaseSearchParam {

    public BaseSearchParam(){
        page = 0;
        pageSize = 10;
        sort = Sort.ASC;
    }

    private int page;

    private int pageSize;

    private Sort sort;

    public void setPage(int value){
        if(value >= 0)
            this.page = value;
    }
}
