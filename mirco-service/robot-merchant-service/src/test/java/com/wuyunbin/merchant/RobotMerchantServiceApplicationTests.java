package com.wuyunbin.merchant;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RobotMerchantServiceApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //opsForGeo()处理地理位置信息的
        redisTemplate.opsForGeo().add("stories",new Point(118.11789, 24.479466),"麦当劳(湖滨东路餐厅)");
        redisTemplate.opsForGeo().add("stories",new Point(118.088762, 24.467328), "陶乡涮涮锅(厦禾店)");
        redisTemplate.opsForGeo().add("stories",new Point(118.163978, 24.483079), "汕头八合里海记牛肉店(加州店)");
        redisTemplate.opsForGeo().add("stories",new Point(118.038997, 24.485467), "海底捞火锅(阿罗海城市广场店)");
        redisTemplate.opsForGeo().add("stories",new Point(118.178674, 24.49141), "必胜客(金山路餐厅)");
        redisTemplate.opsForGeo().add("stories",new Point(120.178664, 24.49341), "xxx");
        //确定用户在什么位置？（确定圆心 118.129715, 24.49874) 圆心位置来自微信小程序的定位功能
        Circle circle = new Circle(new Point(118.088762, 24.467328), new Distance(10, Metrics.KILOMETERS));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs().includeDistance()
                .includeCoordinates().sortAscending().limit(100);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results=redisTemplate.opsForGeo().radius("stories",circle,args);

        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            RedisGeoCommands.GeoLocation<String> location = result.getContent();
            String name = location.getName();
            Distance distance = result.getDistance();
            System.out.println("Name: " + name + ", Distance: " + distance);
        }
    }

}
