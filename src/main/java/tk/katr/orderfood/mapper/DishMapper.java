package tk.katr.orderfood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tk.katr.orderfood.domain.Dish;
import tk.katr.orderfood.domain.User;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
