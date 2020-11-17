package com.testweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.board.service.BoardService;
import com.testweb.board.service.ContentServiceImpl;
import com.testweb.board.service.DeleteServiceImpl;
import com.testweb.board.service.UpdateServiceImpl;
import com.testweb.board.service.UserListServiceImpl;
import com.testweb.board.service.WriteServiceImpl;
import com.testweb.board.service.getListServiceImpl;
import com.testweb.user.model.UserVO;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);	
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String commend = uri.substring(conPath.length()); 
		
		System.out.println(commend);
		
		BoardService service;
		
		if(commend.equals("/board/list.board")) { //글목록요청
			service = new getListServiceImpl();
			service.execute(request, response);
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			
			
			
		}else if(commend.equals("/board/write.board")) { //작성화면
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
		
		}else if(commend.equals("/board/writeForm.board")) {//등록요청
			
			service = new WriteServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("/board/list.board").forward(request, response);
			
		}else if(commend.equals("/board/content.board")) {//글 상세보기 요청
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
			
		}else if(commend.equals("/board/modify.board")) {//글 수정 화면
			
				service = new ContentServiceImpl();
				service.execute(request, response);
				
				request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
	
		}else if(commend.equals("/board/modifyForm.board")) {//글 수정 요청
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
			
		}else if(commend.equals("/board/delete.board")) {//글삭제
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect(request.getContextPath());
			
			
		}else if(commend.equals("/user/userList.board")) {//유저리스트
			service = new UserListServiceImpl();
			service.execute(request, response);
			
	
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			
			
		}
		
		
		 
		 
	}//end dispatchServlet

}
