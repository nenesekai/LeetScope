package nenesekai.leetscope.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>("SUCCESS", "Success", null);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>("SUCCESS", msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("SUCCESS", "Success", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>("SUCCESS", msg, data);
    }
}
