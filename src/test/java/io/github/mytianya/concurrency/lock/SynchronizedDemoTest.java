package io.github.mytianya.concurrency.lock;

import org.junit.Test;


public class SynchronizedDemoTest {

    @Test
    public void lockClass() throws InterruptedException {
        Thread t1=new Thread(()->{
            SynchronizedDemo.lockClass();
        });
        t1.start();
        Thread t2=new Thread(()->{
            SynchronizedDemo.lockClass();
        });
        t2.start();
        Thread.sleep(1000);
    }

    @Test
    public void lockInstance() {
    }

    @Test
    public void lockThis() throws InterruptedException {
        SynchronizedDemo demo=new SynchronizedDemo();
        Thread t1=new Thread(()->{
            for (int i=0;i<10;i++){
                demo.lockThis();

            }
        });
        t1.start();
        Thread t2=new Thread(()->{
            for (int i=0;i<10;i++){
                demo.lockThis();

            }
        });
        t2.start();
        Thread.sleep(1000000);
    }
}