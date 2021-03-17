package com.sadad.sadded;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class PaymentGateway
 */
@WebServlet("/PaymentGateway")
public class PaymentGateway extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentGateway() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaddedDriver</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>GET: Servlet SaddedDriver at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rootUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") + request.getContextPath();
		String postResponse = "";
		Gson gson = new Gson();
		
		String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String msisdn = request.getParameter("msisdn");
        String amount = request.getParameter("amount");
        String description = request.getParameter("description");
        
        // Get the configurations from web.xml
        String apiURL = request.getServletContext().getInitParameter("ApiURL");
        String apiKey = request.getServletContext().getInitParameter("ApiKey");
        String vendorId = request.getServletContext().getInitParameter("VendorId");
        String branchId = request.getServletContext().getInitParameter("BranchId");
        String terminalId = request.getServletContext().getInitParameter("TerminalId");
        String successURL = rootUrl + "/Success";
        String errorURL = rootUrl + "/Error";
        
        SaddedClient client = new SaddedClient(apiURL, apiKey, Integer.parseInt(vendorId), Integer.parseInt(branchId), Integer.parseInt(terminalId));
        try {
        	// Call Invoice creation API
			postResponse = client.CreateInvoiceLink(fullName, email, msisdn, Double.parseDouble(amount), description, successURL, errorURL);
			// Parse the response
			CreateInvoiceResponse invoiceResponse = gson.fromJson(postResponse, CreateInvoiceResponse.class);
			// Error handling
			if (invoiceResponse == null) {
				printError(response, postResponse);
				return;
			}
			
			if (invoiceResponse.ErrorCode != 0) {
				printError(response, postResponse);
				return;
			}
			
			// Open the payment page for customer to pay.
			// Response should be saved for future use
			if (invoiceResponse.ErrorCode == 0) {
				response.sendRedirect(invoiceResponse.PaymentUrl);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        
        
		
	}
	
	private void printError(HttpServletResponse response, String errorMessage) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Invoice creation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Invoice Creation failed with following response</h1>");
            out.println("<br/ > <p>" + errorMessage + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
	}

}
