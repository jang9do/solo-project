package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dto.BoardDto;

public class BoardGongji5Service implements Service { // 公知掲示物の上位の五つを貰うService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardDto> dtos = dao.top5List();
		request.setAttribute("t5list", dtos);
	}
	
}
