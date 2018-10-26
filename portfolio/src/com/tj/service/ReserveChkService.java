package com.tj.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.ReserveDao;

public class ReserveChkService implements Service { // 該当時間の診療予約の有無を確認するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao dao = ReserveDao.getInstance();
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DATE, +1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String rDate = request.getParameter("rDate");
		if(rDate == null) {
			rDate = sdf.format(yesterday.getTime());
		}
		
		int[] result = new int[7];
		String[] time = {"09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"};
		
		for(int i = 0; i<7; i++) {
			result[i] = dao.reserveChk(rDate+" "+time[i]);
		}
		
		request.setAttribute("reserveR", result);
		request.setAttribute("rDateService", rDate);
	}

}
