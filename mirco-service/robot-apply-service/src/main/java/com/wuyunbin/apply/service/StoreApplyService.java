package com.wuyunbin.apply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyunbin.apply.dto.ApplyPageQueryDTO;
import com.wuyunbin.apply.dto.StoreApplyDTO;
import com.wuyunbin.apply.entity.StoreApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 店铺入驻申请表 服务类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
public interface StoreApplyService extends IService<StoreApply> {

    void apply(StoreApply storeApply);

    void approval(StoreApplyDTO storeApplyDTO);

    IPage<StoreApply> getPage(ApplyPageQueryDTO applyPageQueryDTO);
}
