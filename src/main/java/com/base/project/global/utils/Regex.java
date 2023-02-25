package com.base.project.global.utils;

import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 형식 체크하는 곳 */
public class Regex {
    /* 이메일 형식 체크 */
    public static void isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        if(!matcher.find()){
            throw new CustomException(ErrorCode.REGEX_FAILED_EMAIL);
        }
    }
}