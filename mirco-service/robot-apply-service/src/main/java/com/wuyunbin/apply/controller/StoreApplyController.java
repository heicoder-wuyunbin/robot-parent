package com.wuyunbin.apply.controller;

import com.wuyunbin.apply.dto.StoreApplyDTO;
import com.wuyunbin.apply.entity.StoreApply;
import com.wuyunbin.apply.service.StoreApplyService;
import com.wuyunbin.common.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 店铺入驻申请表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@RestController
@RequestMapping("/storeApply")
public class StoreApplyController {
    @Resource
    private StoreApplyService storeApplyService;

    @PostMapping("apply")
    public Result<Object> apply(@RequestBody StoreApply storeApply){
        storeApplyService.apply(storeApply);
        return Result.success();
    }

    @PutMapping("approval")
    public Result<Object> approval(@RequestBody StoreApplyDTO storeApplyDTO){
        storeApplyService.approval(storeApplyDTO);
        return Result.success();
    }

}
