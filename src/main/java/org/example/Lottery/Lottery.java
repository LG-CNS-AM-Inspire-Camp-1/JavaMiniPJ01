package org.example.Lottery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@ToString
public class Lottery {
    private List<Integer> numbers;

    public static Lottery makeLottery() {
        // 5자리 숫자 랜덤으로 로또 만들기
        List<Integer> numbers = new Random().ints(1,6)
                .distinct()
                .limit(4)
                .boxed()
                .collect(Collectors.toList());
//        System.out.printf("자동 발급 번호: %s\n", numbers);
        return new Lottery(numbers);
    }
    public void printLottery() {
        System.out.printf("Lotto: %s\n", numbers);
    }
    public Integer getResult(List<Integer> prizeNumbers){
        long count = prizeNumbers.stream()
                .filter(number -> numbers.contains(number))
                .count();
//        System.out.printf("맞춘 번호 개수: %d개\n",count);
        return Math.toIntExact(count);
    }
}
