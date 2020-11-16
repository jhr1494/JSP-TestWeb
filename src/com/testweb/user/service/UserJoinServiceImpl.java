package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserJoinServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("hp1") +"-"+ request.getParameter("hp2") +"-"+ request.getParameter("hp3")  ;
		String email = request.getParameter("email1") + "@" +  request.getParameter("email2");
		String address_basic = request.getParameter("add_B");
		String address_detaile = request.getParameter("add_D");
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.checkId(id); //중복확인
		
		if(result == 0) {
			UserVO vo = new UserVO(id, pw, name, phone, email, address_basic, address_detaile);
			dao.join(vo);
			
			return 0;
		}else {
			return 1;
		}
		
		
		
	}

}
