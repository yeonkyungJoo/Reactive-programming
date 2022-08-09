package com.yk.reactive.test;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<Integer> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("4. 구독 정보 전달 받음");
        this.subscription = subscription;

        System.out.println("5. 구독 정보 request");
        subscription.request(1); // 백프레셔 - 소비자가 한번에 처리할 수 있는 개수만큼 요청
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext : " + integer);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError");
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
