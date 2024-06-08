package umc.spring.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Store;

public class StoreResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewListDTO {
    List<ReviewPreViewDTO> reviewList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewDTO {
    String ownerNickname;
    Float score;
    String body;
    LocalDate createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateReviewResultDTO {
    Long reviewId;
    LocalDateTime createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MissionPreViewListDTO {
    List<MissionPreViewDTO> missionList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MissionPreViewDTO {
    Integer reward;
    LocalDate deadline;
    String missionSpec;
    Store store;
  }

}
