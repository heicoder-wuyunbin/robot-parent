package com.wuyunbin.sso.api;

import com.wuyunbin.sso.entity.Merchant;
import com.wuyunbin.sso.service.MerchantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchantApi")
public class MerchantApi {
    @Resource
    private MerchantService merchantService;

    @PostMapping("")
    public Boolean save(@RequestBody Merchant merchant){
        this.merchantService.save(merchant);
        return true;
    }
}
