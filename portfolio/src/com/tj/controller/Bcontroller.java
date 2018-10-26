package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.AddBoardDeleteService;
import com.tj.service.AddBoardInsertService;
import com.tj.service.AddBoardListService;
import com.tj.service.BoardBestService;
import com.tj.service.BoardBestUpService;
import com.tj.service.BoardDeleteService;
import com.tj.service.BoardGongjiListservice;
import com.tj.service.BoardInputService;
import com.tj.service.BoardListService;
import com.tj.service.BoardModifyService;
import com.tj.service.BoardReplyService;
import com.tj.service.BoardSearchService;
import com.tj.service.BoardViewService;
import com.tj.service.Service;

//掲示板に関するcontroller
@WebServlet("*.bo")
public class Bcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write = 0; // DBにInputする時viewを見た後に作業するかを確認する変数。
    public Bcontroller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //Get
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //Post
		request.setCharacterEncoding("utf-8"); //Encoding
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		Service service = null; // Interface
		String viewPage = null; // view
		
		if(com.equals("/boardView.bo")) { // 掲示板の掲示物を見るviewに移動
			write = 1; // viewに入る事に成功。
			service = new BoardViewService(); // 掲示物の情報
			service.execute(request, response);
			service = new AddBoardListService(); // 掲示物のComment情報
			service.execute(request, response);
			viewPage = "board/boardView.jsp";
		} else if(com.equals("/boardList.bo")){ // 掲示板のリストを見るviewに移動
			service = new BoardListService();
			service.execute(request, response);
			viewPage = "board/boardList.jsp";
		} else if(com.equals("/boardGongji.bo")) { // 公知掲示板のリストを見るviewに移動
			service = new BoardGongjiListservice();
			service.execute(request, response);
			viewPage = "board/boardGongji.jsp";
		} else if(com.equals("/boardBest.bo")) { // Best掲示板のリストを見るviewに移動
			service = new BoardBestService();
			service.execute(request, response);
			viewPage = "board/boardBest.jsp";
		} else if(com.equals("/boardInput.bo")) { // 掲示物を登録する
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new BoardInputService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "boardList.bo";
		} else if(com.equals("/boardInputView.bo")) { // 掲示物を登録するviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "board/boardInput.jsp";
		} else if(com.equals("/boardModifyView.bo")) { // 掲示物を修正するviewに移動
			write=1; // viewに入る事に成功。
			service = new BoardViewService();
			service.execute(request, response);
			viewPage = "board/boardModify.jsp";
		} else if(com.equals("/boardModify.bo")) { // 掲示物を修正する
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new BoardModifyService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "boardList.bo";
		} else if(com.equals("/boardReplyView.bo")) { // Commentを登録するviewに移動
			write=1; // viewに入る事に成功。
			service = new BoardViewService();
			service.execute(request, response);
			viewPage = "board/boardReply.jsp";
		} else if(com.equals("/boardReply.bo")) { // Comment掲示物を登録するviewに移動
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new BoardReplyService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "boardList.bo";
		} else if(com.equals("/addBoardInsert.bo")) { // Commentを登録する
			if(write == 1) { // viewを見てない状態なら実行しないようにする変数。
				service = new AddBoardInsertService();
				service.execute(request, response);
				write = 0; // 初期化
			}
			viewPage = "boardView.bo";
		} else if(com.equals("/addBoardDelete.bo")) { // Commentを消す
			service = new AddBoardDeleteService();
			service.execute(request, response);
			viewPage = "boardView.bo";
		} else if(com.equals("/boardDelete.bo")) { // 掲示物を消す
			service = new BoardDeleteService();
			service.execute(request, response);
			viewPage = "boardList.bo";
		} else if(com.equals("/boardSearch.bo")) { // 掲示物を検索する
			service = new BoardSearchService();
			service.execute(request, response);
			viewPage = "board/boardSearch.jsp";
		} else if(com.equals("/boardBestUp.bo")) { // 一般の掲示物をBest掲示物に変える
			service = new BoardBestUpService();
			service.execute(request, response);
			viewPage = "boardView.bo";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
