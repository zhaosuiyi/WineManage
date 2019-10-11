package com.wine.account.cash;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
 * 
 * @author YI ZHAO
 */
public class Generator {
	/**
	 * 测试 run 执行 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(System.getProperty("user.dir")+"/shaoxi-cw-cost" + "/src/main/java");
		gc.setFileOverride(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setOpen(false);
		gc.setAuthor("ZhaoYi");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceImplName("%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("sx_cw");
		dsc.setPassword("J@dk4lsa&5d#");
		dsc.setUrl("jdbc:mysql://121.43.177.106:3306/sx_cw_db?characterEncoding=utf8");

		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setTablePrefix("sys_");// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(new String[] {"表名"});
		// 字段名生成策略
		//strategy.setFieldNaming(NamingStrategy.underline_to_camel);
		// 自定义实体父类
		//strategy.setSuperEntityClass("com.shaoxi.cw.model.BaseModel");
		// 自定义 mapper 父类
		strategy.setSuperMapperClass("com.mybaitsplus.devtools.core.base.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceImplClass("com.mybaitsplus.devtools.core.base.BaseService");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("com.mybaitsplus.devtools.core.base.AbstractController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		strategy.setEntityColumnConstant(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.shaoxi.wine.account.cash");
		pc.setEntity("model");
		pc.setMapper("mapper");
		pc.setXml("mapper.xml");
		//pc.setServiceImpl("service");
		//pc.setService("ignore");
		//pc.setController("web");
		mpg.setPackageInfo(pc);
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {

				//this.getConfig().getGlobalConfig().setBaseResultMap(true);
				this.getConfig().getGlobalConfig().setBaseColumnList(true);
				Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
			}
		};
		mpg.setCfg(cfg);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("tpl/entity@Data.java.vm");
		tc.setMapper("tpl/mapper.java.vm");
		tc.setXml("tpl/mapper.xml.vm");
		//tc.setService("tpl/service.java.vm");
		//tc.setController("tpl/controller.java.vm");
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
		// 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
	}
}
