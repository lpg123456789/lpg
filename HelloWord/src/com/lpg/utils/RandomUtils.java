package com.lpg.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import org.apache.log4j.Logger;

public class RandomUtils {
    /**
     * Logger for this class
     */
    protected static Logger logger = Logger.getLogger(RandomUtils.class);

    private static Random random = new Random();

    private static int[] randomArray = new int[] { 15, 83, 73, 87, 32, 51, 86, 16, 57, 4, 5, 3, 14, 85, 59, 28, 42, 96, 78, 54, 37, 67, 50, 52, 23, 90, 66, 44,
            25, 99, 71, 47, 89, 48, 61, 2, 13, 27, 92, 29, 1, 34, 94, 39, 62, 20, 60, 72, 11, 93, 31, 68, 69, 10, 70, 18, 91, 7, 12, 80, 41, 9, 35, 64, 6, 84,
            76, 30, 79, 19, 45, 55, 46, 65, 98, 40, 26, 82, 43, 33, 49, 81, 58, 21, 17, 24, 22, 36, 38, 97, 88, 77, 74, 63, 95, 75, 56, 53, 8, 100 };

    /**
     * 万分比
     */
    private static final int MAX_PROBABILITY = 10000;
    
    /**
     * 百分比
     */
    private static final int MAX_PROBABILITY_BAI = 100;
    /**
     * 不包含最大值<br>
     * [0,max)
     * 
     * @param max
     * @return
     */
    public static int random(int max) {
        return random.nextInt(max);
    }

    /**
     * 包含最大最小值
     * 
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        if (max - min <= 0) {
            return min;
        }
        return min + random.nextInt(max - min + 1);
    }

    /**
     * 根据几率是否生成 规则:大于等于最小 小于等于最大 的一个万分比概率
     * 
     * @param max 最大的值
     * @param min 最小的值
     * @return
     */
    public static boolean isGenerate(int max, int min) {
        int ranNum = random.nextInt(MAX_PROBABILITY);
        if (ranNum >= min && ranNum <= max) {
            return true;
        }
        return false;
    }

    /**
     * 根据几率是否生成 万分比
     * 
     * @param probablility
     * @return
     */
    public static boolean isGenerate(int probablility) {
        return probablility >= random.nextInt(MAX_PROBABILITY);
    }

    /**
     * 根据几率是否生成 百分比
     * 
     * @param probablility
     * @return
     */
    public static boolean isGenerateBai(int probablility) {
        return probablility >= random.nextInt(MAX_PROBABILITY_BAI);
    }

    /**
     * 按帧取随机数(包含最大值, 包含最小值0)
     * 
     * @param mapFrame     地图帧数
     * @param addtionFrame 附加帧数
     * @param maxCount     最大数量
     * @return
     */
    public static int getRandomResult(int mapFrame, int addtionFrame, int maxCount) {
        return getRandomResult(mapFrame, addtionFrame, 0, maxCount);
    }

    /**
     * 按帧取随机数(包含最大值, 包含最小值)
     * 
     * @param mapFrame     地图帧数
     * @param addtionFrame 附加帧数
     * @param minCount     最小数量
     * @param maxCount     最大数量
     * @return
     */
    public static int getRandomResult(int mapFrame, int addtionFrame, int minCount, int maxCount) {
        int frame = (mapFrame + addtionFrame) % randomArray.length;
        // 获取随机值
        int random = (int) Math.ceil((double) randomArray[frame] / 100 * (maxCount - minCount + 1));

        return minCount + random - 1;
    }

    public static int[] getRandomArray() {
        return randomArray;
    }

    public static <T> void shuffle(List<T> list) {
        int size = list.size();
        int value;
        T temp;
        for (int index = 0; index < size; index++) {
            value = index + RandomUtils.random(0, 32767) % (size - index);

            temp = list.get(index);
            list.set(index, list.get(value));
            list.set(value, temp);
        }
    }

