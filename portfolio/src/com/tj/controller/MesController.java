package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.MessageChkService;
import com.tj.service.MessageMulSendService;
import com.tj.service.MessageReadService;
import com.tj.service.MessageReciveService;
import com.tj.service.MessageReplyService;
import com.tj.service.MessageSendListService;
import com.tj.service.MessageSendService;
import com.tj.service.MessageViewService;
import com.tj.service.Service;

//Messageに関するcontroller
@WebServlet("*.mes")
public class MesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write=0; // DBにInputする時viewを見た後に作業するかを確認する変数。
    public MesController() {
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
		
		if(com.equals("/mesNoReadChk.mes")) { // 読んでないメッセージの数を確認する
			service = new MessageChkService();
			service.execute(request, response);
			viewPage = "main/mesChk.jsp";
		} else if(com.equals("/mesReciveList.mes")) { // もらったメッセージのリストを見るviewに移動
			service = new MessageReciveService();
			service.execute(request, response);
			viewPage = "message/messageReciveList.jsp";
		} else if(com.equals("/mesSendList.mes")) { // 送ったメッセージのリストを見るviewに移動
			service = new MessageSendListService();
			service.execute(request, response);
			viewPage = "message/messageSendList.jsp";
		} else if(com.equals("/mesRead.mes")) { // 送ったメッセージの内容を見るviewに移動
			service = new MessageReadService();
			service.execute(request, response);
			viewPage = "message/messageRead.jsp";
		} else if(com.equals("/mesView.mes")) { // もらったメッセージの内容を見るviewに移動
			service = new MessageViewService();
			service.execute(request, response);
			viewPage = "message/messageView.jsp";
		} else if(com.equals("/mesReply.mes")) { // もらったメッセージの返事を送る
			service = new MessageReplyService();
			service.execute(request, response);
			viewPage = "mesReciveList.mes";
		} else if(com.equals("/mesReplyView.mes")) { // もらったメッセージの返事を書くviewに移動
			viewPage = "message/messageReply.jsp";
		} else if(com.equals("/mesSend.mes")) { // メッセージを送る
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new MessageSendService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "mesSendList.mes";
		} else if(com.equals("/mesSendView.mes")) { // メッセージを書くviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "message/messageSend.jsp";
		} else if(com.equals("/messageMulSendView.mes")) { // グループのメッセージを書くviewに移動
			write=1; // viewに入る事に成功。
			viewPage = "message/messageMulSend.jsp";
		} else if(com.equals("/messageMulSend.mes")) { // グループのメッセージを送る
			if(write==1) { // viewを見てない状態なら実行しないようにする変数。
				service = new MessageMulSendService();
				service.execute(request, response);
				write=0; // 初期化
			}
			viewPage = "mesSendList.mes";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
