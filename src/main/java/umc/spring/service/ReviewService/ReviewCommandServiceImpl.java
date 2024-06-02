package umc.spring.service.ReviewService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

  private final ReviewRepository reviewRepository;
  private final MemberRepository memberRepository;
  private final StoreRepository storeRepository;

  @Transactional
  @Override
  public Review createReview(ReviewRequestDTO.CreateReviewDTO requestDTO) {
    Store store = storeRepository.findById(requestDTO.getStoreId()).get();
    Member member = memberRepository.findById(1L).get();
    Review review = ReviewConverter.toReview(requestDTO);
    Double avgScore = calculateAverageRating(review, store);
    store.updateScore(avgScore);
    review.setMember(member);
    review.setStore(store);
    return reviewRepository.save(review);
  }

  private Double calculateAverageRating(Review review, Store store) {
    List<Review> reviews = reviewRepository.findAllByStore(store).get();
    return (store.getScore() * reviews.size() + review.getScore()) / (reviews.size()+1);
  }
}
