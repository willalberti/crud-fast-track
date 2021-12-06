package com.vivo.kafkaproduce.domain;


import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.google.gson.annotations.SerializedName;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-11-03T01:27:18.177Z")
public class OrderDto {
  
  @NotNull
  @SerializedName("description")
  private String description;

  @SerializedName("id")
  private Long id;

  @NotNull
  @SerializedName("name")
  private String name;

  @NotNull @PositiveOrZero
  @SerializedName("total")
  private Double total;

  
  @SerializedName("status")
  private StatusEnum status;

  
  
  public OrderDto description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OrderDto id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderDto name(String name) {
    this.name = name;
    return this;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OrderDto total(Double total) {
    this.total = total;
    return this;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public OrderDto status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDto orderDto = (OrderDto) o;
    return Objects.equals(this.description, orderDto.description) &&
        Objects.equals(this.id, orderDto.id) &&
        Objects.equals(this.name, orderDto.name) &&
        Objects.equals(this.total, orderDto.total) &&
        Objects.equals(this.status, orderDto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, id, name, total, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDto {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }


  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
