package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
  private final ReviewCommandService reviewCommandService;

  @PostMapping("/stores")
  public ApiResponse<?> createReview(@Valid @RequestBody ReviewRequestDTO.CreateReviewDTO requestDTO) {
    Review review = reviewCommandService.createReview(requestDTO);
    return ApiResponse.onSuccess(ReviewConverter.createReviewResult(review));
  }
}
