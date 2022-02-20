package io.github.mytianya.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue {
    public static Logger logger= LoggerFactory.getLogger(BlockedQueue.class);
    ReentrantLock lock=new ReentrantLock();
    //队列满了，向队列添加数据需要等待
    Condition full=lock.newCondition();
    List<Long> queue=new ArrayList<>(6);
    //队列空了，从队列获取数据需要等待
    Condition empty=lock.newCondition();
    public Long take(){
        try {
            lock.lock();
            while (queue.size()==0){
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Long tmp=queue.remove(0);
            full.signalAll();
            logger.info("移除队列数据{}",tmp);
            return tmp;
        }finally {
            lock.unlock();
        }
    }
    public void put(){
        try {
            lock.lock();
            while (queue.size()>=6){
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long tmp=System.currentTimeMillis();
            queue.add(tmp);
            logger.info("添加队列数据{}",tmp);
            empty.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
