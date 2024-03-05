package com.cqie.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/smart_farm?serverTimezone=UTC";
        String username="root";
        String password="123456";
        String module="";//表示项目的模块名称
        String outPath=System.getProperty("user.dir")+"/"+module+"src/main/java";
        String parent="com.smartfarm";//配置包的名称
        String moduleName="";//模块名
        String entity="entity";//实体类名称
        String service="service";
        String serviceImpl="serviceImpl";
        String controller="controller";
        String mapperXml="mapper.xml";

        List<String> tables = new ArrayList<>();
        tables.add("account_info");

        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author("ljt") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath) // 指定输出目录
                            .disableOpenDir();//生成后不打开目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity(entity)//设置mapper文件夹名称
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller)
                            .xml(mapperXml);
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                //模块设置
                //策略设置
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .entityBuilder()//开启自动生成实体类
                            .enableLombok()//开启lombok模型
                            .mapperBuilder()//开启开启生成mapper
                            .superClass(BaseMapper.class)
//                            .enableMapperAnnotation()//开启mapper注解
                            .formatMapperFileName("%sMapper")//格式化mapper名称
                            .formatXmlFileName("%sMapper")//格式化xml名称
                            .serviceBuilder()//开启生成service
                            .formatServiceFileName("%sService")//格式化service接口的名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化service实现类的名称
                            .controllerBuilder()//开启controller生成
                            .formatFileName("%sController")
                            .enableRestStyle();
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
