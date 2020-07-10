package com.example.denglu.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaolong
 * @create 2020-07-08 20:07
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserQuery {
    private String username;
    private String password;
}
