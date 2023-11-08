package usc.edu.csci201.leetscope.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import usc.edu.csci201.leetscope.service.UserService;
import usc.edu.csci201.leetscope.util.JwtUtil;

import java.io.IOException;

@Component
public class JwtIntercepter implements HandlerInterceptor {
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
            Long uid = Long.valueOf(JwtUtil.parseToken(token));
            if (userService.isValidUserID(uid)) {
                request.setAttribute("uid", uid);
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token Provided");
                return false;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return false;
        }
    }
}
