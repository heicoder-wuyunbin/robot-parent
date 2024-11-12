package com.wuyunbin.merchant;

import com.wuyunbin.merchant.entity.MerchantInfo;
import com.wuyunbin.merchant.entity.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "robot-merchant-service",
        contextId = "robot-merchant-service"
)
public interface MerchantClient {
    @PostMapping("merchantInfoApi")
    public boolean save(MerchantInfo merchantInfo);

    @PostMapping("/storeApi/save")
    public boolean addStore(@RequestBody Store store);
}
