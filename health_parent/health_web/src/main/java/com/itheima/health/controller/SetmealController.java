package com.itheima.health.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.utils.QiNiuUtils;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/25 20:27
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    private static final Logger log = LoggerFactory.getLogger(SetmealController.class);

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 上传图片
     * 【注意】此处方法的参数名必须与el-upload中的name的值要一致，大小写严格区分
     *  否则 imgFile=null
     */

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //- 获得原文件名
        String originalFilename = imgFile.getOriginalFilename();
        //- 截取原文件名来获取后缀名 .jpg
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 使用UUID生成唯一文件名 + 后缀名
        String uniqueName = UUID.randomUUID().toString() + extension;
        try {
            //- 调用QiNiuUtils上传文件
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),uniqueName);
            //- 响应结果给页面
            //  - 封装结果到map
            Map<String,String> resultMap = new HashMap<String,String>();
            //  - map有2个key
            //    - domain
            resultMap.put("domain",QiNiuUtils.DOMAIN);
            //    - imgName
            resultMap.put("imgName",uniqueName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,resultMap);
        } catch (IOException e) {
            //e.printStackTrace();
            log.error("上传图片失败",e);
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    /*
    * 添加套餐
    * */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        // 调用服务添加套餐
        Integer setmealId = setmealService.add(setmeal, checkgroupIds);
        // 添加redis生成静态页面的任务
        Jedis jedis = jedisPool.getResource();
        String key = "setmeal:static:html";
        jedis.sadd(key, setmealId+"|1|" + System.currentTimeMillis());
        jedis.close();
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }



    /*体验套餐分页*/
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
            //调用服务分页查询
        PageResult<Setmeal> pageResult = setmealService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }

    /*通过id查询套餐信息*/
    @GetMapping("/findById")
    public Result findBuId(int id){
        //调用服务查询
        Setmeal setmeal = setmealService.findById(id);
        //前端显示的图片需要全路径，封装到map中解决图片路径
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("setmeal",setmeal);
        resultMap.put("domain",QiNiuUtils.DOMAIN);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,resultMap);
    }

    /*通过id查询选中的检查组ids*/
      @GetMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id){
          List<Integer> checkgroupIds = setmealService.findCheckgroupIdsBySetmealId(id);
          return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroupIds);
      }

      /*修改*/
      @PostMapping("/update")
      public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
          // 调用服务更新套餐
          setmealService.update(setmeal,checkgroupIds);
          Jedis jedis = jedisPool.getResource();
          String key = "setmeal:static:html";
          jedis.sadd(key, setmeal.getId()+"|1|" + System.currentTimeMillis());
          jedis.close();
          return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
      }

    /*删除套餐*/
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        // 调用服务删除套餐
        setmealService.deleteById(id);
        Jedis jedis = jedisPool.getResource();
        String key = "setmeal:static:html";
        jedis.sadd(key, id+"|0|" + System.currentTimeMillis());
        jedis.close();
        return new Result(true, "删除套餐成功!");
    }
}








