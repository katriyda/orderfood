package tk.katr.orderfood.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`order`")
public class Order {
    private Integer id;
    private Integer tableNumber;
    private String dishs;
    private Date createTime;
    private Date endTime;
    private String status;
    private Double totalAmount;
    @TableLogic
    private Integer deleted;
    private String phone;
    private String remark;
}
