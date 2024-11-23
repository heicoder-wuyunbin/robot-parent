package com.wuyunbin.sso.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.sso.entity.Merchant;
import com.wuyunbin.sso.service.MerchantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商户表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    @PostMapping("")
    public Result<Object> save(@RequestBody Merchant merchant){
        this.merchantService.save(merchant);
        return Result.success();
    }
}
