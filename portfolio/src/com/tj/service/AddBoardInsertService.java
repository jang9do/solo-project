package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.AddBoardDao;
import com.tj.dto.MemberDto;

public class AddBoardInsertService implements Service { // 掲示板のCommentを入力するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AddBoardDao dao = AddBoardDao.getInstance();
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		String abContent = request.getParameter("abContent");
		
		int result = dao.insert(bNum, mId, abContent);
		
		request.setAttribute("addBoardResult", result);
	}

}
