package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.ReserveDao;
import com.tj.dto.ReserveDto;

public class ReserveReadService implements Service { // 診療予約の詳細内容を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		
		ReserveDto dto = dao.read(rNum);
		
		request.setAttribute("ReserveDto", dto);
	}

}
