package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.AnimalDao;
import com.tj.dto.AnimalDto;
import com.tj.dto.MemberDto;

public class AnimalListService implements Service { // 会員の動物リストを貰うService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AnimalDao dao = AnimalDao.getInstance();
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
		ArrayList<AnimalDto> dtos = dao.list(mId);
		
		request.setAttribute("memberAnimal", dtos);
	}

}
