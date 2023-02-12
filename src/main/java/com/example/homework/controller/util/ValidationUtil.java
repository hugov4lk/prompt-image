package com.example.homework.controller.util;

import java.util.regex.Pattern;

import com.example.homework.exception.ServiceErrorCode;
import com.example.homework.exception.ServiceException;
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
