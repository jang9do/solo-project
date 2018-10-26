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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.dao.AnimalDao;
import com.tj.dto.AnimalDto;

public class AnimalModifyService implements Service { // 動物の詳細情報を修正するService

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AnimalDao dao = AnimalDao.getInstance();
		
		String path = request.getRealPath("aniImg");
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
		 // 写真をサーバーに貯槽する
		if(filename!=null){
			InputStream is = null;
			OutputStream os = null;
			try{
				File file = new File(path+"/"+filename);
				if(file.exists()){
					is = new FileInputStream(path+"/"+filename);
					os = new FileOutputStream("D:/mega-IT/6_jQuery/portfolio/WebContent/aniImg/"+filename);
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
		
		int aNum = Integer.parseInt(multi.getParameter("aNum"));
		AnimalDto dto = dao.view(aNum);
		
		Timestamp aBirth = null;
		if(multi.getParameter("aBirth")==null || multi.getParameter("aBirth").equals("")) {
			System.out.println(2);
			aBirth = dto.getaBirth();
		} else {
			System.out.println(1);
			aBirth = Timestamp.valueOf(multi.getParameter("aBirth")+" 00:00:00");
		}
		
		if(filename==null || filename.equals("")) {
			filename=dto.getaPicture();
		}
		
		dto.setaPicture(filename);
		dto.setaNum(aNum);
		dto.setmId(multi.getParameter("mId"));
		dto.setaName(multi.getParameter("aName"));
		dto.setaBirth(aBirth);
		dto.setaSpecies(multi.getParameter("aSpecies"));
		dto.setaSdetail(multi.getParameter("aSdetail"));
		dto.setaGender(multi.getParameter("aGender"));
		int result = dao.modify(dto);
		
		request.setAttribute("animalResult", result);
		
	}

}
