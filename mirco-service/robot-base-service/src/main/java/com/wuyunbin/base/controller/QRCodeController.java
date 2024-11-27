package com.wuyunbin.base.controller;

import com.wuyunbin.base.QRCode;
import com.wuyunbin.base.service.QRCodeService;
import com.wuyunbin.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "二维码操作类")
@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Resource
    private QRCodeService qrCodeService;

    @Operation(summary = "生成二维码")
    @PostMapping("/generate")
    public Result<String> generateQRCode(@RequestBody QRCode qrCode) {
        String code = qrCodeService.generateQRCode(qrCode);
        return Result.success(code);
    }
}
