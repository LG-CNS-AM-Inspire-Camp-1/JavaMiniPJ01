package org.example;

import java.util.Scanner;

import org.example.KDHRestaurant.RestaurantManagement;
import org.example.movie.MovieOperator;
import org.example.EmailSubscribe.Subscriber;
import org.example.EmailSubscribe.SubscriptionException;
import org.example.EmailSubscribe.SubscriptionManager;
import org.example.musicManagement.MusicManagement;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processSubscriptionManagement(scanner);
        processMusicManagement(scanner);
        runMovieOperator(scanner);
        RestaurantManagementMethod(scanner); // 김대현->식당관리
    }

    // 구독 관리 메서드
    private static void processSubscriptionManagement(Scanner scanner) {
        SubscriptionManager manager = new SubscriptionManager();

        while (true) {
            System.out.println("\n===== 구독 관리 시스템 =====");
            System.out.println("1. 구독 추가");
            System.out.println("2. 구독 조회");
            System.out.println("3. 구독 정보 수정");
            System.out.println("4. 구독 삭제");
            System.out.println("5. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            try {
                switch (choice) {
                    case 1:
                        System.out.print("이메일 입력: ");
                        String email = scanner.nextLine();
                        System.out.print("이름 입력: ");
                        String name = scanner.nextLine();
                        manager.addSubscriber(name, email);
                        break;

                    case 2:
                        System.out.print("이메일 또는 이름 입력: ");
                        String info = scanner.nextLine();
                        manager.findSubscriber(info);
                        break;

                    case 3:
                        System.out.print("이메일 또는 이름 입력: ");
                        String updateUserInfo = scanner.nextLine();
                        Subscriber userInfo = manager.findSubscriber(updateUserInfo);
                        System.out.print("수정할 이메일 입력: ");
                        String updateEmail = scanner.nextLine();
                        System.out.print("수정할 이름 입력: ");
                        String updateName = scanner.nextLine();
                        manager.updateSubscriber(updateEmail, updateName, userInfo);

                    case 4:
                        System.out.print("삭제할 이메일 입력: ");
                        String deleteEmail = scanner.nextLine();
                        manager.deleteSubscriber(deleteEmail);
                        break;

                    case 5:
                        System.out.println("프로그램을 종료합니다.");
                        //scanner.close();
                        return;

                    default:
                        System.out.println("올바른 번호를 선택해주세요.");
                }
            } catch (SubscriptionException e) {
                System.out.println("[오류] " + e.getMessage());
            }
        }
    }

    // 음악 목록 관리 메서드
    public static void processMusicManagement(Scanner scanner) {
        MusicManagement music = new MusicManagement();

        // 사용자 입력
        while (true) {
            System.out.println("\n----- 음악 목록 관리 ----- ");
            System.out.println("1. 음악 등록");
            System.out.println("2. 전체 음악 출력");
            System.out.println("3. 음악 검색");
            System.out.println("4. 음악 삭제");
            System.out.println("5. 인기 음악 조회");
            System.out.println("6. 종료");
            System.out.print("메뉴 선택 : ");

            int choice = scanner.nextInt();
            scanner.nextLine();        // 개행문자 제거

            switch (choice) {
                case 1:
                    music.addMusic(scanner);
                    break;
                case 2:
                    music.printAllMusic();
                    break;
                case 3:
                    music.searchMusic(scanner);
                    break;
                case 4:
                    music.deleteMusic(scanner);
                    break;
                case 5:
                    music.mostPlayedMusic();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 영화목록 관리 메서드
    public static void runMovieOperator(Scanner scanner) {
        new MovieOperator(scanner);
    }

    // 식당 관리 메서드
    private static void RestaurantManagementMethod(Scanner scanner) {
        RestaurantManagement management = new RestaurantManagement();

        while (true) {
            System.out.println("\n=== 식당 관리 시스템 ===");
            System.out.println("1 → 식당 등록");
            System.out.println("2 → 전체 식당 목록 조회");
            System.out.println("3 → 카테고리별 목록 조회");
            System.out.println("4 → 식당 이름 검색(keyword)");
            System.out.println("5 → 식당 삭제");
            System.out.println("6 → 맛집 추천하기(1개만 가능)");
            System.out.println("7 → Real 맛집 보여주기(추천수 입력)");
            System.out.println("8 → 종료!");

            System.out.print("옵션을 고르세용~: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 그 엔터 잡아먹기

            switch (choice) {
                case 1:
                    management.addRestaurant(scanner);
                    break;
                case 2:
                    management.listAllRestaurants();
                    break;
                case 3:
                    System.out.print("카테고리 입력: ");
                    String category = scanner.nextLine();
                    management.listRestaurantsByCategory(category);
                    break;
                case 4:
                    System.out.print("식당 이름(keword) 입력: ");
                    String keyword = scanner.nextLine();
                    management.searchRestaurantsByKeyword(keyword);
                    break;
                case 5:
                    System.out.print("고유 번호를 입력해서 식당 지우기: ");
                    int idToRemove = scanner.nextInt();
                    scanner.nextLine();
                    management.removeRestaurantById(idToRemove);
                    break;
                case 6:
                    management.recommendRestaurant(scanner);
                    break;
                case 7:
                    System.out.print("입력한 추천수 이상의 목록을 보여드려요:  ");
                    int threshold = scanner.nextInt();
                    scanner.nextLine();
                    management.showTopRestaurants(threshold);
                    break;
                case 8:
                    System.out.println("이상, 김대현이었습니다. 감사합니다!");
//                    scanner.close();
                    return;
                default:
                    System.out.println("유효하지 않은 입력입니다. 다시 입력해주세요!");
            }
        }
    }
}

