package nenesekai.leetscope.model;

import lombok.Data;

/**
 * @author Qixuan Chen
 */

@Data
public abstract class Result {
    private String msg;
    private String code;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String FAILED_CODE = "FAILED";
    public static final String MISSING_PARAM_CODE = "MISSING_PARAMS";
    public static final String INVALID_PARAM_CODE = "INVALID_PARAMS";
    public static final String INVALID_TOKEN_CODE = "INVALID_TOKEN";
    public static final String EXPIRED_TOKEN_CODE = "EXPIRED_TOKEN";
}