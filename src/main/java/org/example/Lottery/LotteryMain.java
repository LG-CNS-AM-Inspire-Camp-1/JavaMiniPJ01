package org.example.Lottery;

import lombok.NoArgsConstructor;
import java.util.*;

@NoArgsConstructor
public class LotteryMain {

    public static void printBuyerMenu(Buyer buyer) {
//        clearScreen();
        System.out.printf("\n-------현재 사용자 : %s (잔액: %d)--------\n",buyer.getName(), buyer.getAccount());
        System.out.println("1. 로또 구매");
        System.out.println("2. 내 로또 조회");
        System.out.println("3. 잔액 조회");
        System.out.println("4. 로또 결과 전체 조회");
        System.out.println("5. 전체 사용자 조회");
        System.out.println("6. 사용자 변경");
        System.out.println("7. 사용자 추가");
        System.out.println("8. 사용자 삭제");
        System.out.println("9. 종료");
        System.out.print(">입력: ");
    }
    LotteryManagement lotteryManagement = new LotteryManagement();
    List<Buyer> buyers = new ArrayList<>(
            List.of(
        new Buyer(1,"홍길동", 10000),
        new Buyer(2,"홍길서", 10000),
        new Buyer(3,"홍길남", 10000),
        new Buyer(4,"홍길북", 10000)));

    public void LotteryMain(Scanner scanner) {
        Buyer currentBuyer = buyers.get(0);
        while(true){

            printBuyerMenu(currentBuyer);
            try{
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("구입할 로또 수량을 입력해주세요(장당 1000원): ");
                        int amount = scanner.nextInt();
                        if (currentBuyer.getAccount() < amount * 1000) {
                            System.out.printf("잔액보다 많은 금액을 입력하였습니다. 주문은 취소됩니다.\n 현재 잔액: %d\n", currentBuyer.getAccount());
                            break;
                        }
                        List<Lottery> newLottery = lotteryManagement.sellLottery(amount);
                        currentBuyer.addLottery(newLottery);
                        currentBuyer.setAccount(currentBuyer.getAccount() - amount * 1000);
                        break;
                    case 2:
                        currentBuyer.lotteriesInfo();
                        break;
                    case 4:
                        System.out.println("[추첨 결과]");
                        lotteryManagement.choiceLottery();
                        lotteryManagement.printLottery();
                        lotteryManagement.finishLottery(buyers);
                        break;
                    case 3:
                        currentBuyer.accountInfo();
                        break;
                    case 5:
                        System.out.println("--------전체 사용자-------");
                        buyers.forEach(buyer -> System.out.printf("id: %s, 이름: %s, 잔액: %d\n", buyer.getId(), buyer.getName(), buyer.getAccount()));
                        break;
                    case 6:
                        System.out.print("변경할 사용자 id를 입력해주세요 : ");
                        int changeId = scanner.nextInt();
                        Optional<Buyer> buyerToChange = buyers.stream()
                                .filter(buyer -> buyer.getId() == changeId) // ID로 사용자 검색
                                .findFirst(); // 첫 번째 결과를 가져옴
                        if (buyerToChange.isEmpty()) {
                            System.out.println("존재하지 않은 id 입니다.");
                            break;
                        }
                        currentBuyer = buyerToChange.get();
                        break;
                    case 7:
                        System.out.println("생성할 사용자 정보를 입력하세요.");
                        System.out.print("이름: ");
                        String name = scanner.next();
                        System.out.print("초기 액수: ");
                        int account = scanner.nextInt();

                        Optional<Buyer> maxBuyer = buyers.stream()
                                .max(Comparator.comparingInt(Buyer::getId));
                        Buyer newBuyer;
                        if (maxBuyer.isPresent()) {
                            newBuyer = new Buyer(maxBuyer.get().getId() + 1, name, account);
                        } else {
                            newBuyer = new Buyer(1, name, account);
                        }
                        buyers.add(newBuyer);
                        break;
                    case 8:
                        System.out.print("제거할 사용자 id를 입력해주세요 : ");
                        int deleteId = scanner.nextInt();
                        Optional<Buyer> buyerToRemove = buyers.stream()
                                .filter(buyer -> buyer.getId() == deleteId) // ID로 사용자 검색
                                .findFirst(); // 첫 번째 결과를 가져옴
                        if (buyerToRemove.isEmpty()) {
                            System.out.println("올바르지 않은 id 입니다.");
                            break;
                        }
                        buyers.remove(buyerToRemove.get());
                        System.out.println("해당 유저는 제거 되엇습니다.");
                        break;
                    case 9:
                        System.out.println("종료합니다.");
                        return;
                    default:
                        System.out.println("유효하지 않은 메뉴입니다. 다시 입력해주세요.");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                scanner.nextLine();
            }

        }
    }

}
