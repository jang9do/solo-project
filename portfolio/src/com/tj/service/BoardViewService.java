package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dto.BoardDto;

public class BoardViewService implements Service{ // 掲示物を詳細内容を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		BoardDao dao = BoardDao.getInstance();
		
		BoardDto dto = dao.view(bNum);
		
		request.setAttribute("BoardDto", dto);
	}
	
}
