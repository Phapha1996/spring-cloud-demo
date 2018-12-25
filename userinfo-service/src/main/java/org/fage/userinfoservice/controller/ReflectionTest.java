package org.fage.userinfoservice.controller;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/10/19 16:52
 * https://blog.csdn.net/hongxingxiaonan/article/details/49202613
 * @description 反射获取泛型类型
 **/
public class ReflectionTest {
    private List<Integer> list1;
    private List<Set<Integer>> listSet;
    public Set<String> fun1(Map<Integer, String> map){return null;}


    /**
     * 获取属性泛型
     */
    public static void getFieldGenericType()  {
        try {
            Class clazz = ReflectionTest.class;
            Field field = clazz.getDeclaredField("list1");
            Type type = field.getGenericType();  //取得field的type
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type; //强转成具体的实现类
            Type[] genericTypes = parameterizedType.getActualTypeArguments();  //取得包含的泛型类型
            System.out.println(genericTypes[0]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取递归属性泛型
     */
    public static void getFieldGenericType1()  {
        try {
            Class clazz = ReflectionTest.class;
            Field field = clazz.getDeclaredField("listSet");
            Type type = field.getGenericType();  //取得field的type
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type; //强转成具体的实现类
            Type[] genericTypes = parameterizedType.getActualTypeArguments();  //取得包含的泛型类型

            ParameterizedTypeImpl setType = (ParameterizedTypeImpl) genericTypes[0];//再次将代表Set<Integer>的Type强转成ParameterizedTypeImpl
            Type[] setTypeArguments = setType.getActualTypeArguments();
            System.out.println(setTypeArguments[0]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取方法泛型
     */
    public static void getMethodGenericType()  {
        try {
            Class clazz = ReflectionTest.class;
            Method method = clazz.getMethod("fun1", Map.class);
            Type type = method.getGenericReturnType();
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type;
            Type[] genericTypes = parameterizedType.getActualTypeArguments();
            System.out.println("return generic type " + genericTypes[0]);

            Type mapType = method.getGenericParameterTypes()[0];
            ParameterizedTypeImpl mapParamType = (ParameterizedTypeImpl) mapType;//再次将代表Set<Integer>的Type强转成ParameterizedTypeImpl
            Type[] mapArgs = mapParamType.getActualTypeArguments();
            System.out.println("method param first generic type " + mapArgs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)  {

        getFieldGenericType();
    }


}
