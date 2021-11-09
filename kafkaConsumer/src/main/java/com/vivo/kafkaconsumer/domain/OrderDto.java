package com.vivo.kafkaconsumer.domain;


import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * OrderDto
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-11-03T01:27:18.177Z")
public class OrderDto {
  @SerializedName("description")
  private String description = null;

  @SerializedName("id")
  private Long id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("total")
  private Double total = null;

  
  
	
  /**
   * Gets or Sets status
   */
//  @JsonAdapter(StatusEnum.Adapter.class)
//  public enum StatusEnum {
//    NOT_PROCESSED("NOT_PROCESSED"),
//    
//    PROCESSED("PROCESSED");
//
//    private String value;
//
//    StatusEnum(String value) {
//      this.value = value;
//    }
//
//    public String getValue() {
//      return value;
//    }
//
//    @Override
//    public String toString() {
//      return String.valueOf(value);
//    }
//
//    public static StatusEnum fromValue(String text) {
//      for (StatusEnum b : StatusEnum.values()) {
//        if (String.valueOf(b.value).equals(text)) {
//          return b;
//        }
//      }
//      return null;
//    }
//
//    public static class Adapter extends TypeAdapter<StatusEnum> {
//      @Override
//      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
//        jsonWriter.value(enumeration.getValue());
//      }
//
//      @Override
//      public StatusEnum read(final JsonReader jsonReader) throws IOException {
//        String value = jsonReader.nextString();
//        return StatusEnum.fromValue(String.valueOf(value));
//      }
//    }
//  }

  @SerializedName("status")
  private StatusEnum status = null;

  public OrderDto description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
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

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
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

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
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

   /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")
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

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
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

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
