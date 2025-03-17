package Delivery.BE.Service;

import Delivery.BE.DTO.StoreDTO;
import Delivery.BE.Domain.Store;
import Delivery.BE.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void createStore(StoreDTO storeDTO) {

        Store store = Store.builder()
                .name(storeDTO.getName())
                .address(storeDTO.getAddress())
                .phone(storeDTO.getPhone())
                .description(storeDTO.getDescription())
                .openingHours(storeDTO.getOpeningHours())
                .status(Store.Status.CLOSED)
                .rating(0.0)
                .build();

        storeRepository.save(store);
    }

}