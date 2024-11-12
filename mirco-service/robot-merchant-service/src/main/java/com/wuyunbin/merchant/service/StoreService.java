package com.wuyunbin.merchant.service;

import com.wuyunbin.merchant.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
public interface StoreService extends IService<Store> {

    void saveStore(Store store);
}
