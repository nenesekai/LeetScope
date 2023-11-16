package nenesekai.leetscope.model;

import com.mysql.cj.log.Log;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResult extends Result {
    private String token;

    public LoginResult(String code, String msg, String token) {
        super(code, msg);
        this.token = token;
    }

    public static LoginResult success(String token) {
        return new LoginResult(SUCCESS_CODE, "Login Success", token);
    }

    public static LoginResult failed(String code, String msg) {
        return new LoginResult(code, msg, null);
    }
}
