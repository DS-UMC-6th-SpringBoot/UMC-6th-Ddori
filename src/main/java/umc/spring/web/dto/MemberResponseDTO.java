package umc.spring.web.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.StoreResponseDTO.MissionPreViewDTO;
import umc.spring.web.dto.StoreResponseDTO.ReviewPreViewDTO;

public class MemberResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class JoinResultDTO{
    Long memberId;
    LocalDateTime createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewListDTO{
    List<MemberResponseDTO.ReviewPreViewDTO> reviewList;
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
  public static class ReviewPreViewDTO{
    String ownerNickname;
    Float score;
    String body;
    LocalDate createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MemberMissionPreViewListDTO {
    List<MemberResponseDTO.MemberMissionPreViewDTO> missionList;
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
  public static class MemberMissionPreViewDTO {
    String mission;
    String member;
    MissionStatus status;
  }

}