package tk.katr.orderfood.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("dish")
@Builder
public class Dish {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private String des;
    private Integer status;
    @TableLogic
    private Integer deleted;
    private Integer favour;
}
