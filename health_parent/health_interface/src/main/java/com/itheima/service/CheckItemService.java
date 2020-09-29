package com.itheima.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/19 17:42
 */
public interface CheckItemService {
    /*查询所有项*/
    List<CheckItem> findAll();

    /*添加检查项*/
    void add(CheckItem checkItem);

    /*条件分页查询*/
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /*通过id查询*/
    CheckItem findById(int id);

    /*修改检查项*/
    void update(CheckItem checkitem);

    /*通过id删除*/
    void deleteById(int id);
}
