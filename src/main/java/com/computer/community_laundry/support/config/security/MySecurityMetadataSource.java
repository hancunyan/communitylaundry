package com.computer.community_laundry.support.config.security;

import com.computer.community_laundry.service.system.RoleResourceService;
import com.computer.community_laundry.support.entity.system.SysResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 加载权限资源
 */
@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    @Autowired
    private RoleResourceService roleResourceService;

    private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

    private Map<String, Collection<ConfigAttribute>> aclResourceMap = new HashMap<>();

    /**
     * 构造方法
     */
    public MySecurityMetadataSource() {
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Iterator<String> ite = aclResourceMap.keySet().iterator();
        String resURL;
        while (ite.hasNext()) {
            resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(request)) {
                return aclResourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("security: afterPropertiesSet init MySecurityMetadataSource");
        loadResourceDefine();
    }

    /**
     * 的因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制资源
     */
    private void loadResourceDefine() {

        // 获取页面权限资源
        List<SysResource> aclResourceList = roleResourceService.queryAllPage();


        for (SysResource alcResource : aclResourceList) {
            ConfigAttribute ca = new SecurityConfig(alcResource.getResourcename().toUpperCase());
            String url = alcResource.getResourceurl();
            //加载
            if (aclResourceMap.containsKey(url)) {
                Collection<ConfigAttribute> value = aclResourceMap.get(url);
                value.add(ca);
                aclResourceMap.put(url, value);
            } else {
                Collection<ConfigAttribute> atts = new ArrayList<>();
                atts.add(ca);
                aclResourceMap.put(url, atts);
            }
        }
    }
}
