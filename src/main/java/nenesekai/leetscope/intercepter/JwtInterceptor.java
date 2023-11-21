package nenesekai.leetscope.intercepter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nenesekai.leetscope.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import nenesekai.leetscope.util.JwtUtil;

import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token provided in the request header");
            return false;
        }
        String token = authorization.replace("Bearer ", "");
        try {
            Integer uid = Integer.valueOf(JwtUtil.parseToken(token));
            if (userService.isValidUserId(uid)) {
                request.setAttribute("uid", uid);
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token Provided");
                return false;
            }
        } catch (ExpiredJwtException e) {
            response.sendError(999, e.getMessage());
            return false;
        }
    }
}
