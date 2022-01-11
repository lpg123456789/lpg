//package com.game.utils;
//
//import game.utils.ClassUtil;
//import org.apache.log4j.Logger;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//public class CollectionUtil {
//
//    private static Logger log = Logger.getLogger(CollectionUtil.class);
//    /**
//     * 将List转成Map<R,list<T>>的集合,比如将item按照type进行ConcurrentMap分组,在读取Excel表的时候有使用场景</>></>
//     */
//    public static <T,R> ConcurrentMap<R, List<T>> listToCGroupMaps(Collection<T> list, Function<? super T, ? extends R> f){
//        if(list==null){
//            return new ConcurrentHashMap<R, List<T>>();
//        }
//        return list.stream().collect(Collectors.groupingByConcurrent(f));
//    }
//
//    /**
//     * 将List转成Map<R,list<T>>的集合,比如将item按照type进行map分组,在读取Excel表的时候有使用场景</>></>
//     */
//    public static <T,R> Map<R, List<T>> listToGroupMaps(Collection<T> list, Function<? super T, ? extends R> f){
//        if(list==null){
//            return new HashMap<>();
//        }
//        return list.stream().collect(Collectors.groupingBy(f));
//    }
//
//    /**
//     * 将List转成Map<R,T>的集合,比如将List<Person>转为Map<Long,Person>,出现相同的key后面的会覆盖前面的
//     */
//    public static <T,R> Map<R, T> listToKVMap(Collection<T> list, Function<? super T, ? extends R> f){
//        return listToKVMap(list, f,true);
//    }
//
//    /**
//     * 将List转成Map<R,T>的集合,比如将List<Person>转为Map<Long,Person>,如果fixDuplicate为true,
//     * list出现相同的key会进行覆盖,否则抛错.
//     */
//    public static <T,R> Map<R, T> listToKVMap(Collection<T> list, Function<? super T, ? extends R> f,boolean fixDuplicate){
//        if(list==null){
//            return new HashMap<R, T>();
//        }
//        if(fixDuplicate){
//            return list.stream().collect(Collectors.toMap(f,Function.identity(),(key1, key2) -> key2));
//        }else{
//            return list.stream().collect(Collectors.toMap(f,Function.identity()));
//        }
//    }
//
//    /**
//     * 将List转成Map<R,T>的集合,比如将List<Person>转为ConcurrHashMap<Long,Person>,进行map分组,出现相同的key后面的会覆盖前面的
//     */
//    public static <T,R> ConcurrentMap<R, T> listToKVCMap(Collection<T> list, Function<? super T, ? extends R> f){
//        return listToKVCMap(list, f, true);
//    }
//
//    /**
//     * 将List转成Map<R,T>的集合,比如将List<Person>转为ConcurrHashMap<Long,Person>,进行map分组,如果fixDuplicate为true,
//     * list出现相同的key会进行覆盖,否则抛错.
//     */
//    public static <T,R> ConcurrentMap<R, T> listToKVCMap(Collection<T> list, Function<? super T, ? extends R> f,boolean fixDuplicate){
//        if(list==null){
//            return new ConcurrentHashMap<R, T>();
//        }
//        if(fixDuplicate){
//            return list.stream().collect(Collectors.toConcurrentMap(f,Function.identity(),(key1, key2) -> key2));
//        }else{
//            return list.stream().collect(Collectors.toConcurrentMap(f,Function.identity()));
//        }
//    }
//
//    /**
//     * 将List转成Map<R,T>的集合,比如将List<Person>转为ConcurrHashMap<Long,Person>,支持选定的Map返回类型
//     */
//    public static <T,R extends Map<T,R>> Map<? extends R, T> listToKVCMap(List<T> list, Function<? super T, ? extends R> f,Supplier<Map<R,T>> supplier){
//        if(list==null){
//            return supplier.get();
//        }
//        return list.stream().collect(Collectors.toMap(f,Function.identity(),(key1, key2) -> key2,supplier));
//    }
//
//
//    /**
//     * 取集合符合要求的某个值
//     */
//    public static <T> T filterOne(List<T> list,Predicate<T> predicate){
//        return list.stream().filter(predicate).findFirst().get();
//    }
//
//    /**
//     * 取集合符合要求的值
//     */
//    public static <T> List<T> filterAll(List<T> list,Predicate<T> predicate){
//        return list.stream().filter(predicate).collect(Collectors.toList());
//    }
//
//    /**
//     * map值转list
//     */
//    public static <K,V> List<V> mapValueToList (Map<K,V> map){
//        return new ArrayList<V>(map.values());
//    }
//
//    /**
//     * map键转list
//     */
//    public static <K,V> List<K> mapKeyToList (Map<K,V> map){
//        return new ArrayList<K>(map.keySet());
//    }
//
//    /**
//     * 打印集合
//     * @param map
//     */
//    public static <K,V> void printCollection(Map<K,V> map){
//        map.forEach((k,v)->{System.out.println(k+":"+v);});
//    }
//
//    public static  Function<Object, Object> self(){
//        return Function.identity();
//    }
//
//    /**
//     * 打印集合
//     * @param collection
//     */
//    public static <T> void printCollection (Collection<T> collection){
//        collection.forEach(System.out::println);
//    }
//
//    /**
//     * 查找某个路径下,目标类的所有实例
//     * @param basePackName 类路径
//     * @param clazz 类
//     * @param <T>
//     * @return
//     */
//    public static <T> Set<T> findAllClassInstance(String basePackName,Class<T> clazz) {
//        return findAllClassInstance(basePackName,clazz,t->t!=null);
//    }
//
//    public static <T> Set<T> findAllClassInstance(Class<?> clazz, Set<Class<?>> classSet) {
//        return findAllClassInstance(clazz,t->t!=null,classSet);
//    }
//
//    public static <T> Set<T> findAllClassInstance(String basePackName,Class<T> clazz,Predicate<T> predicate) {
//        Set<Class<?>> classSet = ClassUtil.getClasses(basePackName);
//        Set<T> tasks = findAllClassInstance(clazz, predicate, classSet);
//        return tasks;
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> Set<T> findAllClassInstance(Class<?> clazz, Predicate<T> predicate, Set<Class<?>> classSet) {
//        return classSet.stream().map(c->{
//            try {
//                return (T) c.newInstance();
//            } catch (Exception e) {
//                log.error("create "+clazz.getSimpleName()+" error!!! className:"+c.getSimpleName(),e);
//            }
//            return null;
//        }).filter(predicate).collect(Collectors.toSet());
//    }
//
//}
