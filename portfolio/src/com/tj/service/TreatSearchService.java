package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.TreatChartDao;
import com.tj.dto.TreatChartDto;

public class TreatSearchService implements Service { // 診療記録を貯槽するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aName = request.getParameter("aName");
		String tDate = request.getParameter("tDate");
		TreatChartDao dao = TreatChartDao.getInstance();
		
		ArrayList<TreatChartDto> dtos = dao.search(aName, tDate);
		
		request.setAttribute("tSearchList", dtos);
	}

}
