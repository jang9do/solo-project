package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.*;

@WebServlet("*.res")
public class Rcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write=0; // DBにInputする時viewを見た後に作業するかを確認する変数。
    public Rcontroller() {
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
		
		if(com.equals("/ReserveMList.res")) { // 管理者が病院に入っている診療予約のリストを見る
			service = new ReserveListMasterService();
			service.execute(request, response);
			viewPage = "Reserve/reserveMasterList.jsp";
		} else if(com.equals("/reserveCustiomList.res")) { // お客様が自分が申請した診療予約のリストを見る
			service = new ReserveListCustomerService();
			service.execute(request, response);
			viewPage = "Reserve/reserveCustomList.jsp";
		} else if(com.equals("/Reserve.res")) { // 診療予約をする
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new ReserveService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "ReserveView.res";
		} else if(com.equals("/ReserveView.res")) { // 診療予約をする為のviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "Reserve/reserve.jsp";
		} else if(com.equals("/ReserveChk.res")) { // 診療予約をする為のviewの中のあるajaxページ（時間を選択）
			service = new ReserveChkService();
			service.execute(request, response);
			viewPage = "Reserve/reserveChk.jsp";
		} else if(com.equals("/reserveRead.res")) { // 診療予約の詳細内容を見るviewに移動
			service = new ReserveReadService();
			service.execute(request, response);
			viewPage = "Reserve/reserveRead.jsp";
		} else if(com.equals("/reserveDeleteMaster.res")) { // 管理者が診療予約をキャンセル
			service = new ReserveDeleteService();
			service.execute(request, response);
			viewPage = "ReserveMList.res";
		} else if(com.equals("/reserveDeleteCustom.res")) { // 予約したお客様本人が診療予約をキャンセル
			service = new ReserveDeleteService();
			service.execute(request, response);
			viewPage = "reserveCustiomList.res";
		} else if(com.equals("/reserveFinish.res")) { // 診療が完了されたら予約を削除した後、診療記録に内容と結果を貯蔵する
			service = new ReserveFinishService();
			service.execute(request, response);
			viewPage = "ReserveMList.res";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
