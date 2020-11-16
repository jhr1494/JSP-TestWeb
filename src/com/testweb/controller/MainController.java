package com.testweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.service.BoardService;
import com.testweb.board.service.getListServiceImpl;


@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MainController() {
      
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
		
		String commend = uri.substring(conPath.length()); 
		
		System.out.println(commend);
		
		BoardService service;
		
		if(commend.equals("/")) {
			service = new getListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
	

}
