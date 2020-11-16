package com.testweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.testweb.board.model.BoardDAO;
import com.testweb.board.model.BoardVO;
import com.testweb.user.model.UserVO;
import com.testweb.util.PageVO;

public class UserListServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
			
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");

		
		
		if(vo != null) {
			
			//페이징
			int pNum = 1;
			int amount = 5;
			String id = vo.getId();
			System.out.println("userlist : " + id);
			
			if(request.getParameter("pNum") != null || request.getParameter("amount") != null) {
				pNum = Integer.parseInt(request.getParameter("pNum"));
				amount = Integer.parseInt(request.getParameter("amount"));
			}
			
			ArrayList<BoardVO> userList = dao.UserList(pNum, amount, id);
			int total = dao.getUserTotal(id);
			PageVO pVo = new PageVO(pNum, amount, total);
			//System.out.println("리스트 : " + userList.toString());
			request.setAttribute("userList", userList);
			request.setAttribute("uPVO", pVo);
			 
			
			
		}else {
			vo = null;
		}
		
		
	}



}
