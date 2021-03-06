package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface CheckGroupDao {
    /**
     * 添加检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系
     * 参数的类型相同时，要取别名，或放入map
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 条件查询
     * @param queryString
     * @return
     */
    Page<CheckGroup> findPage(String queryString);

    /*通过id查询*/
    CheckGroup findById(int id);

    /*通过检查组的id查询选中的检查项id集合*/
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /*更新检查组关系*/
    void update(CheckGroup checkGroup);

    /*删除检查组与检查项的关系*/
    void deleteCheckGroupCheckItem(Integer id);

    /*通过检查组id统计个数* */
    int findSetmealCountByCheckGroupId(int id);

    /*通过id删除*/
    void deleteById(int id);

    /*查询所有套餐*/
    List<CheckGroup> findAll();
}
