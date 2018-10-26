package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AnimalDao;
import com.tj.dto.AnimalDto;

public class AnimalGetService implements Service { // ÔÑÚªªÎßÙá¬ï×ÜÃªòÌ¸ªëService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AnimalDao dao = AnimalDao.getInstance();
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		
		AnimalDto dto = dao.view(aNum);
		
		request.setAttribute("animal", dto);

	}

}
