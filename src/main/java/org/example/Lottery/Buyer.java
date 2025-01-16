package org.example.Lottery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class Buyer {
    private final Integer id;
    private final String name;
    private int account;
    private List<Lottery> lotteries = new ArrayList<>();

    public Buyer(Integer id, String name, int account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    // 로또 추가
    public void addLottery(List<Lottery> lotteries) {
        this.lotteries.addAll(lotteries);
        System.out.println("로또를 구매하였습니다. 구매한 로또는 다음과 같습니다.");
        lotteries.forEach(Lottery::printLottery);
    }

    public void lotteriesInfo() {
        System.out.println("이번 회차 누적 구매한 로또는 다음과 같습니다.");
        this.lotteries.forEach(Lottery::printLottery);
    }

    public void accountInfo(){
        System.out.printf("현재 잔액은 %d원입니다\n", this.account);
    }

    public void addPrize(int prize){
        this.account += prize;
        // 로또 용지 비우기
        this.lotteries.clear();
    }
    // 당첨금 조회
    //


}
