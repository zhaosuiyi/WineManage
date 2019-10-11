package com.wine.common.config;

/**
 * @Author: Yi.Zhao
 * @Description:
 * @Date: 2019/7/1 10:41
 * @Version: 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 让mybatis支持typeAliasesPackage使用通配符 com.mdtech.**.model
 * @author: calebzhao
 * @date: 2018/3/27 17:30
 */
@Slf4j
public class MybatisSqlSessionFactoryBean extends com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean {

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    @Override
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);

        Set<String> result = new HashSet<>();
        String[] typeAliasesPackages = typeAliasesPackage.split(",");
        for (String location : typeAliasesPackages){
            location = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(location) + "/" + DEFAULT_RESOURCE_PATTERN;
            try {
                Resource[] resources =  resolver.getResources(location);
                if(resources != null && resources.length > 0){
                    MetadataReader metadataReader;
                    for(Resource resource : resources){
                        if(resource.isReadable()){
                            metadataReader =  metadataReaderFactory.getMetadataReader(resource);
                            String name = Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName();
                            result.add(name);
                        }
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                log.error("PackagesSqlSessionFactoryBean扫描出错", e);
            }
        }



        if(result.size() > 0) {
            super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));
        }
        else{
            log.warn("参数typeAliasesPackage:{}，未找到任何包", typeAliasesPackage);
        }

    }

}
