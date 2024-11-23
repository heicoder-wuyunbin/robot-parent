package com.wuyunbin.merchant.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyunbin.merchant.dto.StorePageQueryDTO;
import com.wuyunbin.merchant.entity.Store;
import com.wuyunbin.merchant.mapper.StoreMapper;
import com.wuyunbin.merchant.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveStore(Store store) {
        this.save(store);
        //加入到redis的list中
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", store.getStoreName());
        map.put("id", store.getId());
        map.put("cover_image", store.getCoverImage());
        map.put("score", store.getScore());
        redisTemplate.opsForGeo().add("stories", new Point(store.getLongitude(), store.getLatitude()), JSON.toJSONString(map));
    }

    @Override
    public IPage<Store> getPage(StorePageQueryDTO storePageQueryDTO) {
        IPage<Store> pageInfo = new Page<>(storePageQueryDTO.getCurrent(), storePageQueryDTO.getPageSize());
        IPage<Store> page = this.page(pageInfo);
        return page;
    }

    @Override
    public IPage<Store> getNearbyPage(StorePageQueryDTO storePageQueryDTO) {
        IPage<Store> pageInfo = new Page<>(storePageQueryDTO.getCurrent(), storePageQueryDTO.getPageSize());
        Circle circle = new Circle(
                new Point(storePageQueryDTO.getLongitude(), storePageQueryDTO.getLatitude()),
                new Distance(storePageQueryDTO.getDistance(), Metrics.KILOMETERS)
        );
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs().includeDistance()
                .includeCoordinates().sortAscending().limit(100);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius("stories", circle, args);

        if (results == null) {
            pageInfo = this.page(pageInfo);
        }

        List<Store> storeList = new ArrayList<>();
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            RedisGeoCommands.GeoLocation<String> location = result.getContent();
            String name = location.getName();
            Store store = JSON.parseObject(name, Store.class);
            storeList.add(store);
            pageInfo.setRecords(storeList);

        }
        return pageInfo;
    }
}
