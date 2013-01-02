package com.GroupTree.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("interceptor-destory");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("interceptor-init");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		return arg0.invoke();
	}

//	@Override
//	public String intercept(ActionInvocation invocation) throws Exception {
//		 ActionContext actionContext = invocation.getInvocationContext();
//		  HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
//		  
//		  Map<String, Object> session = actionContext.getSession();
//
//		  if(actionContext.getName().compareTo("login") == 0){
//			  String result = invocation.invokeActionOnly();
//			  if(result.compareTo("failToLogin") == 0){
//				request.setAttribute("stat", "wrong");
//				request.setAttribute("message", "User name or password is not right");
//				return result;
//			  }
//			  else
//				  return result;
//		  }
//		  if (session != null && session.get("username") != null){
//			  return invocation.invoke();
//		  }
//		request.setAttribute("stat", "wrong");
//		request.setAttribute("message", "Too long time or not login, please retry");
//		return "failToLogin";
//		
//	}


}
