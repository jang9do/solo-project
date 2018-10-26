package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MessageDao;
import com.tj.dto.MemberDto;
import com.tj.dto.MessageDto;


public class MessageReciveService implements Service { // á®ªÃª¿MessageªÎ«ê«¹«Èªòá®ª¦Service

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mesFrom = ((MemberDto)session.getAttribute("login")).getmId();
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || "".equals(pageNum)) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		MessageDao dao = MessageDao.getInstance();
		ArrayList<MessageDto> dtos = dao.receive_search(startRow, endRow, mesFrom);
		request.setAttribute("list", dtos);
		
		int totCnt = dao.receive_count(mesFrom);
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
