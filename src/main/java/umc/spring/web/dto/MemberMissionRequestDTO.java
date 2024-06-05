package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ChallengingMission;

public class MemberMissionRequestDTO {

  @ChallengingMission
  @Getter
  public static class CreateMemberMission {

    @NotNull
    Long memberId;
    @NotNull
    Long missionId;
  }
}