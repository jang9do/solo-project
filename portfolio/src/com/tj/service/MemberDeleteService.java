package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;

public class MemberDeleteService implements Service { // 会員脱退をするService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mId = request.getParameter("mId");
		
		HttpSession session = request.getSession();
		
		int result = dao.delete(mId);
		
		if(result == MemberDao.SUCCESS) {
			session.removeAttribute("login"); // SESSIONからログイン情報を消す
			request.setAttribute("Msg", "�깉�눜 �꽦怨�");
		} else{
			request.setAttribute("Msg", "�깉�눜 �떎�뙣");
		}
	}

}
