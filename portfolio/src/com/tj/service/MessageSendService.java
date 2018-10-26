package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MessageDao;
import com.tj.dto.MemberDto;
import com.tj.dto.MessageDto;

public class MessageSendService implements Service { // MessageªòáêªëService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MessageDao dao = MessageDao.getInstance();
		HttpSession session = request.getSession();
		
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
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
