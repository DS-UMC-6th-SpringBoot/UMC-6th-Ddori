package umc.spring.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {
  public static Mission toMission(MissionRequestDTO.CreateMission requestDTO) {
    return Mission.builder()
        .reward(requestDTO.getReward())
        .missionSpec(requestDTO.getMissionSpec())
        .deadline(LocalDate.from(requestDTO.getDeadline()))
        .memberMissionList(new ArrayList<>())
        .build();
  }

  public static MissionResponseDTO.CreateMissionResultDto tocreateMissionResultDTO(Mission mission) {
    return MissionResponseDTO.CreateMissionResultDto.builder()
        .missionId(mission.getId())
        .createAt(mission.getCreatedAt())
        .build();
  }
}
