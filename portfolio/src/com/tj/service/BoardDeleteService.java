package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;

public class BoardDeleteService implements Service { // 掲示物を消すService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		int bNum =  Integer.parseInt(request.getParameter("bNum"));
		
		int result = dao.delete(bNum);
		
		request.setAttribute("bDeleteResult", result);
	}

}
