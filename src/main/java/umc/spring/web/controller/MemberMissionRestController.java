package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MemberMissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMissions")
public class MemberMissionRestController {

  private final MemberMissionCommandService memberMissionCommandService;

  @PostMapping("/")
  public ApiResponse<?> addMemberMission(@Valid @RequestBody MemberMissionRequestDTO.CreateMemberMission requestDTO) {
    MemberMission memberMission = memberMissionCommandService.addMemberMission(requestDTO);
    return ApiResponse.onSuccess(MemberMissionConverter.toAddMissionResultDTO(memberMission));
  }
}
