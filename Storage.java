package com.luoleo.program1;

import java.util.LinkedList;

public class Storage {
    private final int MAX_NUM=10;
    private final LinkedList<Object> list =new LinkedList<>();

    public void produce(){
        synchronized (list) {
            while (list.size() +1> MAX_NUM) {
                try {
                    System.out.println("生产者"+Thread.currentThread()+"阻塞，库存为："+list.size());
                    list.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            list.notifyAll();
            System.out.println("生产者"+Thread.currentThread()+"生成一个物品，库存为："+list.size());
        }
    }

    public void consumer(){
        synchronized (list) {
            while (list.size()==0) {
                try {
                    System.out.println("消费者"+Thread.currentThread()+"阻塞，库存为："+list.size());
                    list.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.poll();
            list.notifyAll();
            System.out.println("消费者"+Thread.currentThread()+"消费一个物品，库存为："+list.size());
        }
    }
}
