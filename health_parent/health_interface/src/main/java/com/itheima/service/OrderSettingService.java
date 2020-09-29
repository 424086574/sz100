package com.itheima.service;

import com.itheima.health.HealthException;
import com.itheima.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/26 20:45
 */
public interface OrderSettingService {

    /*批量导入*/
    void add(List<OrderSetting> orderSettingList);

    /*日历展示预约设置*/
    List<Map<String, Integer>> getOrderSettingByMonth(String month);

    /**
     * 基于日期的预约设置
     * @param os
     */
    void editNumberByDate(OrderSetting os) throws HealthException;
}
