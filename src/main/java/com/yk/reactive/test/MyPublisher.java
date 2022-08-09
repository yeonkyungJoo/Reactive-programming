package com.yk.reactive.test;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.Iterator;

public class MyPublisher implements Publisher<Integer> {

    // Publisher가 가지고 있는 정보
    Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().iterator();

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {

        System.out.println("1. Subscriber가 구독 요청");
        System.out.println("2. Publisher가 구독 정보 생성");
        MySubscription subscription = new MySubscription(subscriber, iterator);
        System.out.println("3. Publisher가 구독 정보 생성 완료");
        // 구독자에게 구독 정보 전달
        subscriber.onSubscribe(subscription);
    }
}
