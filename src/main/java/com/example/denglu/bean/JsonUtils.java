package com.example.denglu.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaolong
 * @create 2020-07-01 10:40
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonUtils {
    private int code;
    private String msg;
}
