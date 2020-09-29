package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/21 17:34
 */
public interface CheckItemDao {
    /*
    查询所有
    * */
    List<CheckItem> findAll();

    /*添加检查项，新增检查页面的新建功能*/
    void add(CheckItem checkItem);

    /*分页条件查询*/
    Page<CheckItem> findByCondition(String queryString);

    /*通过id查询*/
    CheckItem findById(int id);

    /*修改检查项*/
    void update(CheckItem checkitem);

    /*通过id删除*/
    int findCountByCheckItemId(int id);
    void deleteById(int id);
}
