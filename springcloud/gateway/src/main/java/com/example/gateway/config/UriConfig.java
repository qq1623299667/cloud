package com.example.gateway.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置uri的类
 * @author Jia Shi
 * @since 2020/12/25
 */
@Component
public class UriConfig {
    private static final String LOGIN="/redis/login/login";
    /**
     * 免token校验的uri
     * @author Jia Shi
     * @since 2020/12/25
     */
    public List<String> getIgnoreUris() {
        List<String> list = new ArrayList<>();
        list.add(LOGIN);
        return list;
    }

    /**
     * 生成token的uri
     * @author Jia Shi
     * @since 2020/12/25
     */
    public List<String> getCreateTokenUris() {
        List<String> list = new ArrayList<>();
        list.add(LOGIN);
        return list;
    }
}
