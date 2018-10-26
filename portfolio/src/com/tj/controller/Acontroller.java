package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.*;

//動物に関するcontroller
@WebServlet("*.ani")
public class Acontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write = 0; // DBにInputする時viewを見た後に作業するかを確認する変数。
    public Acontroller() {
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
		
		if(com.equals("/animalList.ani")) { // 登録した動物リストを見るviewに移動
			service = new AnimalListService();
			service.execute(request, response);
			viewPage = "animal/animalList.jsp";
		} else if(com.equals("/animalModifyView.ani")) { // 動物の情報を修正するviewに移動
			write=1; // viewに入る事に成功。
			service = new AnimalGetService();
			service.execute(request, response);
			viewPage = "animal/animalModify.jsp";
		} else if(com.equals("/animalModify.ani")) { // 動物の情報を修正
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new AnimalModifyService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "animalList.ani";
		} else if(com.equals("/animalDelete.ani")) { // 動物のリストから消す。
			service = new AnimalDeleteService();
			service.execute(request, response);
			viewPage = "animalList.ani";
		} else if(com.equals("/animalModifyViewS.ani")) { // 動物の情報を修正する別のviewに移動
			write=1; // viewに入る事に成功。
			service = new AnimalGetService();
			service.execute(request, response);
			viewPage = "animal/animalModifyS.jsp";
		} else if(com.equals("/animalModifyS.ani")) {
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new AnimalModifyService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "AnimalSearch.ani";
		} else if(com.equals("/animalDeleteS.ani")) { // 動物のリストから消す後に他のviewに移動。
			service = new AnimalDeleteService();
			service.execute(request, response);
			viewPage = "AnimalSearch.ani";
		} else if(com.equals("/AnimalInputView.ani")) { // 動物の登録viewに移動
			write=1; // viewに入る事に成功。
			viewPage = "animal/animalInput.jsp";
		} else if(com.equals("/AnimalInput.ani")) { // 動物の登録
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new AnimalInputService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "animalList.ani";
		} else if(com.equals("/AnimalSearchView.ani")) { // 動物の検索機能viewに移動
			viewPage = "animal/animalSearch.jsp";
		} else if(com.equals("/AnimalSearch.ani")) { // 動物の検索
			service = new AnimalSearchService();
			service.execute(request, response);
			viewPage = "AnimalSearchView.ani";
		} else if(com.equals("/animalChk.ani")) { // ログインしたユーザが登録した動物のリストを見せるviewに移動。
			service = new AnimalListService();
			service.execute(request, response);
			viewPage = "animal/animalChk.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
