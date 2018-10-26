package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;

public class IdCheckService implements Service { // 会員加入の時、同じIDの存在有無を確認するService（AJAX使用）

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mId = request.getParameter("mId");
		int result = dao.idCheck(mId);
		if(result == MemberDao.SUCCESS && mId.length()>7) {
			request.setAttribute("idMsg", "<span style=\"color:blue;\">�궗�슜�븷 �닔 �엳�뒗 �븘�씠�뵒�엯�땲�떎</span>");
		} else {
			request.setAttribute("idMsg", "<span style=\"color:red;\">�궗�슜�븷 �닔 �뾾�뒗 �븘�씠�뵒�엯�땲�떎</span>");
		}
	}

}
