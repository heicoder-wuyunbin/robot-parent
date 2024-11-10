package com.wuyunbin.apply.controller;

import com.wuyunbin.apply.dto.MerchantApplyDTO;
import com.wuyunbin.apply.entity.MerchantApply;
import com.wuyunbin.apply.service.MerchantApplyService;
import com.wuyunbin.common.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商家入驻申请表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@RestController
@RequestMapping("/merchantApply")
public class MerchantApplyController {
    @Resource
    private MerchantApplyService merchantApplyService;

    @PostMapping("")
    public Result<Object> apply(@RequestBody MerchantApply merchantApply){
        merchantApplyService.apply(merchantApply);
        return Result.success();
    }

    @PutMapping
    public Result<Object> approval(@RequestBody MerchantApplyDTO merchantApplyDTO){
        merchantApplyService.approval(merchantApplyDTO);
        return Result.success();
    }
}
