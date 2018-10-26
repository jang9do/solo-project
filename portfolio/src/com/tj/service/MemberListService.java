package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MemberListService implements Service { // 会員のリストを貰うService（PAGINGの情報も含まれている）

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || "".equals(pageNum)) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		
		MemberDao dao = MemberDao.getInstance();
		ArrayList<MemberDto> dtos = dao.list(startRow, endRow);
		request.setAttribute("list", dtos);
		
		int totCnt = dao.totCnt();
		int pageCnt = (int)(Math.ceil((double)totCnt/PAGESIZE));
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totCnt", totCnt);
	}

}
