//package com.wuyunbin.sso.common;
//
//import lombok.Data;
//
///**
// * @author wuyunbin
// */
//@Data
//public class Result<T> {
//    private T data;
//    private int errorCode;
//    private String message;
//    private boolean success;
//
//    public static <T> Result<T> error(String message) {
//        Result<T> result = new Result<>();
//        result.setErrorCode(-1);
//        result.setMessage(message);
//        result.setSuccess(false);
//        return result;
//    }
//
//    public static <T> Result<T> success() {
//        return success(null);
//    }
//
//    public static <T> Result<T> success(T data) {
//        Result<T> result = new Result<>();
//        result.setErrorCode(20000);
//        result.setData(data);
//        result.setSuccess(true);
//        result.setMessage("success");
//        return result;
//    }
//}
