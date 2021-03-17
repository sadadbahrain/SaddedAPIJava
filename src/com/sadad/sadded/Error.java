package com.sadad.sadded;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Error
 */
@WebServlet("/Error")
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Error() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, String[]> paramMap = request.getParameterMap();
		Iterator it = paramMap.entrySet().iterator();
		out.println("<h1>Transaction Failed. Following Parameters Posted</h1>");
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = ((Map.Entry<String, String[]>) it.next());
			String key = entry.getKey();
			String[] value = entry.getValue();
			out.println(key + "=" + value[0].toString() + "<br>");
			out.println("-------------------<br>");
		}
		out.close();
	}

}
