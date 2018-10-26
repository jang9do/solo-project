package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;

public class GetIdService implements Service { // 忘れたIDを探すためのService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mName = request.getParameter("mName");
		String mPhone = request.getParameter("mPhone");
		
		String mId = dao.getId(mName, mPhone);
		String message ="";
		
		if(!mId.equals("")) {
			message = "�븘�씠�뵒�뒗 \'"+mId+"\'�엯�땲�떎";
		} else {
			message = "�븘�씠�뵒瑜� 李얠쓣 �닔 �뾾�뒿�땲�떎";
		}
		
		request.setAttribute("getmId", message);
	}

}
