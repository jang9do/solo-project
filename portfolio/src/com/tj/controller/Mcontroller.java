package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.*;

//会員管理とmainページに関するcontroller
@WebServlet("*.mem")
public class Mcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write = 0; // DBにInputする時viewを見た後に作業するかを確認する変数。
    public Mcontroller() {
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
		
		if(com.equals("/main.mem")) { // mainページのviewに移動
			service = new MessageChkService(); // 新しいMessageの情報
			service.execute(request, response);
			service = new BoardGongji5Service(); // 上位公知、五つのリスト
			service.execute(request, response);
			service = new BoardBest5Service(); // Best公知、五つのリスト
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if(com.equals("/login.mem")) { // loginする
			service = new LoginService();
			service.execute(request, response);
			viewPage = "main.mem";
		} else if(com.equals("/logout.mem")) { // logoutする
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "main.mem";
		} else if(com.equals("/joinView.mem")) { // 会員登録をするviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "member/join.jsp";
		} else if(com.equals("/join.mem")) { // 会員登録をする
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new JoinService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "main.mem";
		} else if(com.equals("/idChk.mem")) { // 同じアカウントの存在を確認する
			service = new IdCheckService();
			service.execute(request, response);
			viewPage = "member/idChk.jsp";
		} else if(com.equals("/modifyView.mem")) { // 会員情報を修正するviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "member/modify.jsp";
		} else if(com.equals("/modify.mem")){ // 会員情報を修正
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new MemberModifyService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "modifyView.mem";
		} else if(com.equals("/memberGetView.mem")) { // 会員情報を探すviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "member/get.jsp";
		} else if(com.equals("/memberGetId.mem")) { // アカウントを探す
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new GetIdService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "member/getResult.jsp";
		} else if(com.equals("/memberGetPw.mem")) { // パスワードを探す
			service = new GetPwService();
			service.execute(request, response);
			viewPage = "member/getResult.jsp";
		} else if(com.equals("/MemberDelete.mem")) { // 会員脱退（管理者）
			service = new MemberDeleteService();
			service.execute(request, response);
			viewPage = "main.mem";
		} else if(com.equals("/MemberSearch.mem")) { // 会員を探す
			service = new MemberSearchService();
			service.execute(request, response);
			viewPage = "member/MemberSearch.jsp";
		} else if(com.equals("/MemberList.mem")) { // 会員のリストを見る
			service = new MemberListService();
			service.execute(request, response);
			viewPage = "member/MemberList.jsp";
		} else if(com.equals("/Delete.mem")) { // 会員脱退（ユーザ）
			service = new MemberDeleteService();
			service.execute(request, response);
			viewPage = "logout.mem"; // logout
		} else if(com.equals("/MemberView.mem")) { // 会員情報を見るviewに移動
			service = new MemberViewService();
			service.execute(request, response);
			viewPage = "member/MemberView.jsp";
		} else if(com.equals("/MemberRank.mem")) { // 会員の等級による会員検索
			service =new MemberRankService();
			service.execute(request, response);
			viewPage = "MemberSearch.mem";
		} else if(com.equals("/MemberAdmin.mem")) { // 会員の等級を変える
			service =new MemberAdminService();
			service.execute(request, response);
			viewPage = "MemberAdminView.mem";
		} else if(com.equals("/MemberAdminView.mem")) { // 会員の等級を変える為のviewに移動
			service = new MemberSearchService();
			service.execute(request, response);
			viewPage = "member/MemberAdmin.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
