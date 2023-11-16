package nenesekai.leetscope.model;

import lombok.Data;

@Data
public class NoDataResult extends Result {
    public NoDataResult(String code, String msg) {
        super(code, msg);
    }
    public static NoDataResult success() {
        return new NoDataResult(SUCCESS_CODE, "Success");
    }
    public static NoDataResult success(String msg) {
        return new NoDataResult(SUCCESS_CODE, msg);
    }

    public static NoDataResult failed(String msg) {
        return new NoDataResult(FAILED_CODE, msg);
    }

    public static NoDataResult failed(String code, String msg) {
        return new NoDataResult(code, msg);
    }
}
