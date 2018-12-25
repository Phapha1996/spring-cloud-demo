package org.fage.querydsltest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/10/31 10:27
 * @description
 **/
public class SimpleTest {

    @Test
    public void test(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
