package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AjaxResult<T> {
    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 成功与否
     */
    private Boolean success;

    /**
     * 消息提示
     */
    private String msg;

    /**
     * 错误描述
     */
    private String errDesc;

    /**
     * 用户token
     */
    private String xdxToken;

    public AjaxResult() {
    }

    /**
     * 操作失败
     * @param errDesc 错误信息
     */
    public static AjaxResult<?> failure(String errDesc) {
        return new AjaxResult<>().setErrDesc(errDesc).setSuccess(false);
    }

    /**
     * 操作成功
     * @param msg  返回消息
     * @param total 总条数
     * @param data 返回的数据
     */
    public static <T> AjaxResult<T> success(String msg, Integer total, T data){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(true)
                .setTotal(total)
                .setMsg(msg);
        return result;
    }

    /**
     * 操作成功
     * @param total 总条数
     * @param data 返回的数据
     */
    public static <T> AjaxResult<T> success(T data, Integer total){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(true)
                .setTotal(total)
                .setMsg("操作成功")
                .setData(data);
        return result;
    }

    /**
     * 操作成功
     * @param data 返回的数据
     */
    public static <T> AjaxResult<T> success(T data){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(true)
                .setMsg("操作成功")
                .setData(data);
        return result;
    }

    /**
     * 操作成功
     * @param msg  返回消息
     */
    public static <T> AjaxResult<T> success(String msg){
        return success(msg,0,null);
    }

    /**
     * 操作成功
     * @param msg  返回消息
     * @param total 总条数
     */
    public static <T> AjaxResult<T> success(String msg, Integer total){
        return success(msg,total,null);
    }

    /**
     * 操作成功
     */
    public static <T> AjaxResult<T> success(){
        return success("操作成功",0,null);
    }

}
