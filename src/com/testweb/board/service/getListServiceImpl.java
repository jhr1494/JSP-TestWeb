package com.testweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.model.BoardDAO;
import com.testweb.board.model.BoardVO;
import com.testweb.util.PageVO;

public class getListServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		//페이징
		int pNum = 1;
		int amount = 5;
		
		if(request.getParameter("pNum") != null || request.getParameter("amount") != null) {
			pNum = Integer.parseInt(request.getParameter("pNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		ArrayList<BoardVO> list = dao.getList(pNum, amount);
		int total = dao.getTotal();
		PageVO pVo = new PageVO(pNum, amount, total);
		
		request.setAttribute("list", list);
		request.setAttribute("PageVO", pVo);

	}

}
