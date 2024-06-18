package umc.spring.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public class StoreConverter {

  public static Review toReview(StoreRequestDTO.ReveiwDTO request){
    return Review.builder()
        .title(request.getTitle())
        .score(request.getScore())
        .body(request.getBody())
        .build();
  }

  public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
    return StoreResponseDTO.CreateReviewResultDTO.builder()
        .reviewId(review.getId())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
    return StoreResponseDTO.ReviewPreViewDTO.builder()
        .ownerNickname(review.getMember().getName())
        .score(review.getScore())
        .createdAt(review.getCreatedAt().toLocalDate())
        .body(review.getBody())
        .build();
  }
  public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

    List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
        .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

    return StoreResponseDTO.ReviewPreViewListDTO.builder()
        .isLast(reviewList.isLast())
        .isFirst(reviewList.isFirst())
        .totalPage(reviewList.getTotalPages())
        .totalElements(reviewList.getTotalElements())
        .listSize(reviewPreViewDTOList.size())
        .reviewList(reviewPreViewDTOList)
        .build();
  }

  public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){
    return StoreResponseDTO.MissionPreViewDTO.builder()
        .reward(mission.getReward())
        .deadline(mission.getDeadline())
        .missionSpec(mission.getMissionSpec())
        .store(mission.getStore())
        .build();
  }
  public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){

    List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
        .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

    return StoreResponseDTO.MissionPreViewListDTO.builder()
        .isLast(missionList.isLast())
        .isFirst(missionList.isFirst())
        .totalPage(missionList.getTotalPages())
        .totalElements(missionList.getTotalElements())
        .listSize(missionPreViewDTOList.size())
        .missionList(missionPreViewDTOList)
        .build();
  }
}
