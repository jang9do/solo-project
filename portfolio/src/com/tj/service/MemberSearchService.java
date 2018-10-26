package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MemberSearchService implements Service { // 会員を検索するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		ArrayList<MemberDto> dtos = dao.search(mId, mName);
		
		request.setAttribute("MemberSearch", dtos);
	}

}
