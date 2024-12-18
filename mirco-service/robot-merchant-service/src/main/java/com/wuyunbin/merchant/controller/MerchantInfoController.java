package com.wuyunbin.merchant.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.merchant.entity.MerchantInfo;
import com.wuyunbin.merchant.service.MerchantInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商户信息表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@RestController
@RequestMapping("/merchantInfo")
public class MerchantInfoController {
    @Resource
    private MerchantInfoService merchantInfoService;

    @PostMapping()
    public Result<Object> save(MerchantInfo merchantInfo) {
        this.merchantInfoService.save(merchantInfo);
        return Result.success();
    }
}
