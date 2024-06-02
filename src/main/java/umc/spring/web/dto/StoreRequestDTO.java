package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

  @Getter
  public static class CreateStoreDTO {
    @NotNull
    Long regionId;
    @NotNull
    String name;
    @Size(min = 5, max = 12)
    String address;
    @NotNull
    private Float score;
  }
}
