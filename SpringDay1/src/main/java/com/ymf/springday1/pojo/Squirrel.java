package com.ymf.springday1.pojo;

/**
 * @author yuanmengfan
 * @date 2022/1/18 11:47 下午
 * @description
 */
public class Squirrel implements  Animal{
    @Override
    public void use() {
        System.out.println("松鼠可以采集松果");
    }
}
