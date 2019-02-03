package evol.common;

public enum Sort {
    //升序
    ASC(0, "成功"),
    //降序
    DESC(1, "降序");

    private int code;
    private String name;

    private Sort(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }
}
