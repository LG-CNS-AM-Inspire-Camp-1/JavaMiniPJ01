package org.example.KDHRestaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantManagement {
    private List<Restaurant> restaurants = new ArrayList<>(); // 식당 리스트


    // 1 → 식당 등록
    public void addRestaurant(Scanner scanner) {
        System.out.print("고유 ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // ID 중복 확인
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                System.out.println("중복된 고유 ID입니다. 메뉴로 돌아갑니다.");
                return;
            }
        }

        System.out.print("식당 이름: ");
        String name = scanner.nextLine();

        System.out.print("음식 카테고리: ");
        String category = scanner.nextLine();

        System.out.print("대표 음식: ");
        String signatureDish = scanner.nextLine();

        System.out.print("가격: ");
        String priceRange = scanner.nextLine();

        Restaurant restaurant = new Restaurant(id, name, category, signatureDish, priceRange, 0);
        restaurants.add(restaurant);
        System.out.println("등록된 음식점 정보 -> " + restaurant);
    }

    // 2 → 전체 식당 목록 조회
    public void listAllRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("등록된 식당이 없습니다.");
        }
        restaurants.forEach(restaurant -> System.out.println(restaurant));
    }

    // 3 → 음식 카테고리별 목록 조회
    public void listRestaurantsByCategory(String category) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCategory().equalsIgnoreCase(category)) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("입력하신 카테고리의 식당이 없습니다!: " + category);
        }
    }

    // 4 → 식당 이름에 keyword가 포함되어 있는지 검색
    public void searchRestaurantsByKeyword(String keyword) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("입력하신 키워드에 해당하는 식당이 없습니다!: " + keyword);
        }
    }

    // 5 → 식당 삭제
    public void removeRestaurantById(int id) {
        Restaurant toRemove = null;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                toRemove = restaurant;
                break;
            }
        }
        if (toRemove != null) {
            restaurants.remove(toRemove);
            System.out.println("식당이 삭제되었습니다.: " + toRemove.getName());
        } else {
            System.out.println("ID에 맞는 식당이 없습니다!: " + id);
        }
    }

    // 6 → 맛집 추천하기
    public void recommendRestaurant(Scanner scanner) {
        if (restaurants.isEmpty()) {
            System.out.println("아직까지 추천받은 맛집이 없어요!");
            return;
        }

        System.out.println("기존 레스토랑 (추천은 하나만 가능합니다):");
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }

        System.out.print("추천하고 싶은 레스토랑의 ID를 입력하세요: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                int newCount = restaurant.getRecommendationCount() + 1;
                restaurants.set(restaurants.indexOf(restaurant), new Restaurant(
                        restaurant.getId(), restaurant.getName(), restaurant.getCategory(),
                        restaurant.getSignatureDish(), restaurant.getPriceRange(), newCount));
                System.out.println("추천한 식당: " +
                        restaurant.getName() +
                        " | 종합 추천수: " + newCount);
                return;
            }
        }
        System.out.println("ID에 맞는 식당이 없습니다!: " + id);
    }

    // 7 → Real 찐 맛집 보여주기
    public void showTopRestaurants(int threshold) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRecommendationCount() >= threshold) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.printf("%d 이상의 추천수를 가진 레스토랑이 없습니다. \n",threshold);
        }
    }
}
