package tk.katr.orderfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.katr.orderfood.domain.User;
import tk.katr.orderfood.mapper.UserMapper;
import tk.katr.orderfood.service.UserService;
@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
