package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MessageDao;
import com.tj.dto.MessageDto;

public class MessageReplyService implements Service { // á®ªÃª¿MessageªËÚ÷ÞÀªòáêªëService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MessageDao dao = MessageDao.getInstance();
		
		String mId = request.getParameter("mId");
		String mesTitle = request.getParameter("mesTitle");
		String mesCon = request.getParameter("mesCon");
		String mesFrom = request.getParameter("mesFrom");
		
		MessageDto dto = new MessageDto();
		dto.setmId(mId);
		dto.setMesTitle(mesTitle);
		dto.setMesCon(mesCon);
		dto.setMesFrom(mesFrom);
		
		int result = dao.send(dto);
		
		request.setAttribute("rSendResult", result);
	}

}
