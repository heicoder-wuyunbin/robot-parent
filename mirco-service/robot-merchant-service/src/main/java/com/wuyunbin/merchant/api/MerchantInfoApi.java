package com.wuyunbin.merchant.api;

import com.wuyunbin.merchant.entity.MerchantInfo;
import com.wuyunbin.merchant.service.MerchantInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/merchantInfoApi")
public class MerchantInfoApi {
    @Resource
    private MerchantInfoService merchantInfoService;

    @PostMapping()
    public boolean save(MerchantInfo merchantInfo) {
        this.merchantInfoService.save(merchantInfo);
        return true;
    }
}
