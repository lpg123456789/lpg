package gk.server.shine.persistence.scan;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import gk.common.shine.utils.ClassUtil;
import gk.server.shine.persistence.annotation.TableName;
import io.netty.util.internal.StringUtil;

/**
 * xml包扫描,供mybatis使用
 */
public class XMLConfigBuilderHelper {

    public static Logger log = Logger.getLogger(XMLConfigBuilderHelper.class);

    private static XMLConfigBuilderHelper configBuilderHelper = new XMLConfigBuilderHelper();

    public static XMLConfigBuilderHelper getInstance() {
        return configBuilderHelper;
    }

    /**
     * 从包package中获取所有的Files
     *
     * @param pack
     * @return
     */
    public List<File> getFiles(String pack) {

        // 第一个class类的集合
        List<File> files = new ArrayList<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = File.separator+pack;
        String packageDirName = packageName.replace(".", File.separator);
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

                log.info("find dir:"+ url.getPath()+" ; protocal :"+protocol);

                // 如果是以文件的形式保存
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findFile(packageName, filePath, recursive, files);
                }
            }
        } catch (IOException e) {
            log.error("getClasses error", e);
        }

        return files;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param files
     */
    public void findFile(String packageName, String packagePath, final boolean recursive, List<File> files) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            log.error("no file,dir=" + dir.getName());
            return;
        }
        File[] fileArr = dir.listFiles();
        if (fileArr == null) {
            return;
        }
        files.addAll(Arrays.asList(fileArr));
    }

    public void scanPackage(String dir, Configuration configuration) throws Exception {

        String mapperPackage = dir;

        String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

        log.info("scan xml dir:" + mapperPackage);

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        String path = mapperPackage.replace(".", File.separator);

        String scanPath = CLASSPATH_ALL_URL_PREFIX+path+File.separator+"*.xml";;


        Resource[] resources = resourcePatternResolver.getResources(scanPath);

        for(Resource r:resources){

//            log.error(r.getFilename());
//
//            log.error(r.getURL().getPath());

            String name = r.getFilename();

            String resoure = path + File.separator + name;
            try{
                parseMapperElement(resoure, null, null, configuration);
            }catch (Exception e){
                log.info(name + " addMapper Error");
            }

        }

//        List<File> files = getFiles(mapperPackage);
//
//        String path = mapperPackage.replace(".", File.separator);
//
//        for (File f : files) {
//            log.info("mapper path:"+f.getPath());
//            String name = f.getName();
//            try {
//                String resoure = path + File.separator + name;
//                parseMapperElement(resoure, null, null, configuration);
//            } catch (Exception e) {
//                log.info(f.getName() + " addMapper Error");
//            }
//        }

    }

    public void scanBean(String dir, Configuration configuration) throws Exception {

        String mapperPackage = dir;

        log.info("scan bean dir:" + mapperPackage);

        Set<Class<?>> classes = ClassUtil.scanClasses(mapperPackage, null);
        for (Class c : classes) {
            try {
                String xml = getXmlMapper(c);

				/* if (StringUtil.isEmptyOrNull(xml)) {
				    continue;
				}*/

                log.debug(xml);

                InputStream inputStream = getStringStream(xml);
                XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, c.getName(), configuration.getSqlFragments());
                mapperParser.parse();
            } catch (Exception e) {
                log.info(c.getSimpleName() + " mapper error", e);
            }

        }

    }


    public void parseMapperElement(String resource, String url, String mapperClass, Configuration configuration) throws IOException, ClassNotFoundException {
        if (resource != null && url == null && mapperClass == null) {
            ErrorContext.instance().resource(resource);
            InputStream inputStream = Resources.getResourceAsStream(resource);
            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
            mapperParser.parse();
        } else if (resource == null && url != null && mapperClass == null) {
            ErrorContext.instance().resource(url);
            InputStream inputStream = Resources.getUrlAsStream(url);
            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
            mapperParser.parse();
        } else if (resource == null && url == null && mapperClass != null) {
            Class<?> mapperInterface = Resources.classForName(mapperClass);
            configuration.addMapper(mapperInterface);
        } else {
            throw new BuilderException("A mapper element may only specify a url, resource or class, but not more than one.");
        }
    }

    public String getXmlMapper(Class<?> classes) {

        Annotation annotation = classes.getAnnotation(TableName.class);
        if (annotation == null) {
            return null;
        }
        String tableName = ((TableName) annotation).value();
		/* if (StringUtil.isEmptyOrNull(tableName)) {
		    return null;
		}*/

        String nameSpace = classes.getSimpleName();

        List<Field> fields = ClassHelper.getAllFields(classes);

        String insertSql = getInsertSql(classes, fields, tableName);

//        String updateSql = getUpdateSql(classes,fields);

        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  \n" +
                "<!DOCTYPE mapper  \n" +
                "    PUBLIC \"-//ibatis.apache.org//DTD Mapper 3.0//EN\"  \n" +
                "    \"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"" + nameSpace + "\">\n" +
//                "\t<update id=\"update\" parameterType=\"com.game.log.bean." + nameSpace + "\">\n" +
//                updateSql +
//                "\t</update>\n" +
//                "\n" +
                " <insert id=\"insert\" parameterType=\"com.game.log.bean." + nameSpace + "\">\n" +
                insertSql +
                " \n</insert>\n" +
                "</mapper> ";

        return template;
    }


    public String getInsertSql(Class<?> classes, List<Field> fields, String tableName) {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ")
                .append(tableName)
                .append(" (");

        int size = fields.size();

        String split = ",";
        String pre = " #{";
        String end = "}";

        for (int i = 0; i < size; i++) {
            Field field = fields.get(i);
//            Annotation annotation = field.getAnnotation(FieldIgnore.class);
//            if (annotation != null) {
//                continue;
//            }
            sb.append(field.getName());
            String symbol = i == size - 1 ? ") " : split;
            sb.append(symbol);
        }

        sb.append("\n");
        sb.append("VALUES (");

        for (int i = 0; i < size; i++) {
            Field field = fields.get(i);
//            Annotation annotation = field.getAnnotation(FieldIgnore.class);
//            if (annotation != null) {
//                continue;
//            }
            sb.append(pre);
            sb.append(field.getName());
            sb.append(end);
            String symbol = i == size - 1 ? ") " : split;
            sb.append(symbol);
        }


        return sb.toString();
    }

//    public String getUpdateSql(Class<?> classes,List<Field> fields) {
//
//        String updateSql = "UPDATE player_${serverId} SET player_name = #{player_name} WHERE player_id=#{player_id}";
//        return updateSql;
//    }

    //将一个字符串转化为输入流
    public static InputStream getStringStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
