package tk.katr.orderfood.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)

    private Integer id;
    private String name;
    private String password;
    private String phone;
    private Integer status;
    @TableLogic
    private Integer deleted;
}
