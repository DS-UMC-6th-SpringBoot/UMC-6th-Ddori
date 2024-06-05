package umc.spring.service.StoreService;

import java.util.Optional;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface StoreQueryService {

  Optional<Store> findStore(Long id);

  Page<Review> getReviewList(Long StoreId, Integer page);
}