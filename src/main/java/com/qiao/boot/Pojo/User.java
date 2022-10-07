package com.qiao.boot.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:这是一个实体类
 * @date 2022/10/5 12:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String username;
    private String password;
}
