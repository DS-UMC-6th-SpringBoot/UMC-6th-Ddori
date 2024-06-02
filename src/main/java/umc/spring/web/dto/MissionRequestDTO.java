package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

public class MissionRequestDTO {

  @Getter
  public static class CreateMission {
    @ExistStore
    Long storeId;
    @NotNull
    Integer reward;
    @NotNull
    LocalDateTime deadline;
    @NotBlank
    String missionSpec;
  }
}
