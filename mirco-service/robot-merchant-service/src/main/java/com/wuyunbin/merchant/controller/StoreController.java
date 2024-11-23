package com.wuyunbin.merchant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyunbin.common.Result;
import com.wuyunbin.merchant.dto.StorePageQueryDTO;
import com.wuyunbin.merchant.entity.Store;
import com.wuyunbin.merchant.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@Tag(name = "店铺控制器")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @Operation(summary = "分页查询店铺", description = "根据条件分页查询店铺，主要提供给管理后台")
    @PostMapping("page")
    public Result<IPage<Store>> getPage(@RequestBody StorePageQueryDTO storePageQueryDTO){
        IPage<Store> page= storeService.getPage(storePageQueryDTO);
        return Result.success(page);
    }

    //附近的店铺分页显示
    @Operation(summary = "分页查询附近店铺", description = "根据条件分页查询附近店铺，主要提供给小程序端")
    @PostMapping("nearbyPage")
    public Result<IPage<Store>> getNearbyPage(@RequestBody StorePageQueryDTO storePageQueryDTO){
        IPage<Store> page= storeService.getNearbyPage(storePageQueryDTO);
        return Result.success(page);
    }
}
