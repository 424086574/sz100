package com.itheima.health.service.impl;

import com.itheima.health.HealthException;
import org.springframework.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/19 17:44
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询 所有检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     *
     * @param checkitem
     */
    @Override
    public void add(CheckItem checkitem) {
        checkItemDao.add(checkitem);
    }



    /*条件分页查询*/
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 使用PageHelper分页插件
        // 检查配置了插件没有sqlMapConfig.xml  sqlSessionFactoryBean
        // 放入threadlocal 页码及大小 Page
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 判断是否有查询条件，有则要拼接%
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 紧接着的查询语句会被分页, 从线程中获取threadlocal 页码与大小, total放入threadlocal Page
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        // 防止数据丢失Page属性用的是基础数据类型，没有实现序列化
        // 这一行代码是让web和service分开，将来想要换web层的代码不会影响到service层，官方一点代码解耦
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(),page.getResult());
        return pageResult;
    }

    /*通过id查询*/
    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    /*修改检查项*/
    @Override
    public void update(CheckItem checkitem) {
        checkItemDao.update(checkitem);
    }

    /*通过id删除*/
    @Override
    public void deleteById(int id) {
        //判断是否被检查组使用了
        //统计个数检查组与检查项的关系表 条件检查的id=id
        int count = checkItemDao.findCountByCheckItemId(id);
        //被用了就要报错
        if(count>0){
            throw new HealthException("该检查项被检查组使用了，不能删除");
        }
        //没被用就可以删除
        checkItemDao.deleteById(id);
    }
}