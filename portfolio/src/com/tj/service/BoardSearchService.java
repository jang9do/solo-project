package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dto.BoardDto;

public class BoardSearchService implements Service { // 掲示物を検索するService（PAGINGの情報も含まれている）

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || "".equals(pageNum)) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int PAGESIZE=20, BLOCKSIZE=10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		
		int rank = Integer.parseInt(request.getParameter("rank"));
		String value = request.getParameter("value");
		
		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardDto> dtos = dao.Search(rank, value, startRow, endRow);
		request.setAttribute("list", dtos);
		
		int totCnt = dao.totCntS(rank, value);
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
