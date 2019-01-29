package com.dipsy.laa.common.util;

import java.util.*;

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

    private static Map<String, Integer> map = new HashMap<>();

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
        int i = 0;
//        label:
        while (i < 10000) {
            i = i + 1;
            luckDraw();
//            System.out.println("是否要挖密宝图：是（Y/y️）否（N/n）");
//            Scanner input = new Scanner(System.in);
//            if (input.hasNext()) {
//                String answer = input.next();
//                switch (answer.toUpperCase()) {
//                    case "Y":
//                        luckDraw();
//                        break;
//                    case "N":
//                        System.out.println("抽奖结束");
//                        break label;
//                    default:
//                        System.out.println("请输入Y/y或N/n");
//                        break;
//                }
//            }
        }
        System.out.println("挖宝一万次，收益如下-----------------------");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "            共" + entry.getValue() + "个");
        }
    }

    /**
     * 抽奖
     */
    private static void luckDraw() {
        double rate = Math.random();
        if (0 < rate && rate <= 0.0010) {
            System.out.println("恭喜获得梦游先生坐骑碎片*1");
            if (map.keySet().contains("梦游先生坐骑碎片*1")) {
                int count = map.get("梦游先生坐骑碎片*1") + 1;
                map.put("梦游先生坐骑碎片*1", count);
            } else {
                map.put("梦游先生坐骑碎片*1", 1);
            }
        } else if (0.0010 < rate && rate <= 0.0010 + 0.0012) {
            System.out.println("恭喜获得红色头饰*1");
            if (map.keySet().contains("红色头饰*1")) {
                int count = map.get("红色头饰*1") + 1;
                map.put("红色头饰*1", count);
            } else {
                map.put("红色头饰*1", 1);
            }
        } else if (0.0022 < rate && rate <= 0.0022 + 0.0100) {
            System.out.println("恭喜获得金色头饰*1");
            if (map.keySet().contains("金色头饰*1")) {
                int count = map.get("金色头饰*1") + 1;
                map.put("金色头饰*1", count);
            } else {
                map.put("金色头饰*1", 1);
            }
        } else if (0.0122 < rate && rate <= 0.0122 + 0.0283) {
            System.out.println("恭喜获得稻荷灵魂碎片*1");
            if (map.keySet().contains("稻荷灵魂碎片*1")) {
                int count = map.get("稻荷灵魂碎片*1") + 1;
                map.put("稻荷灵魂碎片*1", count);
            } else {
                map.put("稻荷灵魂碎片*1", 1);
            }
        } else if (0.0405 < rate && rate <= 0.0405 + 0.0283) {
            System.out.println("恭喜获得优妮灵魂碎片*1");
            if (map.keySet().contains("优妮灵魂碎片*1")) {
                int count = map.get("优妮灵魂碎片*1") + 1;
                map.put("优妮灵魂碎片*1", count);
            } else {
                map.put("优妮灵魂碎片*1", 1);
            }
        } else if (0.0688 < rate && rate <= 0.0688 + 0.0283) {
            System.out.println("恭喜获得戴斯特灵魂碎片*1");
            if (map.keySet().contains("戴斯特灵魂碎片*1")) {
                int count = map.get("戴斯特灵魂碎片*1") + 1;
                map.put("戴斯特灵魂碎片*1", count);
            } else {
                map.put("戴斯特灵魂碎片*1", 1);
            }
        } else if (0.0971 < rate && rate <= 0.0971 + 0.1501) {
            System.out.println("恭喜获得忘忧果*10");
            if (map.keySet().contains("忘忧果*10")) {
                int count = map.get("忘忧果*10") + 1;
                map.put("忘忧果*10", count);
            } else {
                map.put("忘忧果*10", 1);
            }
        } else if (0.2472 < rate && rate <= 0.2472 + 0.1501) {
            System.out.println("恭喜获得紫色头饰*1");
            if (map.keySet().contains("紫色头饰*1")) {
                int count = map.get("紫色头饰*1") + 1;
                map.put("紫色头饰*1", count);
            } else {
                map.put("紫色头饰*1", 1);
            }
        } else if (0.3973 < rate && rate <= 0.3973 + 0.2502) {
            System.out.println("恭喜获得捕蛋网*3");
            if (map.keySet().contains("捕蛋网*3")) {
                int count = map.get("捕蛋网*3") + 1;
                map.put("捕蛋网*3", count);
            } else {
                map.put("捕蛋网*3", 1);
            }
        } else if (0.6475 < rate && rate <= 0.6475 + 0.3525) {
            System.out.println("恭喜获得高级技能书*1");
        }
    }
}
