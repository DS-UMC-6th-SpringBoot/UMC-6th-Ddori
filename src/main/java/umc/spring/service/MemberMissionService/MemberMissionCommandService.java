package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
  public MemberMission addMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO);

  public boolean existsMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO);
}