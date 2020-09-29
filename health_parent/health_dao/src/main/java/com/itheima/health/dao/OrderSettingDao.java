package com.itheima.health.dao;

import com.itheima.health.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/26 20:48
 */
public interface OrderSettingDao {

    /*通过日期预约*/
    OrderSetting findByOrderDate(Date orderDate);

    /*更新可预约数量*/
    void updateNumber(OrderSetting orderSetting);

    /*添加预约*/
    void add(OrderSetting orderSetting);

    /*通过月份获取预约设置数据*/
    List<Map<String, Integer>> getOrderSettingBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
