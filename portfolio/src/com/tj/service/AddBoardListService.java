package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.AddBoardDao;
import com.tj.dto.AddBoardDto;

public class AddBoardListService implements Service { // 掲示物のCommentリストを貰うService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AddBoardDao dao = AddBoardDao.getInstance();
		
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		
		ArrayList<AddBoardDto> dtos = dao.list(bNum);
		
		request.setAttribute("addBoardList", dtos);
	}

}
