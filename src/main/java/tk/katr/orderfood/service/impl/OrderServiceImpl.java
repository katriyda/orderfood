package tk.katr.orderfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.katr.orderfood.domain.Dish;
import tk.katr.orderfood.domain.Order;
import tk.katr.orderfood.mapper.DishMapper;
import tk.katr.orderfood.mapper.OrderMapper;
import tk.katr.orderfood.service.DishService;
import tk.katr.orderfood.service.OrderService;

@Service

public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
