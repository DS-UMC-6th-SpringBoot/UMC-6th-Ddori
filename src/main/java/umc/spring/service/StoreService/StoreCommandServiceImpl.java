package umc.spring.service.StoreService;


import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

  private final StoreRepository storeRepository;
  private final RegionRepository regionRepository;

  @Transactional
  @Override
  public Store createStore(StoreRequestDTO.CreateStoreDTO requestDTO) {
    Region region = regionRepository.findById(requestDTO.getRegionId()).get();
    Store store = StoreConverter.toStore(requestDTO);
    store.setRegion(region);
    return storeRepository.save(store);
  }
}
