package com.assets.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.assets.model.User;

public class LoginInterceptor implements  HandlerInterceptor  {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		HttpSession session = arg0.getSession();
		String uri = arg0.getRequestURI(); // ��ȡ��¼��uri������ǲ��������ص�
		//if(session.getAttribute("LOGIN_USER")!=null || uri.indexOf("system/login")!=-1) {// ˵����¼�ɹ� ���� ִ�е�¼����	
		System.err.println(uri);
		if(session.getAttribute("user")!=null) {
			// ��¼�ɹ�������
			User u=(User)session.getAttribute("user");
			System.err.println(u.toString());
			return true;
		}else {
			// ���غ�����¼ҳ��
			arg1.sendRedirect(arg0.getContextPath()+"/system/login.do");
			return false;
		}
	}

}
