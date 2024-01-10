package tk.katr.orderfood.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import tk.katr.orderfood.config.CustomAuthorization;
import tk.katr.orderfood.domain.ApiResponse;
import tk.katr.orderfood.domain.User;
import tk.katr.orderfood.service.UserService;
import tk.katr.orderfood.utils.JwtUtils;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Resource
    UserService userService;
    @Value("${security.salt}")
    private String salt;
    @Resource
    private JwtUtils jwtUtils;

    @PostMapping()
    @CustomAuthorization
    public ApiResponse<String> login(@RequestBody User user) {
        String hashedPassword = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes());
        User userd = userService.getById(user.getId());
        if (userd.getPassword().equals(hashedPassword)) {
            return ApiResponse.success(jwtUtils.generateToken(userd));
        }
        return ApiResponse.error("错误");
    }

}
