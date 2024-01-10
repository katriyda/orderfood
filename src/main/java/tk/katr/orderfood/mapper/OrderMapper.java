package tk.katr.orderfood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tk.katr.orderfood.domain.Order;
import tk.katr.orderfood.domain.User;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
