package evol.common.api;

import lombok.*;

@Data
public class ApiResult<T> {
    private int code;
    private boolean success;
    private String message;
    private final T data;

    public ApiResult(StatusCode status, T value){
        this.code = status.getCode();
        this.message = status.getDescription();
        this.data = value;
        this.success = this.code == StatusCode.SUCCESS.getCode();


    }

    public ApiResult(StatusCode status, String message, T value){
        this.code = status.getCode();
        this.message = message;
        this.data = value;
        this.success = this.code == StatusCode.SUCCESS.getCode();

    }

    public static <T> ApiResult<T> success(T value){
        ApiResult result = new ApiResult(StatusCode.SUCCESS, StatusCode.SUCCESS.getDescription(), value);
        return result;
    }

    public static <T> ApiResult<T> paramError(){
        ApiResult result = new ApiResult(StatusCode.PARAMS_ERROR, StatusCode.PARAMS_ERROR.getDescription(), null);
        return result;
    }
}
