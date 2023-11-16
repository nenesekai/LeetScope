package nenesekai.leetscope.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qixuan Chen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "Success", null);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(SUCCESS_CODE, msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, "Success", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(SUCCESS_CODE, msg, data);
    }

    public static final String SUCCESS_CODE = "SUCCESS";
}
