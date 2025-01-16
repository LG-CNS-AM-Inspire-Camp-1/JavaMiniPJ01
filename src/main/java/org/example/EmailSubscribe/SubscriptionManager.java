package org.example.EmailSubscribe;
import java.util.*;

public class SubscriptionManager {
    //구독자 리스트 생성
    private final List<Subscriber> subscriberList = new ArrayList<>();
    // userId에 반영될 자동 증가 변수
    private int nextUserId = 1;

    //구독 추가
    public void addSubscriber(String name, String email) {
        //이메일 중복 검사
        for (Subscriber subscriber : subscriberList) {
            if (subscriber.getEmail().equalsIgnoreCase(email)) {
                throw new SubscriptionException("이미 구독중인 이메일입니다.");
            }
        }

        Subscriber newSubscriber = new Subscriber(nextUserId++, name, email);
        subscriberList.add(newSubscriber);
        System.out.println("구독 해주셔서 감사드립니다!" + name + "님!");
    }

    //구독 조회
    public Subscriber findSubscriber(String info) {
        for (Subscriber subscriber : subscriberList) {
            if (subscriber.getEmail().equalsIgnoreCase(info) || subscriber.getName().equalsIgnoreCase(info)) {
                System.out.println("구독자 정보 : " + subscriber);
                return subscriber;
            }
        }
        throw new SubscriptionException("해당 정보와 일치하는 구독자를 찾을 수 없습니다.");
    }

    //구독 정보 수정
    public void updateSubscriber(String email, String name, Subscriber subscriber) {
        int userId = subscriber.getUserId();
        subscriberList.remove(subscriber);
        Subscriber updateSubscriber = new Subscriber(userId, name, email);
        subscriberList.add(updateSubscriber);
        System.out.println("구독 정보가 수정되었습니다.");
    }

    //구독 취소
    public void deleteSubscriber(String email) {
        Iterator<Subscriber> iterator = subscriberList.iterator();
        while (iterator.hasNext()) {
            Subscriber subscriber = iterator.next();
            if (subscriber.getEmail().equalsIgnoreCase(email)) {
                iterator.remove();
                System.out.println("구독이 해제되었습니다. 다시 뵙길 기대하겠습니다! :)");
                return;
            }
        }
        throw new SubscriptionException("해당 이메일의 구독자를 찾을 수 없습니다.");
    }
}
