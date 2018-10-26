package com.tj.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.dao.BoardDao;
import com.tj.dao.MessageDao;
import com.tj.dto.BoardDto;
import com.tj.dto.MemberDto;
import com.tj.dto.MessageDto;

public class BoardReplyService implements Service { // 返事の掲示物を登録するService
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("login")).getmId();
		String mName = ((MemberDto)session.getAttribute("login")).getmName();
		String bIp = request.getRemoteAddr();
		
		// 掲示物の添付ファイル（サーバーに貯槽）
		String path = request.getRealPath("bFile");
		int size = 1024*1024*5;
		String filename = null;
		MultipartRequest multi = null;
		try{
			multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = multi.getFileNames();
			if(params.hasMoreElements()){
				String paramStr = params.nextElement();
				filename = multi.getFilesystemName(paramStr);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(filename!=null){
			InputStream is = null;
			OutputStream os = null;
			try{
				File file = new File(path+"/"+filename);
				if(file.exists()){
					is = new FileInputStream(path+"/"+filename);
					os = new FileOutputStream("D:/mega-IT/6_jQuery/portfolio/WebContent/bFile/"+filename);
					byte[] bs = new byte[(int)file.length()];
					while(true){
						int readByte = is.read(bs);
						if(readByte==-1) break;
						os.write(bs, 0, readByte);
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch(Exception e2) {}
			}
		}
		if(filename==null || filename.equals("")) {
			filename="noFile";
		}
		
		int bNum = Integer.parseInt(multi.getParameter("bNum"));
		BoardDto dto = dao.view(bNum);
		
		String mesMId = dto.getmId();
		String mesTitle = dto.getbTitle();
		
		int baNum = Integer.parseInt(multi.getParameter("baNum"));
		String bContent =  multi.getParameter("bContent");
		int bStep = dto.getbStep()+1;
		int bIndent = dto.getbIndent()+1;
		
		String bTitle ="";
		for(int i=0; i<bIndent; i++) {
			bTitle = bTitle+"&nbsp;";
		}
		if(bIndent==1) {
			bTitle= bTitle+"�뵒[re]"+multi.getParameter("bTitle");
		} else {
			bTitle= bTitle+multi.getParameter("bTitle");
		}
		
		dto.setBaNum(baNum);
		dto.setmId(mId);
		dto.setbName(mName);
		dto.setbIp(bIp);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		dto.setbStep(bStep);
		dto.setbIndent(bIndent);
		dto.setbFile(filename);
		int result = dao.reInsert(dto);
		
		MessageDao mesDao = MessageDao.getInstance();
		MessageDto mesDto = new MessageDto();
		mesDto.setmId(mId);
		mesDto.setMesCon("�썝湲� �젣紐� :"+mesTitle+"<br>�옉�꽦�옄 : "+mName+"("+mId+")");
		mesDto.setMesFrom(mesMId);
		mesDto.setMesTitle("�옉�꽦湲� "+bNum+"�쓽 �떟湲��씠 �옉�꽦�릺�뿀�뒿�땲�떎");
		mesDao.send(mesDto);
		
		request.setAttribute("bInputResult", result);
	}
}
