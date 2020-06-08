package com.liyuan.demo.controller;

import com.liyuan.demo.domain.exception.MultiDataSourceException;
import com.liyuan.demo.domain.response.PageListResponse;
import com.liyuan.demo.domain.response.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.ObjectError;
import java.util.List;
public class BaseController {
    /**
     * 系统日志配置.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 未知异常，提示请求失败.
     */
    public static final String UNKNOWNEXCEPTION = "请求失败";
    /**
     * 未知异常，提示请求失败.
     */
    public static final String PARAMSVALIDFAIL = "参数错误！";
    /**
     * 默认成功的Status Code.
     */
    private static final int RESCODE_OK = 200;
    /**
     * 默认失败的Status Code.
     */
    private static final int RESCODE_FAIL = 201;

    /**
     * 默认成功返回数据封装：有返回数据对象 + 成功信息
     */
    protected ResponseEntity getSuccessResult(Object obj) {
        return getSuccessResult(RESCODE_OK, "操作成功", obj);
    }

    /**
     * 默认成功返回数据封装：没有返回数据对象
     *
     * @return
     */
    protected ResponseEntity getSuccessResult() {
        return getSuccessResult(null);
    }

    /**
     * 自定义成功返回数据封装：返回数据对象 + 成功信息 + 成功码
     *
     * @return
     */
    protected ResponseEntity getSuccessResult(int successCode, String msg, Object obj) {
        return new ResponseEntity("ok", successCode, msg, obj);
    }

    /**
     * 默认失败返回数据封装：只需提供错误信息
     *
     * @return
     */
    protected ResponseEntity getFailResult(String msg) {

        return this.getFailResult(RESCODE_FAIL, msg);
    }

    /**
     * 自定义失败返回数据封装:可自定义错误码+错误信息
     *
     * @param errCode
     * @param msg
     * @return
     */
    protected ResponseEntity getFailResult(int errCode, String msg) {
        return new ResponseEntity("fail", errCode, msg, Collections.EMPTY_MAP);
    }


    /**
     * 业务异常控制
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MultiDataSourceException.class)
    @ResponseBody
    public ResponseEntity ExceptionHandler(MultiDataSourceException e) {
        logger.warn(e.getLocalizedMessage());
        return this.getFailResult(e.getErrCode(), e.getMessage());
    }

    /**
     * 运行期异常控制
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public
    @ResponseBody
    ResponseEntity runtimeExceptionHandler(RuntimeException e) {
        logger.error("发生系统异常", e);
        return this.getFailResult("系统异常，请和管理员联系！");
    }
 /**
     *  校验错误拦截处理
     *
     * @param e 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public
    @ResponseBody
    ResponseEntity validationBodyException(MethodArgumentNotValidException e){

        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");

            });
            return this.getFailResult(errors.get(0).getDefaultMessage());

        }
        return this.getFailResult("请求参数校验失败");
    }

    /**
     * 参数类型转换错误
     *
     * @param e 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public
    @ResponseBody
    ResponseEntity parameterTypeException(HttpMessageConversionException e){

        logger.error(e.getCause().getLocalizedMessage());
        return this.getFailResult(e.getLocalizedMessage());

    }
    public <T> PageListResponse<T> getPageListResponse(Integer pageNum, Integer pageSize, Integer totalCount, List<T> dataList){
        PageListResponse<T> pageListResponse = new PageListResponse<T>(pageNum,pageSize,totalCount,dataList);
        return pageListResponse;
    }

}
