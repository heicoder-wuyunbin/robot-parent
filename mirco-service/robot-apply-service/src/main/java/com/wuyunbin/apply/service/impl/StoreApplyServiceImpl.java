package com.wuyunbin.apply.service.impl;

import com.wuyunbin.apply.dto.StoreApplyDTO;
import com.wuyunbin.apply.entity.StoreApply;
import com.wuyunbin.apply.mapper.StoreApplyMapper;
import com.wuyunbin.apply.service.StoreApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.merchant.MerchantClient;
//import com.wuyunbin.merchant.StoreClient;
import com.wuyunbin.merchant.entity.Store;
import com.wuyunbin.sso.SSOClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺入驻申请表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@Service
public class StoreApplyServiceImpl extends ServiceImpl<StoreApplyMapper, StoreApply> implements StoreApplyService {
    @Resource
    private HttpServletRequest request;
    @Resource
    private SSOClient ssoClient;

    @Resource
    private MerchantClient merchantClient;

//    @Resource
//    private StoreClient storeClient;
    @Override
    public void apply(StoreApply storeApply) {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        //merchantId和memberId一致
        String memberId = ssoClient.getMemberIdByToken();
        log.info("memberId:{}", memberId);
        storeApply.setMerchantId(memberId);
        this.save(storeApply);
    }

    @Override
    public void approval(StoreApplyDTO storeApplyDTO) {
        //为什么不能new，要从数据库读？
        StoreApply storeApply = this.getById(storeApplyDTO.getId());
        //判断  待审核
        if(!"0".equals(storeApply.getStatus())){
            throw new RuntimeException("申请单状态异常");
        }
        storeApply.setStatus(storeApplyDTO.getStatus());
        storeApply.setRemark(storeApplyDTO.getRemark());
        this.updateById(storeApply);

        //生成对应的店铺信息
        Store store=new Store();
        BeanUtils.copyProperties(storeApply,store);
        //对拷的过程中会把申请单的id拷到店铺id里
        store.setStoreApplyId(storeApply.getId());
        store.setStoreName(storeApply.getStoreName());
        store.setId(null);
        merchantClient.addStore(store);
    }
}
