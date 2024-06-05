package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

  private final MemberRepository memberRepository;
  private final MissionRepository missionRepository;
  private final MemberMissionRepository memberMissionRepository;

  @Transactional
  @Override
  public MemberMission addMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO) {
    Member member = memberRepository.findById(requestDTO.getMemberId()).get();
    Mission mission = missionRepository.findById(requestDTO.getMissionId()).get();

    MemberMission memberMission = MemberMissionConverter.toMemberMission(mission);
    memberMission.setMember(member);
    return memberMissionRepository.save(memberMission);
  }

  @Override
  public boolean existsMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO) {
    Member member = memberRepository.findById(requestDTO.getMemberId()).get();
    Mission mission = missionRepository.findById(requestDTO.getMissionId()).get();
    return !memberMissionRepository.existsByMissionAndMember(mission, member);
  }
}
