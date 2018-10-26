package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.ReserveDao;
import com.tj.dto.ReserveDto;

public class ReserveListMasterService implements Service { // η����ܻ�ª˪Ϫ��êƪ���������峪�̸��Service

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		String day = request.getParameter("day");
		ArrayList<ReserveDto> dtos = dao.daylist(day);
		
		request.setAttribute("dayList", dtos);
	}

}
