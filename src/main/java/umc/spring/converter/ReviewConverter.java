package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public class ReviewConverter {

  public static Review toReview(ReviewRequestDTO.CreateReviewDTO request) {
    return Review.builder()
        .title(request.getBody())
        .score(request.getScore())
        .build();
  }

  public static ReviewResponseDTO.CreateReviewDto createReviewResult(Review review) {
    return ReviewResponseDTO.CreateReviewDto.builder()
        .reviewId(review.getId())
        .createAt(review.getCreatedAt())
        .build();
  }
}