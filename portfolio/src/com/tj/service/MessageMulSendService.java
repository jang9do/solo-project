package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dao.MessageDao;
import com.tj.dto.MemberDto;
import com.tj.dto.MessageDto;

public class MessageMulSendService implements Service { // 特定RANKの会員全体にMessageを送るService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int levelN = Integer.parseInt(request.getParameter("levelN"));
		MemberDao mDao = MemberDao.getInstance();
		MessageDao dao = MessageDao.getInstance();
		HttpSession session = request.getSession();
		
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
		String mesTitle = request.getParameter("mesTitle");
		String mesCon = request.getParameter("mesCon");
		ArrayList<String> mesFroms = new ArrayList<String>();
		
		if(levelN==5) {
			for(int i = 2; i<5; i++) {
				ArrayList<String> temp = mDao.searchRank(i);
				for(String t:temp) {
					mesFroms.add(t);
				}
			}
		} else {
			mesFroms = mDao.searchRank(levelN);
		}
		
		int result = 0;
		
		for(String mesFrom : mesFroms) {
			MessageDto dto = new MessageDto();
			dto.setmId(mId);
			dto.setMesTitle(mesTitle);
			dto.setMesCon(mesCon);
			dto.setMesFrom(mesFrom);
			result = dao.send(dto);
			if(result==0) {
				break;
			}
		}
		
		request.setAttribute("rSendResult", result);
	} 

}
