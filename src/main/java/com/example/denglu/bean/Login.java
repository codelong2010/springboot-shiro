package com.example.denglu.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {

  private int id;
  private String username;
  private String password;
  private List<Role> roleList;
}
