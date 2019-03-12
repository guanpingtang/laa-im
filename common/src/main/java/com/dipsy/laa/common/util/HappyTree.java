package com.dipsy.laa.common.util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 开心树抽奖程序
 *
 * @author tgp
 */
public class HappyTree {

    /**
     * 奖品等级和奖品
     */
    private static Map<Integer, String> JACKPOT;

    /**
     * 奖品等级和概率
     */
    private static Map<Integer, Double> CHANCE;

    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 设置奖池
     */
    public static void initJackpot() {
        if (JACKPOT == null) {
            JACKPOT = new HashMap<>();
        }
        if (CHANCE == null) {
            CHANCE = new HashMap<>();
        }
        JACKPOT.put(1, "梦游先生坐骑碎片*1");
        JACKPOT.put(2, "红色头饰*1");
        JACKPOT.put(3, "金色头饰*1");
        JACKPOT.put(4, "稻荷灵魂碎片*1");
        JACKPOT.put(5, "优妮灵魂碎片*1");
        JACKPOT.put(6, "戴斯特灵魂碎片*1");
        JACKPOT.put(7, "忘忧果*10");
        JACKPOT.put(8, "紫色头饰*1");
        JACKPOT.put(9, "捕蛋网*3");
        JACKPOT.put(10, "高级技能书*1");

        CHANCE.put(1, 0.0010);
        CHANCE.put(2, 0.0012);
        CHANCE.put(3, 0.0100);
        CHANCE.put(4, 0.0283);
        CHANCE.put(5, 0.0283);
        CHANCE.put(6, 0.0283);
        CHANCE.put(7, 0.1501);
        CHANCE.put(8, 0.1501);
        CHANCE.put(9, 0.2502);
        CHANCE.put(10, 0.3525);
    }

    public static void main(String[] args) {
        initJackpot();
        label:
        while (true) {
            System.out.println("是否要抽奖：是（Y/y️）否（N/n）");
            Scanner input = new Scanner(System.in);
            if (input.hasNext()) {
                String answer = input.next();
                switch (answer.toUpperCase()) {
                    case "Y":
                        luckDraw();
                        break;
                    case "N":
                        System.out.println("抽奖结束");
                        break label;
                    default:
                        System.out.println("请输入Y/y或N/n");
                        break;
                }
            }
        }
    }

    /**
     * 抽奖
     */
    private static void luckDraw() {
        double rate = Math.random();
        if (0 < rate && rate <= 0.0010) {
            System.out.println("恭喜获得梦游先生坐骑碎片*1");
        } else if (0.0010 < rate && rate <= 0.0010 + 0.0012) {
            System.out.println("恭喜获得红色头饰*1");
        } else if (0.0022 < rate && rate <= 0.0022 + 0.0100) {
            System.out.println("恭喜获得金色头饰*1");
        } else if (0.0122 < rate && rate <= 0.0122 + 0.0283) {
            System.out.println("恭喜获得稻荷灵魂碎片*1");
        } else if (0.0405 < rate && rate <= 0.0405 + 0.0283) {
            System.out.println("恭喜获得优妮灵魂碎片*1");
        } else if (0.0688 < rate && rate <= 0.0688 + 0.0283) {
            System.out.println("恭喜获得戴斯特灵魂碎片*1");
        } else if (0.0971 < rate && rate <= 0.0971 + 0.1501) {
            System.out.println("恭喜获得忘忧果*10");
        } else if (0.2472 < rate && rate <= 0.2472 + 0.1501) {
            System.out.println("恭喜获得紫色头饰*1");
        } else if (0.3973 < rate && rate <= 0.3973 + 0.2502) {
            System.out.println("恭喜获得捕蛋网*3");
        } else if (0.6475 < rate && rate <= 0.6475 + 0.3525) {
            System.out.println("恭喜获得高级技能书*1");
        }
    }

    public int next() {
        while (true) {
            int i = count.get();
            int next = i + 1;
            if (count.compareAndSet(i, next)) {
                return next;
            }
        }
    }

}
