package com.gen.script.config.xss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.nhncorp.lucy.security.xss.XssFilter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
 
public class RequestWrapper extends HttpServletRequestWrapper {
    private byte[] b;
 
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
//        XssFilter filter = XssFilter.getInstance("lucy-xss-sax.xml");
        XssFilter filter = XssFilter.getInstance("lucy-xss-sax.xml", true);	//RequestBody에 대한 XSS 공격시 주석을 출력하지 않기위한 설정.
        b = new String(filter.doFilter(getBody(request))).getBytes("UTF-8");
    }
 
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(b);
        return new ServletInputStreamImpl(bis);
    }
 
    class ServletInputStreamImpl extends ServletInputStream {
        private InputStream is;
 
        public ServletInputStreamImpl(InputStream bis) {
            is = bis;
        }
 
        public int read() throws IOException {
            return is.read();
        }
 
        public int read(byte[] b) throws IOException {
            return is.read(b);
        }

		public boolean isFinished() {
			return false;
		}

		public boolean isReady() {
			return false;
		}

		public void setReadListener(ReadListener readListener) {
			
		}
    }
 
    public static String getBody(HttpServletRequest request) throws IOException {
        String body = null;
        BufferedReader br= null;
       StringBuilder sb= new StringBuilder();
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
               br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = br.read(charBuffer)) > 0) {
                   sb.append(charBuffer, 0, bytesRead);
                }
              
            } else {
               sb.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (br!= null) {
                try {
                   br.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = sb.toString();
        return body;
    }
}