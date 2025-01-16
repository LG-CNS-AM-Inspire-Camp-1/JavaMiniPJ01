package org.example.Lottery;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LotteryManagement {
    // 로또 판매
    // 로또 당첨금 지급
    // 로또 당첨 번호 추첨
    private int account = 0; // 로또 수익금 저장
    private List<Integer> lottery = new ArrayList<>(); // 당첨 번호 저장

    public List<Lottery> sellLottery(int tickets) {
        List<Lottery> lotteries = new ArrayList<Lottery>();
        for (int i = 0; i < tickets; i++) {
            lotteries.add(Lottery.makeLottery());
        }
        account += tickets * 1000;
        return lotteries;
    }

    public void choiceLottery(){
        this.lottery = Lottery.makeLottery().getNumbers();
    }

    public void printLottery(){
        System.out.printf("추첨된 로또 번호는 %s입니다.\n",this.lottery);
    }

    public void finishLottery(List<Buyer> buyers) {

        // 1등 당첨자 계산
        long firstPrizeWinners = buyers.stream()
                .flatMap(buyer -> buyer.getLotteries().stream())
                .filter(l -> l.getResult(this.lottery) == 4)  // 5개 번호 일치
                .count();

        // 당첨금 계산
        if (firstPrizeWinners == 0) {
            System.out.printf("당첨자가 없습니다. 당첨금은 누적됩니다. 누적액: %d\n",this.account);
        }else{
            int firstPrize = (int) (this.account / firstPrizeWinners);

            int finalFirstPrize = (int) (Math.round(firstPrize / 1000.0) * 1000);;

            this.account -= finalFirstPrize;
            buyers.forEach(buyer -> {
                List<Integer> result = buyer.getLotteries()
                        .stream().map(l -> l.getResult(this.lottery))
                        .collect(Collectors.toList());
                int count = (int) result.stream().filter(l -> l == 4).count();
                buyer.addPrize( count * finalFirstPrize);
                System.out.printf("%s의 1등 당첨 횟수: %d, 현재 잔액: %d\n",buyer.getName(), count, buyer.getAccount());
            });
        }

    }

    private int calculatePrizeMoney(int matchedCount, int firstPrize) {
        return matchedCount * firstPrize;
    }
}
