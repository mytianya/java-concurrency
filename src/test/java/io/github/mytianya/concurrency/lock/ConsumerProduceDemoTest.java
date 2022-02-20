package io.github.mytianya.concurrency.lock;

import org.junit.Test;

public class ConsumerProduceDemoTest {
    @Test
    public void testConsumerProduce() throws InterruptedException {
        ConsumerProduceDemo consumerProduceDemo =new ConsumerProduceDemo();
        Thread t=new Thread(()->{
            while (true){
                consumerProduceDemo.producer();
            }
        });
        t.setName("生产者");
        Thread t1=new Thread(()->{
            while (true){
                consumerProduceDemo.consumer();
            }
        });
        t1.setName("消费者");
        t.start();
        t1.start();
        Thread.sleep(100);
    }
}