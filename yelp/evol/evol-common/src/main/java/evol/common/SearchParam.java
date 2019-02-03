package evol.common;

import lombok.*;

@Data
public abstract class SearchParam {

    public SearchParam(){
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
