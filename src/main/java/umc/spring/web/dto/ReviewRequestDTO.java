package umc.spring.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

  @Getter
  public static class CreateReviewDTO {
    @ExistStore
    Long storeId;
    @Size(min = 10, max = 1000)
    String body;
    @DecimalMin("0.5")
    @DecimalMax("5")
    Double score;
  }
}