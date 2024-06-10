package umc.spring.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

public class MemberConverter {

  public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
    return MemberResponseDTO.JoinResultDTO.builder()
        .memberId(member.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Member toMember(MemberRequestDTO.JoinDto request){

    Gender gender = null;

    switch (request.getGender()){
      case 1:
        gender = Gender.MALE;
        break;
      case 2:
        gender = Gender.FEMALE;
        break;
      case 3:
        gender = Gender.NONE;
        break;
    }

    return Member.builder()
        .address(request.getAddress())
        .specAddress(request.getSpecAddress())
        .gender(gender)
        .name(request.getName())
        .memberPreferList(new ArrayList<>())
        .build();
  }

  public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
    return MemberResponseDTO.ReviewPreViewDTO.builder()
        .ownerNickname(review.getMember().getName())
        .score(review.getScore())
        .createdAt(review.getCreatedAt().toLocalDate())
        .body(review.getBody())
        .build();
  }
  public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

    List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
        .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

    return MemberResponseDTO.ReviewPreViewListDTO.builder()
        .isLast(reviewList.isLast())
        .isFirst(reviewList.isFirst())
        .totalPage(reviewList.getTotalPages())
        .totalElements(reviewList.getTotalElements())
        .listSize(reviewPreViewDTOList.size())
        .reviewList(reviewPreViewDTOList)
        .build();
  }

  public static MemberResponseDTO.MemberMissionPreViewDTO memberMissionPreViewDTO(MemberMission memberMission){
    return MemberResponseDTO.MemberMissionPreViewDTO.builder()
        .mission(memberMission.getMission().getMissionSpec())
        .member(memberMission.getMember().getName())
        .status(memberMission.getStatus())
        .build();
  }
  public static MemberResponseDTO.MemberMissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList){

    List<MemberResponseDTO.MemberMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
        .map(MemberConverter::memberMissionPreViewDTO).collect(Collectors.toList());

    return MemberResponseDTO.MemberMissionPreViewListDTO.builder()
        .isLast(missionList.isLast())
        .isFirst(missionList.isFirst())
        .totalPage(missionList.getTotalPages())
        .totalElements(missionList.getTotalElements())
        .listSize(missionPreViewDTOList.size())
        .missionList(missionPreViewDTOList)
        .build();
  }
}