package ch.heigvd.gamification.api.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * Event
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-10T14:12:42.843+01:00")

public class Event   {
  private Long id = null;

  private Long userId = null;

  private String pointScale = null;

  private Long increase = null;

  public Event id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Event userId(Long userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(required = true, value = "")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Event pointScale(String pointScale) {
    this.pointScale = pointScale;
    return this;
  }

   /**
   * Get pointScale
   * @return pointScale
  **/
  @ApiModelProperty(required = true, value = "")
  public String getPointScale() {
    return pointScale;
  }

  public void setPointScale(String pointScale) {
    this.pointScale = pointScale;
  }

  public Event increase(Long increase) {
    this.increase = increase;
    return this;
  }

   /**
   * Get increase
   * @return increase
  **/
  @ApiModelProperty(required = true, value = "")
  public Long getIncrease() {
    return increase;
  }

  public void setIncrease(Long increase) {
    this.increase = increase;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.id, event.id) &&
        Objects.equals(this.userId, event.userId) &&
        Objects.equals(this.pointScale, event.pointScale) &&
        Objects.equals(this.increase, event.increase);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, pointScale, increase);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    pointScale: ").append(toIndentedString(pointScale)).append("\n");
    sb.append("    increase: ").append(toIndentedString(increase)).append("\n");
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

