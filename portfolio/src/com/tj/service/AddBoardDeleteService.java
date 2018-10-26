package com.tj.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AddBoardDao;
import com.tj.dto.AddBoardDto;

public class AddBoardDeleteService implements Service { // 掲示板のCommentを削除するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AddBoardDao dao = AddBoardDao.getInstance();
		int abNum = Integer.parseInt(request.getParameter("abNum"));
		
		int result = dao.delete(abNum);
		
		request.setAttribute("addBoardDeleteResult", result);
	}

}
