package tk.katr.orderfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.katr.orderfood.config.CustomAuthorization;
import tk.katr.orderfood.domain.ApiResponse;
import tk.katr.orderfood.domain.Dish;
import tk.katr.orderfood.domain.Order;
import tk.katr.orderfood.service.DishService;
import tk.katr.orderfood.service.OrderService;
import tk.katr.orderfood.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    DishService dishService;

    @PostMapping
    @CustomAuthorization
    ApiResponse<String> addOrder(@RequestBody Order order) {
        log.info("添加订单" + order);
        String[] dishs = order.getDishs().split(",");
        Double prices = 0.0;
        StringBuilder dishStr = new StringBuilder();
        for (String dish : dishs) {
            String[] s = dish.split("\\.");
            Dish dish1 = dishService.getById(s[0]);
            if (dish1 == null) {
                return ApiResponse.error("菜品不存在");
            }
            dishStr.append(dish1.getName()).append(s[1]).append("个，");
            prices += dish1.getPrice();
        }
        dishStr.deleteCharAt(dishStr.length() - 1);
        order.setDishs(dishStr.toString());
        order.setTotalAmount(prices);
        order.setCreateTime(new Date());
        return orderService.save(order) ? ApiResponse.success("添加成功") : ApiResponse.error("添加失败");
    }

    @GetMapping("/phone")
    @CustomAuthorization
    ApiResponse<List<Order>> getOrderByPhone(@RequestParam String phone) {
        LambdaQueryWrapper<Order> eq = new LambdaQueryWrapper<Order>().eq(Order::getPhone, phone);
        return ApiResponse.success(orderService.list(eq));
    }

    @GetMapping
    @CustomAuthorization
    ApiResponse<Page<Order>> getOrder(@RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      @RequestParam(defaultValue = "") String status) {
        Page<Order> orderPage = new Page<>(page, size);
        if (status.isEmpty()) {
            return ApiResponse.success(orderService.page(orderPage));
        }
        return ApiResponse.success(orderService.page(orderPage, new LambdaQueryWrapper<Order>().eq(Order::getStatus, status)));
    }


    @GetMapping("/{id}")
    @CustomAuthorization
    ApiResponse<Order> getOrder(@PathVariable String id) {
        return ApiResponse.success(orderService.getById(id));
    }

    @PutMapping
    ApiResponse<String> updateOrder(@RequestBody Order order) {
        log.info("修改订单：" + order);
        if (order.getStatus().equals("订单完成")) {
            order.setEndTime(new Date());
        }
        return orderService.updateById(order) ? ApiResponse.success("修改成功") : ApiResponse.error("修改失败");
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteOrder(@PathVariable String id) {
        return orderService.removeById(id) ? ApiResponse.success("删除成功") : ApiResponse.error("删除失败");
    }


}
