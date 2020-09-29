package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/25 21:03
 */
public interface SetmealDao {

    /*添加套餐*/
    void add(Setmeal setmeal);

    /**/
    void addSetmealCheckgroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /*体验套餐分页*/
    Page<Setmeal> findByCondition(String queryString);

    /*
     * 通过id查询套餐信息
     * */
    Setmeal findById(int id);

    /*通过id查询选中的检查组ids*/
    List<Integer> findCheckgroupIdsBySetmealId(int id);

    /*更新套餐*/
    void update(Setmeal setmeal);

    /*删除就关系*/
    void deleteSetmealCheckGroup(Integer id);

    /*删除套餐*/
    void deleteById(Integer id);

    /*通过id查询套餐信息*/
    int findOrderCountBySetmealId(Integer id);

    /*查询数据库套餐中的所有图片*/
    List<String> findImgs();

    /*查询所有*/
    List<Setmeal> findAll();

    /*查询套餐详情*/
    Setmeal findDetailById(int id);

    /*查询套餐详情*/
    Setmeal findDetailById2(int id);
}
