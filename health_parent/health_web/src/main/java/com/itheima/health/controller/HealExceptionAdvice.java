package com.itheima.health.controller;



import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.itheima.health.HealthException;
import com.itheima.health.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/22 18:26
 */
/*全局异常处理*/
@RestControllerAdvice
public class HealExceptionAdvice {
    /*
    * info:打印日子，记录流程性的内容
    * debug:记录一些重要的数据 id, orderId, userId
    * error:记录异常的堆栈信息，代替e.printStackTrace();
    * 工作中不能有System.out.print(),e.printStackTrace();
    * */
    private static final Logger log = LoggerFactory.getLogger(HealExceptionAdvice.class);
    /*自定义抛出的异常处理*/
    @ExceptionHandler()
    public Result handleHealthException(HealthException e){
        log.error("违反业务逻辑",e);
        //这个是自己抛出的异常，把异常信息包装下返回即可
        return new Result(true,e.getMessage());
    }

    /*所有未知的异常处理*/
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error("发生未知异常",e);
        return new Result(false,"发生未知错误，操作失败，请联系管理员");
    }
}
