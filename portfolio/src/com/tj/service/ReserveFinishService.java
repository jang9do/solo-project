package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.ReserveDao;

public class ReserveFinishService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		String tResult = request.getParameter("tResult");
		
		int result = dao.reserveFinish(rNum, tResult);
		
		request.setAttribute("rFinish", result);
	}

}
