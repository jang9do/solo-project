package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MessageDao;
import com.tj.dto.MessageDto;

public class MessageReadService implements Service { // 送ったMessageの詳細内容を見るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MessageDao dao = MessageDao.getInstance();
		int mesNum = Integer.parseInt(request.getParameter("mesNum"));
		
		MessageDto dto = dao.read(mesNum);
		
		request.setAttribute("mesRead", dto);
	}

}
