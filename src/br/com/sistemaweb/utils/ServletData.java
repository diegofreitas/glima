package br.com.sistemaweb.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;



public class ServletData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String name = req.getParameter("name");
		ServletDataHelper helper=(ServletDataHelper) req.getSession().getAttribute(name);
		if(helper==null){
			throw new DataHelperException("No DataHelper available");
		}
		
		if(helper.getStream()==null){
			throw new DataHelperException("No  data stream available");
		}
		OutputStream outputStream = resp.getOutputStream();
		try {
			if(helper.getExportType()!=null)
				resp.setContentType(helper.getExportType().getContentType());
			
			int length = helper.getStream().length;
			resp.setContentLength(length);
			//resp.setHeader("Content-Disposition", "attachment; filename="+name+helper.getExportType().getExtension());
			//IOUtils.write(helper.getStream(), outputStream);
			outputStream.write(helper.getStream());
			outputStream.flush();
			outputStream.close();
			//req.getSession().setAttribute(name, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
