package com.testweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebFilter({ "/board/modify.board", "/board/update.board", "/board/delete.board" })
public class BoradFilter2 implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		if(user == null) {
			res.sendRedirect("location.href='/TestWeb/user/login.user'");
			return;
		}
		
		String id = user.getId();
		String writer = req.getParameter("writer");
		System.out.println(id);
		System.out.println("넘어온 id값 : " + writer);
		

			
		if(writer == null || !id.equals(writer)) {

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println(" <script> ");
			out.println(" alert('권한이 없습니다'); ");
			out.println(" location.href='/TestWeb/board/list.board'; "); 
			out.println(" </script> ");

			return;
		}

		
		chain.doFilter(request, response);
		
	}
	
}
