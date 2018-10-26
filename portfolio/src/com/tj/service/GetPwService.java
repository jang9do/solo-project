package com.tj.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;

public class GetPwService implements Service { // 忘れたパスワードを探すためのService（EMAILに転送）

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String mId = request.getParameter("mId");
		String mPhone = request.getParameter("mPhone");
		String mEmail = request.getParameter("mEmail");
		
		String mPw = dao.getPassword(mId, mPhone);
		
		if(mPw.equals("")){
			request.setAttribute("getmPw", "ID �샊�� �쟾�솕踰덊샇瑜� �솗�씤�빐二쇱꽭�슂");
		} else {
			String host = "smtp.naver.com";
			String user = "jang9do";
			String password = "jangwkd8eh9413";
			
			Properties pros = new Properties();
			pros.put("mail.smtp.host", host);
			pros.put("mail.smtp.auth", "true");
			
			request.setAttribute("getmPw", "鍮꾨�踰덊샇�뒗 \'"+mEmail+"\'濡� �쟾�넚�릺�뿀�뒿�땲�떎");
			
			Session session = Session.getDefaultInstance(pros, new javax.mail.Authenticator() {
				  protected PasswordAuthentication getPasswordAuthentication() {
					  return new PasswordAuthentication(user, password);
				  }
			});
			
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
				
				message.setSubject("[�룞臾쇰퀝�썝] 鍮꾨�踰덊샇 李얘린�엯�땲�떎");
				   
				message.setText("鍮꾨�踰덊샇 : "+mPw);

				Transport.send(message);
				System.out.println("message sent successfully...");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
