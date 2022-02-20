package io.github.mytianya.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedDemo {
    public static Logger logger= LoggerFactory.getLogger(SynchronizedDemo.class);
    public static synchronized void lockClass(){
        logger.info("synchronized关键字修饰static方法锁是对象class");
    }
    public  void lockClass1(){
        synchronized (SynchronizedDemo.class){
            logger.info("synchronized关键字修饰static方法锁是对象class");
        }
    }
    public synchronized void lockInstance(){
        logger.info("synchronized关键字修饰对象方法此时的锁是对象实例");

    }
    public void lockThis(){
        synchronized (this){
            logger.info("synchronized关键字this方法此时的锁是此对象实例");
            try {
                this.notifyAll();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
