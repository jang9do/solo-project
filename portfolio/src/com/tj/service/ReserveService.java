package com.tj.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AnimalDao;
import com.tj.dao.ReserveDao;
import com.tj.dto.AnimalDto;
import com.tj.dto.ReserveDto;

public class ReserveService implements Service { // òàÖûåøå³ªòª¹ªëService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		ReserveDao dao = ReserveDao.getInstance();
		ReserveDto dto = new ReserveDto();
		AnimalDao aDao = AnimalDao.getInstance();
		AnimalDto aDto = aDao.view(aNum);
		String aName = aDto.getaName();
		Timestamp rDate = Timestamp.valueOf(request.getParameter("rDate")+":00");
		String rDetail = request.getParameter("rDetail");
		
		dto.setaNum(aNum);
		dto.setaName(aName);
		dto.setrDate(rDate);
		dto.setrDetail(rDetail);
		
		int result = dao.reserve(dto);
		
		request.setAttribute("rResult", result);
	}

}
