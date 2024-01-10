package tk.katr.orderfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.katr.orderfood.domain.Dish;
import tk.katr.orderfood.domain.User;
import tk.katr.orderfood.mapper.DishMapper;
import tk.katr.orderfood.mapper.UserMapper;
import tk.katr.orderfood.service.DishService;
import tk.katr.orderfood.service.UserService;

@Service

public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
