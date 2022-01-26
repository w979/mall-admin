package com.wry.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGeneratorUtil {
    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<>();
            File configFile = new File(MybatisGeneratorUtil.class.getResource("/mybatis-generator-config.xml").toURI());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            warnings.forEach(System.out::println);

            System.out.println("生成成功");
        } catch (Exception e) {
            System.out.println("生成失败："+e.getMessage());
            e.printStackTrace();
        }

    }

}
