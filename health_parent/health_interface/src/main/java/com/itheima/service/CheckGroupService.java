package com.itheima.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.sun.xml.internal.ws.handler.HandlerException;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /*通过id查询*/
    CheckGroup findById(int id);

    /*通过检查组的id查询选中的检查项id集合*/
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /*修改检查组*/
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /*通过id删除*/
    void deleteById(int id) throws HandlerException;

    /*查询所有套餐*/
    List<CheckGroup> findAll();

}
