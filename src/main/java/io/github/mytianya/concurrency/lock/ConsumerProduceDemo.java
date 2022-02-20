package io.github.mytianya.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsumerProduceDemo {
    public static final Logger log= LoggerFactory.getLogger(ConsumerProduceDemo.class);
    public List<Long> list=new ArrayList<>();
    public void consumer(){
        synchronized (list){
            while (list.size()<1){
                try {
                    log.info("wait...");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("消费了：{}",list.remove(0));
            list.notifyAll();
        }
    }
    public void producer(){
        synchronized (list){
            while (list.size()>5){
                try {
                    log.info("wait...");
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            long tmp=System.nanoTime();
            log.info("生产了：{}",tmp);
            list.add(tmp);
            list.notifyAll();
        }
    }
}
