package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/25 20:55
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements com.itheima.service.SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    /*添加套餐*/
    @Override
    @Transactional
    public Integer add(Setmeal setmeal, Integer[] checkgroupIds) {
        //先添加套餐
        setmealDao.add(setmeal);
        //获取新增的套餐的id
        Integer setmealId = setmeal.getId();
        //遍历选中的检查组id，添加套餐和检查组的关系
        if(null!=checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckgroup(setmealId,checkgroupId);
            }
        }
        return setmealId;
    }

    /*体验套餐分页*/
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //查询条件
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //条件查询 该查询语句会被分页
        Page<Setmeal> page = setmealDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Setmeal>(page.getTotal(),page.getResult());
    }

    /*
     * 通过id查询套餐信息
     * */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    /*通过id查询选中的检查组ids*/
    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(int id) {
        return setmealDao.findCheckgroupIdsBySetmealId(id);
    }

    /*更新套餐*/
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //先更新套餐信息
        setmealDao.update(setmeal);
        //删除旧关系
        setmealDao.deleteSetmealCheckGroup(setmeal.getId());
        //添加新关系
        if(null!=checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckgroup(setmeal.getId(),checkgroupId);
            }
        }
    }

    /*删除套餐*/
    @Override
    @Transactional
    public void deleteById(Integer id) {
        //是否存在订单，如果存在则不能删除
        int count = setmealDao.findOrderCountBySetmealId(id);
        if(count>0){
            //已经有订单使用了这个套餐，不能删除
            throw new HealthException("已经有订单使用了这个套餐，不能删除！");
        }
        //先删除套餐与检查组的关系
        setmealDao.deleteSetmealCheckGroup(id);
        //再删除套餐
        setmealDao.deleteById(id);
    }

    /*查询数据库中所有的图片*/
    @Override
    public List<String> findImgs() {
        return setmealDao.findImgs();
    }

    /*查询所有*/
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    /*查询套餐详情*/
    @Override
    public Setmeal findDetailById(int id) {
        return setmealDao.findDetailById(id);
    }

    /*查询套餐详情*/
    @Override
    public Setmeal findDetailById2(int id) {
        return setmealDao.findDetailById2(id);
    }


}
