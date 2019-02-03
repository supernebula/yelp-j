package evol.common;

import lombok.*;

@Data
public abstract class SearchParam {

    private int page;

    private int pageSize;

    private Sort sort;
}
