package org.example.movie;

import java.util.Scanner;

public class MovieOperator {
    private MovieManagement management;
    private Scanner scanner;

    public MovieOperator(Scanner scanner) {
        this.scanner = scanner;
        management = new MovieManagement();
        start();
    }

    private void start() {
        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // 입력 버퍼 비우기

            switch (choice) {
                case 1:
                    management.addMovie(scanner);
                    break;
                case 2:
                    management.listMovies();
                    break;
                case 3:
                    management.searchMovies(scanner);
                    break;
                case 4:
                    management.deleteMovie(scanner);
                    break;
                case 5:
                    management.mostViewedMovies();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n영화 관리 시스템:");
        System.out.println("1. 영화 등록");
        System.out.println("2. 전체 영화 출력");
        System.out.println("3. 영화 검색");
        System.out.println("4. 영화 삭제");
        System.out.println("5. 인기 영화 조회");
        System.out.println("6. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }
}
