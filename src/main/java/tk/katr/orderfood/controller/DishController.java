package tk.katr.orderfood.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.katr.orderfood.config.CustomAuthorization;
import tk.katr.orderfood.domain.ApiResponse;
import tk.katr.orderfood.domain.Dish;
import tk.katr.orderfood.service.DishService;


@RestController
@RequestMapping("/api/dish")
@Slf4j
public class DishController {
    @Resource
    DishService dishService;

    @PostMapping
    ApiResponse<String> addDish(@RequestBody Dish dish) {
        return dishService.save(dish) ? ApiResponse.success("添加成功") : ApiResponse.error("添加失败");
    }

    @GetMapping()
    @CustomAuthorization
    ApiResponse<Page<Dish>> getDish(@RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size) {
        Page<Dish> dishPage = new Page<>(page, size);

        return ApiResponse.success(dishService.page(dishPage));
    }

    @GetMapping("/{id}")
    ApiResponse<Dish> getDish(@PathVariable String id) {
        return ApiResponse.success(dishService.getById(id));
    }

    @PutMapping()
    ApiResponse<String> updateDish(@RequestBody Dish dish) {
        return dishService.updateById(dish) ? ApiResponse.success("修改成功") : ApiResponse.error("修改失败");
    }

    @PutMapping("/{id}")
    @CustomAuthorization
    ApiResponse<String> favourDish(@PathVariable String id) {
        log.info("点赞菜品id：" + id);
        Dish dish = dishService.getById(id);
        if (dish == null) {
            log.error("点赞失败，未查询到id：" + id);
            return ApiResponse.error("点赞失败");
        }
        if (dish.getFavour() == null) {
            dish.setFavour(0);
        }
        return dishService.updateById(Dish.builder().id(Integer.valueOf(id)).favour(dish.getFavour() + 1).build()) ? ApiResponse.success((String.valueOf(dish.getFavour()+1))) : ApiResponse.error("点赞失败");
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteDish(@PathVariable String id) {
        return dishService.removeById(id) ? ApiResponse.success("删除成功") : ApiResponse.error("删除失败");
    }


}
