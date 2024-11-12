package com.wuyunbin.apply.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.wuyunbin.apply.dto.MerchantApplyDTO;
import com.wuyunbin.apply.entity.MerchantApply;
import com.wuyunbin.apply.mapper.MerchantApplyMapper;
import com.wuyunbin.apply.service.MerchantApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.merchant.MerchantClient;
import com.wuyunbin.merchant.entity.MerchantInfo;
import com.wuyunbin.sso.SSOClient;
import com.wuyunbin.sso.entity.Merchant;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private MerchantClient merchantClient;

    @Override
    public void apply(MerchantApply merchantApply) {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        String memberId = ssoClient.getMemberIdByToken();
        log.info("memberId:{}", memberId);
        merchantApply.setMemberId(memberId);
        this.save(merchantApply);
    }

    @Override
    public void approval(MerchantApplyDTO merchantApplyDTO) {
        MerchantApply merchantApply = this.getById(merchantApplyDTO.getId());
        merchantApply.setId(merchantApplyDTO.getId());

        //判断  待审核
        if(!"0".equals(merchantApply.getStatus())){
            throw new RuntimeException("申请单状态异常");
        }
        merchantApply.setStatus(merchantApplyDTO.getStatus());
        merchantApply.setRemark(merchantApplyDTO.getRemark());
        this.updateById(merchantApply);
        //生成对应的商户登录信息 sso

        Merchant merchant=new Merchant();
        merchant.setId(merchantApply.getMemberId());
        merchant.setPhone(merchantApply.getPhone());
        //截取身份证的后6位或者手机号后6位，推荐用身份证
        merchant.setPassword(merchantApply.getIdCard().substring(merchantApply.getIdCard().length()-6));

        ssoClient.save(merchant);
        //生成对应的商户信息非登录信息 merchant_info表
        MerchantInfo merchantInfo=new MerchantInfo();
        //todo 对拷
        BeanUtils.copyProperties(merchantApply,merchantInfo);
        merchantInfo.setId(merchant.getId());
        merchantClient.save(merchantInfo);
    }
}
