package evol.common.api;

import lombok.*;

@Data
public class ApiResult<T> {
    private int code;
    private String message;
    private final T data;

    public ApiResult(int code, String message, T value){
        this.code = code;
        this.message = message;
        this.data = value;

    }

    public static <T> ApiResult<T> success(T value){
        ApiResult result = new ApiResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDescription(), value);
        return result;
    }

    public static <T> ApiResult<T> paramError(){
        ApiResult result = new ApiResult(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getDescription(), null);
        return result;
    }
}
