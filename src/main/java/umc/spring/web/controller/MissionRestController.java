package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

  private final MissionCommandService missionCommandService;

  @PostMapping("/stores")
  public ApiResponse<?> createMission(@Valid @RequestBody MissionRequestDTO.CreateMission requestDTO) {
    Mission mission = missionCommandService.createMission(requestDTO);
    return ApiResponse.onSuccess(MissionConverter.tocreateMissionResultDTO(mission));
  }
}
