package xb.common.entity;

import lombok.Data;

@Data
public class Result {
    private String code;
    private String message;

    private Result(){}

    public Result(String code){
        this.code = code;
        this.message = "";
    }

    public Result(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static final String OK = "200";
    public static final String ERROR = "500";

}
