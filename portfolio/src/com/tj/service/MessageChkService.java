package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MessageDao;
import com.tj.dto.MemberDto;

public class MessageChkService implements Service { // 読んだMessageをチェックするService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("login")!=null) {
			MemberDto dto = (MemberDto)session.getAttribute("login");
			MessageDao mesDao = MessageDao.getInstance();
			int result = mesDao.noReadChk(dto.getmId());
			if(result!=MessageDao.FAIL) {
				request.setAttribute("readMsg", result);
			}
		}
	}

}
