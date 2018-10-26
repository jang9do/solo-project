package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MemberViewService implements Service { // 会員の詳細情報を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		ArrayList<MemberDto> dtos = null;
		if(request.getParameter("mId")!=null) {
			String mId = request.getParameter("mId");
			dtos = dao.search(mId, "");
		} else {
			String mId = ((MemberDto)session.getAttribute("login")).getmId();
			String mName = ((MemberDto)session.getAttribute("login")).getmName();
			dtos = dao.search(mId, mName);
		}
		MemberDto dto = dtos.get(0);
		
		request.setAttribute("MemberView", dto);

	}

}
