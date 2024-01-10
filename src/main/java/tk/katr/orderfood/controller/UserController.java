package tk.katr.orderfood.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import tk.katr.orderfood.domain.ApiResponse;
import tk.katr.orderfood.domain.User;
import tk.katr.orderfood.service.UserService;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Resource
    UserService userService;
    @Value("${security.salt}")
    private String salt;

    @PostMapping
    public ApiResponse<String> addUser(@RequestBody User user) {
        String hashedPassword = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes());
        user.setPassword(hashedPassword);
        userService.save(user);
        return ApiResponse.success("添加成功");
    }

}
