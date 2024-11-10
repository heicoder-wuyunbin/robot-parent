package com.wuyunbin.apply.service.impl;

import com.wuyunbin.apply.entity.MerchantApply;
import com.wuyunbin.apply.mapper.MerchantApplyMapper;
import com.wuyunbin.apply.service.MerchantApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.sso.SSOClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家入驻申请表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Slf4j
@Service
public class MerchantApplyServiceImpl extends ServiceImpl<MerchantApplyMapper, MerchantApply> implements MerchantApplyService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private SSOClient ssoClient;

    @Override
    public void apply(MerchantApply merchantApply) {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        String memberId = ssoClient.getMemberIdByToken(token);
        log.info("memberId:{}", memberId);
        merchantApply.setMemberId(memberId);
        this.save(merchantApply);
    }
}
