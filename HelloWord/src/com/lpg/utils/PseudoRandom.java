package com.lpg.utils;

/**
 * @author lijr
 * @date 2020-10-19 20:47
 */
public class PseudoRandom {

    public PseudoRandom(long seed) {
        this.seed = seed;
    }

    private long seed;

    /**
     * 区间随机
     *
     * @param min
     * @param max
     * @return
     */
    public int random(int min, int max) {
        if (max <= min) {
            return min;
        }
        return min + random(max - min + 1);
    }

    /**
     * 伪随机种子，基于线性同余生成器
     *
     * @param bound
     * @return [0, bound)
     */
    public int random(int bound) {
        this.seed = (this.seed * 9301 + 49297) % 233280;
        double rnd = this.seed / 233280.0;
        int result = (int) (rnd * bound);
        return result;
    }
}
