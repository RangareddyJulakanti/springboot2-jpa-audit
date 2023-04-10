/*
package com.ranga.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@Slf4j
public class CodeMessageFormatter {

    private final CodeToMessageFormatter codeToMessageFormatter;
    @Autowired
    public CodeMessageFormatter(CodeToMessageFormatter codeToMessageFormatter){
        this.codeToMessageFormatter=codeToMessageFormatter;
    }
    private static <T> Map<String, T> toMap(Class<? extends Object> T, Object... args) {
        Map<String, T> map = Collections.emptyMap();
        if (args != null) {
            if (args.length % 2 != 0) {
                throw new BaseException("ASSERTION_ERROR", args, "Number of arguments passed needs to be even");
            }

            map = new HashMap();

            for(int i = 0; i < args.length; i += 2) {
                if (!(args[i] instanceof String)) {
                    throw new BaseException("ASSERTION_ERROR", args, "Argument at odd number needs to be of type string");
                }

                ((Map)map).put((String)args[i], args[i + 1]);
            }
        }

        return (Map)map;
    }
    public String format(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        String pattern = this.codeToMessageFormatter.message(code, "");
        this.log.trace("Creating localized message using locale {}, code {},args {}, pattern {}", new Object[]{locale, code, args, pattern});
        Map<String, Object> argsMap = toMap(Object.class, args);
        if (argsMap != null && !argsMap.isEmpty()) {
            if (pattern.length() == 0) {
                return argsMap.toString();
            } else {
                String message = StrSubstitutor.replace(pattern, argsMap, "$", "$");
                return message;
            }
        } else {
            return pattern;
        }
    }
}
*/
