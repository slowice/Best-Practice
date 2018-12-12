package xb.common_utils.commonUtils.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private String code;
    private String msg;
    private T data;
}
