package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/18 21:58
 */

/*查询所有*/
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll(){
        // 调用服务查询所有的检查项
        List<CheckItem> list = checkItemService.findAll();
        // 封装返回的结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }
    /*
    * 添加检查项  就是检查项的新建功能
    * */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        //调用服务添加检查项，不报错就是成功
        checkItemService.add(checkItem);
        //返回操作的结果
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /*分页条件查询*/
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用业务来分页，获取分页结果
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);

        // 返回给页面, 包装到Result, 统一风格
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }


    /*通过id查询*/
    @GetMapping("/findById")
    public Result findById(int id){
        //调用服务通过id查询检查项信息
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    /*修改检查项*/
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkitem){
        //调用服务修改检查项
        checkItemService.update(checkitem);
        //返回操作结果
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /*通过id删除*/
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        //调用服务删除
        checkItemService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
    
}
