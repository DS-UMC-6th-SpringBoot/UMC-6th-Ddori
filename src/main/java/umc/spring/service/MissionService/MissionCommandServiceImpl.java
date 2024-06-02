package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{

  private final MissionRepository missionRepository;
  private final StoreRepository storeRepository;

  @Transactional
  @Override
  public Mission createMission(MissionRequestDTO.CreateMission requestDTO) {
    Store store = storeRepository.findById(requestDTO.getStoreId()).get();
    Mission mission = MissionConverter.toMission(requestDTO);
    mission.setStore(store);
    return missionRepository.save(mission);
  }
}
