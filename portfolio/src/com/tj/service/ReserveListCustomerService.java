package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.ReserveDao;
import com.tj.dto.MemberDto;
import com.tj.dto.ReserveDto;

public class ReserveListCustomerService implements Service { // 会員自身の診療予約リストを貰うService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
		ArrayList<ReserveDto> dtos = dao.mIdlist(mId);
		
		request.setAttribute("mIdList", dtos);

	}

}
