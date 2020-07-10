package com.example.denglu.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission {

  private long id;
  private long pId;
  private long rId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPId() {
    return pId;
  }

  public void setPId(long pId) {
    this.pId = pId;
  }


  public long getRId() {
    return rId;
  }

  public void setRId(long rId) {
    this.rId = rId;
  }

}
