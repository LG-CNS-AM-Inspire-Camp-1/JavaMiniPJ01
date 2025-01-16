package org.example;

import org.example.EmailSubscribe.SubscriptionException;
import org.example.EmailSubscribe.SubscriptionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SubscriptionManager manager = new SubscriptionManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 구독 관리 시스템 =====");
            System.out.println("1. 구독 추가");
            System.out.println("2. 구독 조회");
            System.out.println("3. 구독 삭제");
            System.out.println("4. 종료");
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
                        String query = scanner.nextLine();
                        manager.findSubscriber(query);
                        break;

                    case 3:
                        System.out.print("삭제할 이메일 입력: ");
                        String deleteEmail = scanner.nextLine();
                        manager.deleteSubscriber(deleteEmail);
                        break;

                    case 4:
                        System.out.println("프로그램을 종료합니다.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("올바른 번호를 선택해주세요.");
                }
            } catch (SubscriptionException e) {
                System.out.println("[오류] " + e.getMessage());
            }
        }
    }
}