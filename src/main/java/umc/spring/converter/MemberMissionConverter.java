package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {
  public static MemberMission toMemberMission(Mission mission) {
    return MemberMission.builder()
        .mission(mission)
        .build();
  }

  public static MemberMissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(MemberMission memberMission) {
    return MemberMissionResponseDTO.AddMissionResultDTO.builder()
        .MemberMissionId(memberMission.getId())
        .createAt(memberMission.getCreatedAt())
        .build();
  }
}