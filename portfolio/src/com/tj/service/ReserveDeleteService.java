package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.ReserveDao;

public class ReserveDeleteService implements Service { // òàÖûåøå³ªòá¼ª¹Service

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		
		int result = dao.delete(rNum);
		
		request.setAttribute("rDelete", result);
	}

}
