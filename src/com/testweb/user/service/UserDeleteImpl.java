package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserDeleteImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String pw = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getId();
		
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo2 = dao.login(id, pw);
		
		
		if(vo2 != null) {
			dao.delete(id);
			session.invalidate();
		
			return 1;
		}else {
			return 0;
		}

	}
	
	
	

}
