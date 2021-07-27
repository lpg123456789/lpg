package gk.common.shine.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    public static Logger log = Logger.getLogger(ClassUtil.class);

    public static final String CLASS_FILE_SUBFIX = ".class";

    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {// 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            log.error("ClassNotFound className=" + className, e);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        log.error("getClasses jar error", e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("getClasses error", e);
        }

        return classes;
    }

    /**
     * 获取某个字段上的注解
     *
     * @param object          字段实例
     * @param annotationClazz 注解类型
     * @param <T>             注解
     * @return 目标注解
     */
    public static <T extends Annotation> T getFiledAnnotation(Object object, Class<T> annotationClazz) {
        if (object == null) {
            return null;
        }
        Class<?> clazz = object.getClass();
        if (clazz.isEnum()) {
            String enumName = ((Enum<?>) object).name();
            try {
                return clazz.getField(enumName).getAnnotation(annotationClazz);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            }
        }
        return clazz.getAnnotation(annotationClazz);
    }


    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            log.error("no class,dir=" + dir.getName());
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    log.error("class not found, className=" + className);
                }
            }
        }
    }

    /**
     * 查找指定包名下的所有符合条件类<br>
     * 含子包<br>
     * class.forName会导致static{}方法块执行<br>
     * 
     * 
     * @param packageName
     * @param filter
     * @return
     */
    public static Set<Class<?>> scanClasses(String packageName, Predicate<Class<?>> filter) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String packageDirName = packageName.replace('.', '/');
            Enumeration<URL> urls = classLoader.getResources(packageDirName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url == null) {
                    continue;
                }
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    File packageFile = new File(packagePath);
                    if (!packageFile.exists() || !packageFile.isDirectory()) {
                        continue;
                    }
                    File[] childFiles = packageFile.listFiles();
                    for (File childFile : childFiles) {
                        String childFileName = childFile.getName();
                        if (childFile.isDirectory()) {
                            // 文件夹 递归查找子包
                            String subPackageName = packageName + "." + childFileName;
                            Set<Class<?>> subClasses = scanClasses(subPackageName, filter);
                            classes.addAll(subClasses);
                        } else if (childFileName.endsWith(CLASS_FILE_SUBFIX)) {
                            // class文件
                            String clazzName = packageName + "." + childFileName.substring(0, childFileName.length() - CLASS_FILE_SUBFIX.length());
                            try {
                                Class<?> clazz = Class.forName(clazzName);
                                if (clazz != null && (filter == null || filter.test(clazz))) {
                                    classes.add(clazz);
                                }
                            } catch (Exception e) {
                                log.error("class[" + clazzName + "] error", e);
                            }
                        }
                    }

                } else if ("jar".equals(protocol)) {
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = jarURLConnection.getJarFile();
                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    while (jarEntries.hasMoreElements()) {
                        JarEntry jarEntry = jarEntries.nextElement();
                        String name = jarEntry.getName();
                        if (jarEntry.isDirectory() || !name.endsWith(CLASS_FILE_SUBFIX)) {
                            // 文件夹 或者不是class文件
                            continue;
                        }
                        String clazzName = name.substring(0, name.length() - CLASS_FILE_SUBFIX.length()).replace('/', '.');
                        if (!clazzName.startsWith(packageName)) {
                            continue;
                        }
                        try {
                            Class<?> clazz = Class.forName(clazzName);
                            if (clazz != null && (filter == null || filter.test(clazz))) {
                                classes.add(clazz);
                            }
                        } catch (Exception e) {
                            log.error("class[" + clazzName + "] error", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("find package[" + packageName + "] error", e);
        }
        return classes;
    }

    public static Set<Class<?>> scanImplClasses(String packageName, final Class<?> clazz) {
        Set<Class<?>> clazzSet = scanClasses(packageName, tmpClazz -> {
            if (!clazz.isAssignableFrom(tmpClazz)) {
                return false;
            }
            if (tmpClazz.isInterface()) {
                return false;
            }
            if (Modifier.isAbstract(tmpClazz.getModifiers())) {
                return false;
            }
            return true;
        });
        return clazzSet;
    }

    /**
     * 通过某个接口或者抽象类的 Class 对象，创建出相应的对象 可以处理普通类，枚举（包括枚举直接实现接口，或者在枚举字段中实现接口）
     *
     * @param implClasses 接口实现类 Class
     * @param <T>         抽象类型
     * @return objects
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> newImplClassesObjects(List<Class<? extends T>> implClasses)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> newObjects = new ArrayList<>(implClasses.size());
        for (Class<? extends T> implClass : implClasses) {
            if (implClass.isEnum()) { // 处理枚举直接实现接口
                Object[] objects = implClass.getEnumConstants();
                for (Object object : objects) {
                    T obj = (T) object;
                    newObjects.add(obj);
                }
            } else if (implClass.getSuperclass().isEnum()) {// 处理在枚举字段中实现接口的情况
                Class<?> superClass = implClass.getSuperclass();
                Object[] objects = superClass.getEnumConstants();
                for (Object object : objects) {
                    if (object.getClass() == implClass) {
                        T obj = (T) object;
                        newObjects.add(obj);
                        break;
                    }
                }
            } else {
                T object = getOrCreateInstance(implClass);
                newObjects.add(object);
            }
        }
        return newObjects;
    }



    public static <T> List<T> scanImplAndNewInstances(String packageName, Class<T> clazz) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Set<Class<?>> implClazzes = scanImplClasses(packageName, clazz);
        List<T> instances = new ArrayList<>(implClazzes.size());
        if (implClazzes.isEmpty()) {
            return Collections.emptyList();
        }
        for (Class<?> implClazz : implClazzes) {
            if (implClazz.isEnum()) { // 处理枚举直接实现接口
                Object[] enums = implClazz.getEnumConstants();
                for (Object enumInstance : enums) {
                    T obj = clazz.cast(enumInstance);
                    instances.add(obj);
                }
            } else if (implClazz.getSuperclass().isEnum()) {// 处理在枚举字段中实现接口的情况
                Class<?> superClass = implClazz.getSuperclass();
                Object[] enums = superClass.getEnumConstants();
                for (Object enumInstance : enums) {
                    if (enumInstance.getClass() == implClazz) {
                        T obj = clazz.cast(enumInstance);
                        instances.add(obj);
                        break;
                    }
                }
            } else {
                T object = clazz.cast(getOrCreateInstance(implClazz));
                instances.add(object);
            }
        }
        return instances;
    }


    /**
     * 反射构造一个对象，如果设置了单例方法 getInstance,将直接使用单例而不是重新创建新对象
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getOrCreateInstance(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Method method = clazz.getMethod("getInstance");
            Object instance = method.invoke(clazz);
            if (instance != null) {
                return clazz.cast(instance);
            }
        }catch (Exception ignored){
        }
        return clazz.getConstructor().newInstance();
    }

}