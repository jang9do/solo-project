package com.tj.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.dao.BoardDao;
import com.tj.dto.BoardDto;
import com.tj.dto.MemberDto;

public class BoardInputService implements Service { // 掲示物を登録するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		HttpSession session = request.getSession();
		BoardDto dto = new BoardDto();
		String mId = "鍮꾪쉶�썝";
		
		if(session.getAttribute("login")!=null) {
			mId = ((MemberDto)session.getAttribute("login")).getmId();
		}

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
		
		int baNum = Integer.parseInt(multi.getParameter("baNum"));
		String bName = multi.getParameter("bName");
		String bTitle = multi.getParameter("bTitle");
		String bContent =  multi.getParameter("bContent");
		
		dto.setBaNum(baNum);
		dto.setmId(mId);
		dto.setbName(bName);
		dto.setbIp(bIp);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		dto.setbFile(filename);
		int result = dao.insert(dto);
		
		request.setAttribute("bInputResult", result);
	}

}
