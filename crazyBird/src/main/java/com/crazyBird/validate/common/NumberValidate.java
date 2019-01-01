package com.crazyBird.validate.common;

import org.apache.commons.lang3.StringUtils;

import com.crazyBird.utils.RegexUtils;

public class NumberValidate extends AbstractValidate {

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        String pattern = "^\\d+$";
        return RegexUtils.regexMatch(pattern, value);
    }

}
