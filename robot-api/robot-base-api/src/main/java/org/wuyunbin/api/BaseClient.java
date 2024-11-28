package org.wuyunbin.api;

import com.wuyunbin.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
        name = "robot-base-service",
        contextId = "robot-base-service"
)
public interface BaseClient {
    @GetMapping("/qrcodeApi/generate/{url}")
    String generateQRCode(@PathVariable("url") String url);
}
