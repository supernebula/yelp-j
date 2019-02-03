package evol.common.api;

public enum StatusCode {
    //成功
    SUCCESS(200, "成功"),
    //参数错误
    PARAMS_ERROR(1000, "参数错误"),
    //服务器错误
    SERVER_ERROR(2000, "服务器错误");

    private int code;
    private String description;

    private StatusCode(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }

}
