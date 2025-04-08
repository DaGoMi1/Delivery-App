package Delivery.BE.Enum;

import Delivery.BE.Exception.MissingRequiredDataException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SortType {
    FAVORITE,
    CREATED_AT,
    RATING;

    public static SortType fromString(String type) {
        return Arrays.stream(SortType.values())
                .filter(t -> t.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new MissingRequiredDataException("올바르지 않은 정렬 타입입니다: " + type));
    }
}
