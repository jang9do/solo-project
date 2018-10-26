package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class LoginService implements Service { // ログインする為のService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDao dao = MemberDao.getInstance();
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		
		MemberDto dto = dao.login(mId, mPw);
		
		if(dto != null) {
			session.setAttribute("login", dto);
		} else {
			request.setAttribute("Msg", "�븘�씠�뵒 �샊�� 鍮꾨�踰덊샇瑜� �솗�씤�빐二쇱꽭�슂");
		}
	}

}
