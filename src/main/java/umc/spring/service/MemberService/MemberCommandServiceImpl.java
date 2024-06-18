package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;
import umc.spring.web.dto.MemberRequestDTO.CompleteMissionDto;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

  private final MemberRepository memberRepository;

  private final MemberMissionRepository memberMissionRepository;

  private final MissionRepository missionRepository;

  private final FoodCategoryRepository foodCategoryRepository;

  @Override
  @Transactional
  public Member joinMember(MemberRequestDTO.JoinDto request) {

    Member newMember = MemberConverter.toMember(request);
    List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
        .map(category -> {
          return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        }).collect(Collectors.toList());

    List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

    memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

    return memberRepository.save(newMember);
  }

  @Override
  public MemberMission completeMemberMission(CompleteMissionDto request) {
    Member member = memberRepository.findById(request.getMemberId()).get();
    Mission mission = missionRepository.findById(request.getMissionId()).get();

    MemberMission memberMission = memberMissionRepository.findByMissionAndMember(mission, member);

    memberMission.setStatus(MissionStatus.COMPLETE);
    return memberMissionRepository.save(memberMission);
  }

  @Override
  public boolean isMemberMissionCompleted(MemberRequestDTO.CompleteMissionDto request) {
    Member member = memberRepository.findById(request.getMemberId()).get();
    Mission mission = missionRepository.findById(request.getMissionId()).get();

    MemberMission memberMission = memberMissionRepository.findByMissionAndMember(mission ,member);

    return memberMission.getStatus() == MissionStatus.COMPLETE;
  }

}