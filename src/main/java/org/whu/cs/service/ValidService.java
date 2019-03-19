package org.whu.cs.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * 数据校验类
 */
@Service
public class ValidService {

    /**
     * Is valid leetcode url boolean.
     *
     * @param url the url
     * @return the boolean
     */
    public boolean isValidLeetcodeUrl(String url) {
        String pattern = "https://leetcode(-cn)?.com/([A-Za-z0-9_-]+)/";
        return Pattern.matches(pattern, url);
    }
}
