package com.assets.Interceptor;

import java.awt.Checkbox;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.assets.dao.RoleMapper;
import com.assets.model.User;
import com.assets.tool.Utils;

public class PermissionInterceptor implements HandlerInterceptor {
	
	
	public boolean checkpower(String power,String operation){
		if (power.contains(operation)) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		String power=(String) session.getAttribute("power");
		String operatoin="";
		// 
		System.err.println(power);
		if (url.indexOf("/insert")!=-1) {
			System.err.println("add");
			operatoin="增加";
		}
		if (url.indexOf("/del")!=-1||url.indexOf("/batchDelete")!=-1) {
			System.err.println("del");
			operatoin="删除";
		}
		if (url.indexOf("/update")!=-1) {
			System.err.println("update");
			operatoin="更改";
		}
		if (url.indexOf("/get")!=-1) {
			System.err.println("get");
			operatoin="查询";
		}
		if (!operatoin.equals("")&&operatoin!=null) {
			if (checkpower(power,operatoin)) {
				System.err.println("权限通过!");
				return true;
			}else{
				System.err.println("权限验证失败!");
				String jsonStr ="{\"code\":\"1002\",\"msg\":\"缺少权限\"}";	
				Utils.commend(response, jsonStr);
				return false;
			}
		}else{	
			return true;
		}
		
 /*
		// 校验是否是公开资源地址
		List<String> open_urls = ResourcesUtil.gekeyList("anonymousURL");
 
		for (String open_url : open_urls) {
			if (url.indexOf(open_url) >= 0) {
				// 公开地址放行
				return true;
			}
		}
		//判断是否是公共访问地址
		List<String> common_urls = ResourcesUtil.gekeyList("commonURL");
 
		for (String common_url : common_urls) {
			if (url.indexOf(common_url) >= 0) {
				//公共地址放行
				return true;
			}
		}
 
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		// 获取用户权限列表
		List<SysPermission> permission_list = activeUser.getPermissions();
		// 校验用户访问地址是否在用户权限范围内
		for (SysPermission sysPermission : permission_list) {
			String permission_url = sysPermission.getUrl();
			if (url.contains(permission_url)) {
				return true;
			}
		}*/
		
	
	}
 
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
 
	}
 
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
}