    public static void shuffle(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            int tmp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = tmp;
        }
    }

    /**
     * 根据Map里的权重随机出<T>
     * 
     * @param map <T, 权重>
     */
    public static <T> T randomMapIdByWeight(Map<T, Integer> map) {
        int total = 0;
        for (Integer num : map.values()) {
            total += num;
        }

        int random = random(1, total);

        int i = 0;
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            i += entry.getValue();
            if (i >= random) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 根据Map里的权重随机出<T>
     *
     * @param map <T, 权重>
     */
    public static <T> T randomMapIdByWeight(Map<T, Integer> map, Random random) {
        int total = 0;
        for (Integer num : map.values()) {
            total += num;
        }

        int randomNum = random.nextInt(total);

        int i = 0;
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            i += entry.getValue();
            if (i > randomNum) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 根据Map里的权重随机出<T>
     * 
     * @param map <权重，T>
     */
    public static <T> T randomIdByWeight(Map<Integer, T> map) {
        int total = 0;
        for (Integer num : map.keySet()) {
            total += num;
        }

        int random = random(total + 1);

        int i = 0;
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            i += entry.getKey();
            if (i >= random) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 从一组集合中 根据权重随机取出1个对象
     * 
     * @param randomCollection
     * @param calcWeightFunction 计算列表元素权重的方法
     * @return
     */
    public static <T> Optional<T> randomByWeight(Collection<T> randomCollection, Function<T, Integer> calcWeightFunction) {
        if (randomCollection == null || randomCollection.isEmpty() || calcWeightFunction == null) {
            return Optional.empty();
        }
        int[] weightArray = new int[randomCollection.size()];
        int totalWeight = 0;
        int index = 0;
        for (T tmp : randomCollection) {
            int tmpWeight = calcWeightFunction.apply(tmp);
            totalWeight += tmpWeight;
            weightArray[index++] = tmpWeight;
        }
        if (totalWeight <= 0) {
            return Optional.empty();
        }
        int randomValue = random.nextInt(totalWeight);
        index = 0;
        for (T tmp : randomCollection) {
            randomValue -= weightArray[index++];
            if (randomValue < 0) {
                return Optional.ofNullable(tmp);
            }
        }
        return Optional.empty();
    }

    /**
     * 从一个数组中 根据权重随机取出1个对象
     * 
     * @param randomArray
     * @param calcWeightFunction 计算列表元素权重的方法
     * @return
     */
    public static <T> Optional<T> randomByWeight(T[] randomArray, Function<T, Integer> calcWeightFunction) {
        if (randomArray == null || randomArray.length <= 0 || calcWeightFunction == null) {
            return Optional.empty();
        }
        int[] weightArray = new int[randomArray.length];
        int totalWeight = 0;
        for (int i = 0; i < randomArray.length; i++) {
            T tmp = randomArray[i];
            int tmpWeight = calcWeightFunction.apply(tmp);
            weightArray[i] = tmpWeight;
            totalWeight += tmpWeight;
        }
        if (totalWeight <= 0) {
            return Optional.empty();
        }
        int randomValue = random.nextInt(totalWeight);
        for (int i = 0; i < weightArray.length; i++) {
            randomValue -= weightArray[i];
            if (randomValue < 0) {
                T tmp = randomArray[i];
                return Optional.of(tmp);
            }
        }
        return Optional.empty();
    }

    /**
     * 从一组列表中 根据权重随机取出1个对象
     * 
     * @param randomList
     * @param calcWeightFunction 计算列表元素权重的方法
     * @return
     */
    public static <T> int randomIndexByWeight(List<T> randomList, Function<T, Integer> calcWeightFunction) {
        if (randomList == null || randomList.isEmpty() || calcWeightFunction == null) {
            return -1;
        }
        int size = randomList.size();
        int[] weightArray = new int[size];
        int totalWeight = 0;
        for (int i = 0; i < size; i++) {
            T tmp = randomList.get(i);
            int tmpWeight = calcWeightFunction.apply(tmp);
            totalWeight += tmpWeight;
            weightArray[i] = tmpWeight;
        }
        if (totalWeight <= 0) {
            return -1;
        }
        int randomValue = random.nextInt(totalWeight);
        for (int i = 0; i < size; i++) {
            randomValue -= weightArray[i];
            if (randomValue < 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从一个数组中 根据权重随机取出1个下标
     * 
     * @param randomArray
     * @param calcWeightFunction 计算列表元素权重的方法
     * @return
     */
    public static <T> int randomIndexByWeight(T[] randomArray, Function<T, Integer> calcWeightFunction) {
        if (randomArray == null || randomArray.length <= 0 || calcWeightFunction == null) {
            return -1;
        }
        int[] weightArray = new int[randomArray.length];
        int totalWeight = 0;
        for (int i = 0; i < randomArray.length; i++) {
            T tmp = randomArray[i];
            int tmpWeight = calcWeightFunction.apply(tmp);
            weightArray[i] = tmpWeight;
            totalWeight += tmpWeight;
        }
        if (totalWeight <= 0) {
            return -1;
        }
        int randomValue = random.nextInt(totalWeight);
        for (int i = 0; i < weightArray.length; i++) {
            randomValue -= weightArray[i];
            if (randomValue < 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从一组集合中 根据权重随机取出1个对象
     * 
     * @param randomCollection
     * @param randomSize
     * @param repeat             是否会重复抽取
     * @param calcWeightFunction 计算列表元素权重的方法
     * @return
     */
    public static <T> List<T> randomListByWeight(Collection<T> randomCollection, int randomSize, boolean repeat, Function<T, Integer> calcWeightFunction) {
        if (randomCollection == null || randomCollection.isEmpty() || randomSize <= 0 || calcWeightFunction == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        int[] weightArray = new int[randomCollection.size()];
        int totalWeight = 0;
        int index = 0;
        for (T tmp : randomCollection) {
            int tmpWeight = calcWeightFunction.apply(tmp);
            totalWeight += tmpWeight;
            weightArray[index++] = tmpWeight;
        }
        if (totalWeight <= 0) {
            return Collections.emptyList();
        }
        for (int i = 0; i < randomSize; i++) {
            if (totalWeight <= 0) {
                break;
            }
            int randomValue = random.nextInt(totalWeight);
            index = 0;
            for (T tmp : randomCollection) {
                int weight = weightArray[index];
                if (weight <= 0) {
                    index++;
                    continue;
                }
                randomValue -= weight;
                if (randomValue < 0) {
                    result.add(tmp);
                    if (!repeat) {
                        totalWeight -= weight;
                        weightArray[index] = 0;
                    }
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /**
     * 随机生成数组<br>
     * 数组元素在[min,max]中<br>
     * 若不可重复 且随机范围比数量小 生成的数组小于randomSize
     * 
     * @param min
     * @param max
     * @param randomSize
     * @param repeat     是否有重复的元素
     * @return
     */
    public static int[] randomArray(int min, int max, int randomSize, boolean repeat) {
        if (min > max || randomSize <= 0) {
            return new int[0];
        }
        if (repeat) {
            // 可重复抽取
            int[] result = new int[randomSize];
            for (int i = 0; i < randomSize; i++) {
                result[i] = random(min, max + 1);
            }
            return result;
        }
        // 不重复抽取
        int poolSize = max - min + 1;
        if (poolSize < randomSize) {
            randomSize = poolSize;
        }
        int[] srcArray = new int[poolSize];
        for (int i = 0; i < poolSize; i++) {
            srcArray[i] = min + i;
        }
        shuffle(srcArray);
        int[] result = Arrays.copyOf(srcArray, randomSize);
        return result;
    }

    /**
     * 从一组集合里 抽取多个对象<br>
     * 几率相同
     * 
     * @param sourceList
     * @param resultSize 若数量大于来源 且不可重复 返回列表大小为较小值
     * @param repeat
     * @return
     */
    public static <T> List<T> randomList(List<T> sourceList, int resultSize, boolean repeat) {
        if (sourceList == null || sourceList.isEmpty() || resultSize <= 0) {
            return Collections.emptyList();
        }
        int[] randomIndexs = randomArray(0, sourceList.size() - 1, resultSize, repeat);
        List<T> result = new ArrayList<>(resultSize);
        for (int index : randomIndexs) {
            T tmp = sourceList.get(index);
            result.add(tmp);
        }
        return result;
    }

    /**
     * 从一组集合里 抽取多个对象<br>
     * 几率相同
     * 
     * @param sources
     * @param resultSize 若数量大于来源 且不可重复 返回列表大小为较小值
     * @param repeat
     * @return
     */
    public static <T> List<T> randomList(Collection<T> sources, int resultSize, boolean repeat) {
        if (sources == null || sources.isEmpty() || resultSize <= 0) {
            return Collections.emptyList();
        }
        int[] randomIndexs = randomArray(0, sources.size() - 1, resultSize, repeat);
        List<T> tmpList = null;
        if (sources instanceof List) {
            tmpList = (List<T>) sources;
        } else {
            tmpList = new ArrayList<>(sources);
        }
        List<T> result = new ArrayList<>(resultSize);
        for (int index : randomIndexs) {
            T tmp = tmpList.get(index);
            result.add(tmp);
        }
        return result;
    }

    /**
     * 根据Map里的权重随机出多个sid
     */
    public static <T> Set<T> randomMoreByWeight(Map<T, ? extends Number> map, int count, boolean repeat) {
        Map<T, ? extends Number> tmpMap = new HashMap<>(map);
        Set<T> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int total = 0;
            for (Number num : tmpMap.values()) {
                total += num.intValue();
            }
            int randomNum = random(total);
            int tmp = 0;
            Iterator<? extends Map.Entry<T, ? extends Number>> iterator = tmpMap.entrySet().iterator();
            in: while (iterator.hasNext()) {
                Map.Entry<T, ? extends Number> entry = iterator.next();
                tmp += entry.getValue().intValue();
                if (tmp > randomNum) {
                    result.add(entry.getKey());
                    if (!repeat) {
                        iterator.remove();
                    }
                    break in;
                }
            }
        }
        return result;
    }

    public static <T> T random(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(random(list.size()));
    }
}
