package com.dipsy.laa.common.util;

public class ReorderingDemo {
    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            x = y = a = b = 0;
            Thread one = new Thread("A") {
                public void run() {
                    a = 1;
                    x = b;
                }
            };
            Thread two = new Thread("B") {
                public void run() {
                    b = 1;
                    y = a;
                }
            };
            one.start();
            two.start();

            one.join();
            two.join();
            System.out.println(Thread.currentThread().getName() + ":" + x + " " + y);
        }
    }
}