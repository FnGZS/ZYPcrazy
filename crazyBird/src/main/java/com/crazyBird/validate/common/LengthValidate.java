package com.crazyBird.validate.common;

import org.apache.commons.lang3.StringUtils;

public class LengthValidate extends AbstractValidate {

    /**
     * æœ?å°é•¿åº?
     */
    private int minLength;

    /**
     * æœ?å¤§é•¿åº?
     */
    private int maxLength;

    @Override
    protected boolean execute() {
        int length = 0;
        if (StringUtils.isBlank(value)) {
            return true;
        } else {
            length = value.trim().getBytes().length;
        }
        if (length >= minLength && length <= maxLength) {
            return true;
        } else {
            return false;
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

}
