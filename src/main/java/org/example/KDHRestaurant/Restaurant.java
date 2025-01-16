package org.example.KDHRestaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString

public class Restaurant {
    // 필드 선언
    private int id; // 등록 순서 (고유 값)
    private String name; // 식당 이름
    private String category; // 음식 카테고리
    private String signatureDish; // 대표 음식
    private String priceRange; // 가격대
    private int recommendationCount; // 추천수

    @Override
    public String toString() {
        return String.format(
                "식당 고유 ID: %d, 식당 이름: %s, 카테고리: %s, 대표 음식: %s, 가격: %s, 추천 수: %d",
                id, name, category, signatureDish, priceRange, recommendationCount
        );
    }


}
