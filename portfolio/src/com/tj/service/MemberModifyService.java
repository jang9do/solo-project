package com.tj.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MemberModifyService implements Service { // 会員の情報を修正するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		MemberDto OriginDto = (MemberDto) session.getAttribute("login");
		
		String mId = request.getParameter("mId");
		String mPw;
		if(request.getParameter("mPw")==null || request.getParameter("mPw").equals("")) {
			mPw = OriginDto.getmPw();
		} else {
			mPw = request.getParameter("mPw");
		}
		String mName = request.getParameter("mName");
		Timestamp mBirth = null;
		if(request.getParameter("mBirth")!=null && !request.getParameter("mBirth").equals("")) {
			mBirth = Timestamp.valueOf(request.getParameter("mBirth")+" 00:00:00");
		}
		String mGender = request.getParameter("mGender");
		String mPhone = request.getParameter("mPhone");
		String mTel = request.getParameter("mTel");
		String mAddress = request.getParameter("mAddress");
		String mAdetail = request.getParameter("mAdetail");
		MemberDto dto = new MemberDto(mId, OriginDto.getLevelN(), mPw, mName, mBirth, mGender, mPhone, mTel, mAddress, mAdetail, OriginDto.getmJoin(), OriginDto.getlName());
		int result = dao.modify(dto);
		if(result == MemberDao.SUCCESS) {
			session.setAttribute("login", dto);
			request.setAttribute("modiMsg", "�닔�젙�꽦怨�");
		} else {
			request.setAttribute("modiMsg", "�닔�젙�떎�뙣");
		}
	}

}
