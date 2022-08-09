package com.yk.reactive.test;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독 정보
public class MySubscription implements Subscription {

    // - 구독자
    // - 어떤 데이터를 구독할 것인가
    private Subscriber subscriber;
    private Iterator<Integer> iterator;

    public MySubscription(Subscriber subscriber, Iterator<Integer> iterator) {
        this.subscriber = subscriber;
        this.iterator = iterator;
    }

    @Override
    public void request(long l) {

        while (l > 0) {
            if(iterator.hasNext()) {
                subscriber.onNext(iterator.next());
            } else {
                subscriber.onComplete();
                break;
            }
            l--;
        }
    }

    @Override
    public void cancel() {

    }
}
