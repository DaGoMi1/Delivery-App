package Delivery.BE.Service;

import Delivery.BE.DTO.ResponseStoreDTO;
import Delivery.BE.Enum.SortType;
import Delivery.BE.Exception.MissingRequiredDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SortService {
    public List<ResponseStoreDTO> sort(List<ResponseStoreDTO> storeDTOs, SortType type) {
        return switch (type) {
            case FAVORITE -> storeDTOs.stream() // 찜 기준 내림차순 정렬
                    .sorted(Comparator.comparing(ResponseStoreDTO::getFavoriteCount).reversed())
                    .collect(Collectors.toList());
            case CREATED_AT -> storeDTOs.stream() // 가게 생성 시간 기준 내림차순 정렬
                    .sorted(Comparator.comparing(ResponseStoreDTO::getCreatedAt).reversed())
                    .collect(Collectors.toList());
            case RATING -> storeDTOs.stream() // 별점 기준 내림차순 정렬
                    .sorted(Comparator.comparing((ResponseStoreDTO store) -> store.getRatingDTO().getRating()).reversed())
                    .collect(Collectors.toList());
            default -> throw new MissingRequiredDataException("타입이 올바르지 않습니다. type: " + type);
        };
    }
}
