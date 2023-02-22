package com.base.project.domain.test;

import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.api.ResponseCode;
import com.base.project.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    @GetMapping("/success")
    public ResponseEntity<ApiResponse<String>> testSuccess() {
        String data = "test api success";
        log.info("test controller {}",data);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(ResponseCode.TEST));
    }

    @GetMapping("/fail")
    public ResponseEntity<ApiResponse<String>> testFail() {
        String data = "test api fail";
        log.info("test controller {}",data);
        throw new CustomException(ErrorCode.TEST);
    }
}