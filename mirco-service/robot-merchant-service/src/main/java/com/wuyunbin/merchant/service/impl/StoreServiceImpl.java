package com.wuyunbin.merchant.service.impl;

import com.wuyunbin.merchant.entity.Store;
import com.wuyunbin.merchant.mapper.StoreMapper;
import com.wuyunbin.merchant.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void saveStore(Store store) {
        this.save(store);
        //加入到redis的list中
        redisTemplate.opsForList().leftPush("store",store);
    }
}
