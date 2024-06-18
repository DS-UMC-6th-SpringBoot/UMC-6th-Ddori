package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.CompleteMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

  private final MemberCommandService memberCommandService;
  private final MemberQueryService memberQueryService;

  @Operation(summary = "회원 가입 API", description = "회원 가입 API 입니다.")
  @PostMapping("/")
  public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
    Member member = memberCommandService.joinMember(request);
    return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
  }

  @Operation(summary = "사용자가 작성한 리뷰 목록 조회 API", description = "사용자가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
  @GetMapping("/reviews")
  public ApiResponse<?> getMemberReviewList(@CheckPage @RequestParam(name = "page") Integer page) {
    Page<Review> reviewList = memberQueryService.getReviewList(1L, page);
    return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));
  }

  @Operation(summary = "사용자가 진행중인 미션 목록 조회 API", description = "사용자의 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
  @GetMapping("/missions")
  public ApiResponse<?> getMemberMissionList(@CheckPage @RequestParam(name = "page") Integer page) {
    Page<MemberMission> memberMissionList = memberQueryService.getMemberMissionList(1L, page);
    return ApiResponse.onSuccess(MemberConverter.missionPreViewListDTO(memberMissionList));
  }

  @PostMapping("/missions/complete")
  @Operation(summary = "진행 중인 미션 진행 완료로 바꾸기 API", description = "진행 중인 미션의 상태를 완료로 변경하는 API 입니다.")
  public ApiResponse<?> completeMemberMission(@CompleteMission @RequestBody @Valid MemberRequestDTO.CompleteMissionDto request) {
    MemberMission memberMission  = memberCommandService.completeMemberMission(request);
    return ApiResponse.onSuccess(MemberConverter.memberMissionPreViewDTO(memberMission));
  }
}