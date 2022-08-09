package com.yk.reactive.test;

public class App {

    public static void main(String[] args) {

        MyPublisher publisher = new MyPublisher();
        // 구독자
        MySubscriber subscriber = new MySubscriber();
        publisher.subscribe(subscriber);

    }
}
