package com.wokecustoms.prompt.image.controller.util;

import com.wokecustoms.prompt.image.exception.ServiceErrorCode;
import com.wokecustoms.prompt.image.exception.ServiceException;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public void validateSortRegex(String sort) {
        Pattern pattern = Pattern.compile("^(?:[A-Za-z]+:(?:asc|desc))");
        if (!pattern.matcher(sort).matches()) {
            throw new ServiceException(String.format("Sort parameter '%s' is not valid.", sort), ServiceErrorCode.INVALID_SORT_REGEX);
        }
    }
}
