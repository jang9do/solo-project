package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.TreatChartDao;
import com.tj.dto.TreatChartDto;

public class TreatReadService implements Service { // 診療記録の詳細内容を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int tNum = Integer.parseInt(request.getParameter("tNum"));
		TreatChartDao dao = TreatChartDao.getInstance();
		
		TreatChartDto dto = dao.read(tNum);
		
		request.setAttribute("tRead", dto);
	}

}
