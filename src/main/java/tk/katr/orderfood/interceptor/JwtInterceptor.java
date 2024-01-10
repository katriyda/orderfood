package tk.katr.orderfood.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tk.katr.orderfood.config.CustomAuthorization;
import tk.katr.orderfood.domain.User;
import tk.katr.orderfood.utils.JwtUtils;

import java.lang.reflect.Method;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            if (!hasCustomAuthorizationAnnotation(handlerMethod.getMethod())) {
                String token = request.getHeader("Authorization");

                if (token != null && token.startsWith("Bearer ")) {
                    //提取jwt
                    token = token.substring(7);
                    //获取user
                    User user = jwtUtils.getUserFromToken(token);

                    if (user != null) {
                        request.setAttribute("userid", user.getId());
                        request.setAttribute("username", user.getName());
                        log.info("Request from IP: {} - User: {} - Accessing: {}", getClientIpAddress(request), user.getName(), request.getRequestURI());

                        return true;
                    }
                }
                log.warn("Unauthorized access from IP: {} - Accessing: {}", getClientIpAddress(request), request.getRequestURI());

                response.sendError(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return false;
            }
        }
        return true;
    }
    // Get client IP address from the request
    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    private boolean hasCustomAuthorizationAnnotation(Method method) {
        return method.isAnnotationPresent(CustomAuthorization.class);
    }
}
