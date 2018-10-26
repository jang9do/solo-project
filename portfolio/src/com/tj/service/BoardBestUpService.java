package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;

public class BoardBestUpService implements Service { // 一般掲示物をBest掲示物に変えるService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		
		int result = dao.best(bNum);
		
		request.setAttribute("bestUp", result);
	}

}
