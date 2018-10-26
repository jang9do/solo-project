package com.tj.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class JoinService implements Service { // 会員加入するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		Timestamp mBirth = null;
		if(request.getParameter("mBirth")!=null && !request.getParameter("mBirth").equals("")) { // STRINGになっいる誕生日情報をTimestamp型に変換
			mBirth = Timestamp.valueOf(request.getParameter("mBirth")+" 00:00:00");
		}
		String mGender = request.getParameter("mGender");
		String mPhone = request.getParameter("mPhone");
		String mTel = request.getParameter("mTel");
		String mAddress = request.getParameter("mAddress");
		String mAdetail = request.getParameter("mAdetail");
		MemberDto dto = new MemberDto(mId, 4, mPw, mName, mBirth, mGender, mPhone, mTel, mAddress, mAdetail, null, null);
		
		int result = dao.join(dto);
		
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("joinMsg", "媛��엯�꽦怨�");
		} else {
			request.setAttribute("joinMsg", "�떎�뙣");
		}
	}

}
