package cloud.apollo.controller;

import cloud.apollo.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController {
    @Autowired
    private ConfigProperties configProperties;

    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String getName() {
        return configProperties.getName();
    }

    @GetMapping("/mysql")
    public Map<Object, Object> getMySQLProperties() {
        // JDK9中的新特性，快速创建只读集合。
        Map<Object, Object> map = new HashMap<>();
        map.put("host", configProperties.getMysqlHost());
        map.put("port", configProperties.getMysqlPort());
        map.put("username", configProperties.getMysqlUsername());
        map.put("password", configProperties.getMysqlPassword());
        return map;
    }
}
