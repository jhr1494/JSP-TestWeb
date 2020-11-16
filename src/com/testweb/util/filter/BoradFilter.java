package com.testweb.util.filter;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserVO;

@WebFilter({ "/board/write.board", "/board/writeForm.board" })
public class BoradFilter implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		if(user == null) {
			res.setContentType("text/html; charset=UTF-8"); //응답문서설정
			PrintWriter out = res.getWriter();
			out.println(" <script> ");
			out.println(" alert('로그인이 필요한 서비스 입니다'); ");
			out.println(" location.href='/TestWeb/user/login.user'; "); //로그인 화면
			out.println(" </script> ");
			
			return;
		
		
		}	
		chain.doFilter(request, response);
	}
	
}
