package com.test.demo.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.demo.exception.ReEnumException;

/**
 * 统一返回信息
 * 
 * @author ASUS 创建时间 2017年8月26日 下午1:51:31
 *
 */
public class BaseJson {

	/** 日志处理 **/
	private static Logger baseServiceLog = LoggerFactory.getLogger(BaseJson.class);

	/**
	 * 成功编码
	 */
	private static final Integer SUCCESS_CODE = 100;

	/**
	 * 统一失败编码
	 */
	private static final Integer FAIL_COMMON_CODE = 200;

	/**
	 * 异常失败编码
	 */
	private static final Integer FAIL_EXCEPTION_CODE=201;
	
	/**
	 * 
	 * @param linkedHashMap-响应信息
	 * @return 统一返回成功信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map returnSuccessInfo(Map linkedHashMap) {
		Map result = new LinkedHashMap(5);
		result.put("success", SUCCESS_CODE);// 1成功
		result.put("successResult", linkedHashMap);
		return result;
	}

	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// public Map returnSuccessInfo_(LinkedHashMap linkedHashMap){
	// Map result = new LinkedHashMap(5);
	// result.put("respCode","000000" );//响应码
	// result.put("respDescription","操作成功");//响应信息
	// result.put("list", linkedHashMap);
	// return result;
	// }

	/**
	 * 
	 * @param linkedHashMap-响应信息
	 * @return 统一返回成功信息 将所需的list 返回
	 */
	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// public Map returnSuccessInfoList(List linkedHashMap){
	// Map result = new LinkedHashMap(5);
	// result.put("respCode","000000" );//响应码
	// result.put("respDescription","操作成功");//响应信息
	// result.put("list", linkedHashMap);
	// return result;
	// }

	/**
	 * 
	 * @param linkedHashMap-响应信息
	 * @return 统一返回成功信息 将所需的String返回
	 */
	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// public Map returnSuccessInfoString(String linkedHashMap){
	// Map result = new LinkedHashMap(5);
	// result.put("respCode","000000" );//响应码
	// result.put("respDescription","开户成功");//响应信息
	// result.put("acctNo", linkedHashMap);
	// return result;
	// }

	/**
	 * 
	 * @param linkedHashMap-响应信息
	 * @return 统一处理失败信息
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map returnFailtrueInfo(Exception e) {
		LinkedHashMap resultMap = new LinkedHashMap<>();
		
		if(e instanceof ReEnumException) {
			BaseCode code =((ReEnumException) e).getCode();
			resultMap.put("code", code.getCode());
			resultMap.put("msg", code.getMsg());
			resultMap.put("success", FAIL_EXCEPTION_CODE);
		}else {
			resultMap.put("success", FAIL_COMMON_CODE);
		}

		return resultMap;
	}

	// public Map returnFailtrueInfo(Exception e) {
	//
	// Map result = new LinkedHashMap(5);
	// LinkedHashMap linkedHashMap = new LinkedHashMap();
	//
	// 如果是自定义异常
	// if(e instanceof OnigiriException){
	// ReturnCodeType returnCodeType = ((OnigiriException)e).getRetCodeType();
	// linkedHashMap.put("retCode",returnCodeType.getRetCode() );//响应码
	// linkedHashMap.put("retInfo",returnCodeType.getRetInfo());//响应信息
	// // linkedHashMap.put("retCodeInfo",returnCodeType.getRetCodeInfo());//实际异常信息
	//
	// result.put("success", "2");//发生业务异常 - 有提示信息
	// }else if(e instanceof RbRetException){
	// RbReturnCodeType rbReturnCodeType =
	// ((RbRetException)e).getRbReturnCodeType();
	// linkedHashMap.put("retCode",rbReturnCodeType.getRetCode() );//响应码
	// linkedHashMap.put("retInfo",rbReturnCodeType.getRetInfo());//响应信息
	//
	// result.put("success", "2");//发生业务异常 - 有提示信息(接口调用返回异常)
	// }else {
	// result.put("success", "3");//系统发生异常
	// }
	// // 系统异常 具体异常还没有处理 需要处理的时候再扩建这个方法
	// result.put("success", FAIL_CODE);
	// // 具体返回信息
	// result.put("iqbresult", linkedHashMap);
	// return result;
	// }

	/**
	 * 处理验证信息
	 * 
	 * @param br
	 * @throws Exception
	 */
	// public void validateIPInfo(BindingResult br) throws OnigiriException {
	// if (br.hasFieldErrors()) { // 判断验证是否出错
	// List<FieldError> fes = br.getFieldErrors();
	// for (FieldError fe : fes) {
	// //记录日志
	// String erroInfo = new
	// StringBuffer("异常信息---对象：").append(fe.getObjectName()).append("属性 : ")
	// .append(fe.getField()) .append(" :"
	// ).append(fe.getDefaultMessage()).toString();
	// baseServiceLog.error(erroInfo);
	// throw new OnigiriException(erroInfo);
	// }
	// }
	// }
}
