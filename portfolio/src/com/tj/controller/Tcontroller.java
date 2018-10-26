package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.Service;
import com.tj.service.TreatReadService;
import com.tj.service.TreatSearchService;

//診療記録に関するcontroller
@WebServlet("*.tre")
public class Tcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Tcontroller() {
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
		
		if(com.equals("/treatSearch.tre")) { // 診療記録を検索
			service = new TreatSearchService();
			service.execute(request, response);
			viewPage = "treatSearchView.tre";
		} else if(com.equals("/treatSearchView.tre")) { // 診療記録の検索結果を見せるviewに移動
			viewPage = "treatChart/treatSearch.jsp";
		} else if(com.equals("/treatView.tre")) { // 診療記録の内容を見るviewに移動
			service = new TreatReadService();
			service.execute(request, response);
			viewPage = "treatChart/treatView.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
