package com.wuyunbin.apply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyunbin.apply.dto.ApplyPageQueryDTO;
import com.wuyunbin.apply.dto.MerchantApplyDTO;
import com.wuyunbin.apply.entity.MerchantApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商家入驻申请表 服务类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
public interface MerchantApplyService extends IService<MerchantApply> {

    void apply(MerchantApply merchantApply);

    void approval(MerchantApplyDTO merchantApplyDTO);

    IPage<MerchantApply> getPage(ApplyPageQueryDTO applyPageQueryDTO);
}
