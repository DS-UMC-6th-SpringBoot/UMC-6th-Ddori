package umc.spring.converter;

import java.util.ArrayList;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public class StoreConverter {

  public static Store toStore(StoreRequestDTO.CreateStoreDTO requestDTO) {
    return Store.builder()
        .name(requestDTO.getName())
        .address(requestDTO.getAddress())
        .missionList(new ArrayList<>())
        .reviewList(new ArrayList<>())
        .build();
  }

  public static StoreResponseDTO.CreateResultDto toCreateStoreDTO(Store store) {
    return StoreResponseDTO.CreateResultDto.builder()
        .storeId(store.getId())
        .createAt(store.getCreatedAt())
        .build();
  }
}