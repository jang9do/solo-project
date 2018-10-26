package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MessageDao;
import com.tj.dto.MessageDto;

public class MessageViewService implements Service { // 貰ったMessageの詳細内容を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MessageDao dao = MessageDao.getInstance();
		int mesNum = Integer.parseInt(request.getParameter("mesNum"));
		
		MessageDto dto = dao.view(mesNum);
		
		request.setAttribute("mesView", dto);
	}

}
