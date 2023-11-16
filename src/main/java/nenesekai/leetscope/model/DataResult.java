package nenesekai.leetscope.model;

import lombok.Data;

@Data
public class DataResult<T> extends Result {
    private T data;

    public DataResult(String code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public static <T> DataResult<T> success(T data) {
        return new DataResult<>(SUCCESS_CODE, "Success", data);
    }

    public static <T> DataResult<T> success(String msg, T data) {
        return new DataResult<>(SUCCESS_CODE, msg, data);
    }

    public static <T> DataResult<T> success(String code, String msg, T data) {
        return new DataResult<>(code, msg, data);
    }
}
