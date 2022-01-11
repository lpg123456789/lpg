package com.lpg.utils;

/**
 *
 * @author redback
 * @version 1.00
 * @time 2020-10-19 14:15
 */
public class RandomValue {

    /**
     * 随机种子
     */
    private long seed;

    public RandomValue() {
    }

    public RandomValue(long seed) {
        this.seed = seed;
    }


    /**
     * 随机区间范围 [min, max]
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @return 区间内的值[min, max]
     */
    public int random(int min, int max) {
        if (min >= max) {
            return min;
        }
        this.seed = Math.abs((this.seed * 9301 + 49297) % 233280);
        double rnd = this.seed / 233280.0;
        double result = Math.floor(min + rnd * (max - min + 1));
        return (int) result;
    }

    /**
     * int[][] 二维数组，每个数组也只有两个元素[目标值，概率]
     * @param weightData 带权重得的数据
     * @return targetValue
     */
    public int randomByWeight(int[][] weightData){
        if (weightData.length == 0) {
            return 0;
        }
        int total = 0;
        for (int[] weight : weightData) {
            total += weight[1];
        }
        if (total < 1) {
            return 0;
        }
        //从 1 开始
        int randomValue = random(1, total);
        int count = 0;
        for (int[] weight : weightData) {
            int probability = weight[1];
            if (probability < 1) {
                continue;
            }
            count += probability;
            if (count >= randomValue) {
                return weight[0];
            }
        }
        return 0;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }


}
