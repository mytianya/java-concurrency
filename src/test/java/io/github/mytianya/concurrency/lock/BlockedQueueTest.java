package io.github.mytianya.concurrency.lock;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockedQueueTest {
    @Test
    public void testBlockQueue(){
        BlockedQueue blockedQueue=new BlockedQueue();
        Thread t=new Thread(()->{
            while (true){
                blockedQueue.put();
            }
        });
        t.setName("put-thread");
        Thread t1=new Thread(()->{
            while (true){
               blockedQueue.take();
            }
        });
        t1.setName("take-thread");
        t.start();t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}