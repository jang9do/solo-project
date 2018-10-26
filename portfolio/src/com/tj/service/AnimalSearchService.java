package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AnimalDao;
import com.tj.dto.AnimalDto;

public class AnimalSearchService implements Service { // 動物を検索するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AnimalDao dao = AnimalDao.getInstance();
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String aName = request.getParameter("aName");
		
		ArrayList<AnimalDto> dtos = dao.search(mId, mName, aName);
	
		request.setAttribute("animals", dtos);
	}

}
