package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orb.ParserImplBase;
import com.tj.dao.MemberDao;

public class MemberRankService implements Service { // 一般会員をVIPにRankUpするService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mId = request.getParameter("mId");
		int levelN = Integer.parseInt(request.getParameter("levelN"));
		
		int result = dao.rankUp(mId, levelN);
		
		request.setAttribute("rankResult", result);
	}

}
