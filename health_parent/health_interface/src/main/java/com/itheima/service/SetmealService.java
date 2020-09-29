package com.itheima.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/25 20:54
 */
public interface SetmealService {

    /*添加套餐*/
    Integer add(Setmeal setmeal, Integer[] checkgroupIds);

    /*体验套餐分页*/
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /*通过id查询套餐信息*/
    Setmeal findById(int id);

    /*通过id查询选中的检查组ids*/
    List<Integer> findCheckgroupIdsBySetmealId(int id);


    /*更新套餐*/
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /*删除套餐*/
    void deleteById(Integer id);

    /*查询数据库中所有的图片*/
    List<String> findImgs();

    /*查询所有*/
    List<Setmeal> findAll();

    /*查询套餐详情*/
    Setmeal findDetailById(int id);

    /*查询套餐详情*/
    Setmeal findDetailById2(int id);
}
