package br.com.sistemaweb.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;



public class ServletImageData extends ServletData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		try {
			super.doGet(req, resp);
		} catch (DataHelperException e) {
			BufferedImage image = new BufferedImage( 100, 133, BufferedImage.TYPE_INT_RGB );
			Graphics2D graphics2D = image.createGraphics();

			graphics2D.setColor( Color.BLUE );
			graphics2D.fill3DRect( 0, 0, 100, 133, true );
			graphics2D.setColor( Color.WHITE );
			graphics2D.drawString( "SEM FOTO", 23, 67 );

			ImageIO.write( image, "jpg", resp.getOutputStream() );
		}
	}

}