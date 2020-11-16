package com.testweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.board.service.BoardService;
import com.testweb.board.service.UserListServiceImpl;
import com.testweb.user.service.UserDeleteImpl;
import com.testweb.user.service.UserJoinServiceImpl;
import com.testweb.user.service.UserLoginServiceImpl;
import com.testweb.user.service.UserService;
import com.testweb.user.service.UserUpdateImpl;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String command = uri.substring(conPath.length());
		
		System.out.println(command);	
		
		
		UserService service;
		BoardService service2;
		
		
		//기능별 화면처리
		
		if(command.equals("/user/join.user")) { //회원가입 화면
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
			
		}else if(command.equals("/user/joinForm.user")) { //가입요청처리
			
			service = new UserJoinServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 1) {
				request.setAttribute("msg", "이미존재하는 회원입니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else {
				response.sendRedirect("login.user");
			}
			
		}else if(command.equals("/user/login.user")) { //로그인 화면
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		
		}else if(command.equals("/user/loginForm.user")) {//로그인 요청
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);
			
			service2 = new UserListServiceImpl();
			service2.execute(request, response);
			
			if(result == 1) {
				
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
				
			}else {
				request.setAttribute("msg", "아이디, 비밀번호를 확인해주세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
				
			}
			
		}else if(command.equals("/user/mypage.user")) {//마이페이지화면
			
			service2 = new UserListServiceImpl();
			service2.execute(request, response);
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			
		}else if(command.equals("/user/update.user")) {//정보수정화면
			request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			
		}else if(command.equals("/user/updateForm.user")) {//정보수정요청
			service = new UserUpdateImpl();
			int result = service.execute(request, response);
			
			if(result == 1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println(" alert('업데이트가 정상 처리 되었습니다'); ");
				out.println(" location.href = 'mypage.user' ");
				out.println("</script>");
				
			}else {
				response.sendRedirect("mypageinfo.user");
				
			}
			
		}else if(command.equals("/user/delete.user")) { //회원탈퇴요청
			service = new UserDeleteImpl();
			int result = service.execute(request, response);
			
			service2 = new UserListServiceImpl();
			service2.execute(request, response);
			
			if(result == 1) {
				response.sendRedirect("login.user");
			}else {
				request.setAttribute("msg", "비밀번호를 확인해주세요");
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			}
			
		
		}else if(command.equals("/user/logout.user")) { //로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			
			
			response.sendRedirect(request.getContextPath());
			
			
		}
			
			
			
			
	}//end dispatchServlet
	
	
	
	
	


}
