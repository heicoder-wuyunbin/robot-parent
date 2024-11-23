package com.wuyunbin.apply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyunbin.apply.dto.ApplyPageQueryDTO;
import com.wuyunbin.apply.dto.MerchantApplyDTO;
import com.wuyunbin.apply.entity.MerchantApply;
import com.wuyunbin.apply.service.MerchantApplyService;
import com.wuyunbin.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "商家入驻申请表")
@RestController
@RequestMapping("/merchantApply")
public class MerchantApplyController {
    @Resource
    private MerchantApplyService merchantApplyService;

    @Operation(summary = "申请入驻")
    @PostMapping("")
    public Result<Object> apply(@RequestBody MerchantApply merchantApply){
        merchantApplyService.apply(merchantApply);
        return Result.success();
    }

    @Operation(summary = "商家入驻审批")
    @PutMapping
    public Result<Object> approval(@RequestBody MerchantApplyDTO merchantApplyDTO){
        merchantApplyService.approval(merchantApplyDTO);
        return Result.success();
    }

    @Operation(summary = "分页查询商家入驻申请")
    @PostMapping("page")
    public Result<IPage<MerchantApply>> getPage(@RequestBody ApplyPageQueryDTO applyPageQueryDTO){
        IPage<MerchantApply> page= merchantApplyService.getPage(applyPageQueryDTO);
        return Result.success(page);
    }
}
