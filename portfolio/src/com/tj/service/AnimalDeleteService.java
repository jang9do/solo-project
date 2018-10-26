package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AnimalDao;

public class AnimalDeleteService implements Service { // 登録した動物を削除するService（論理的な削除）

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		AnimalDao dao = AnimalDao.getInstance();
		
		int result = dao.delete(aNum);
		
		request.setAttribute("AnimalDelete", result);
	}

}
