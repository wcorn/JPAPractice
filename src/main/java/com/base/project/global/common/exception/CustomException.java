package com.base.project.global.common.exception;

import com.base.project.global.common.api.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException  {
    private final ErrorCode errorCode;

}