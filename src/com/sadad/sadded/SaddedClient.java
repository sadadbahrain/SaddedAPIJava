package com.sadad.sadded;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaddedClient {
	private final String _apiURL;
	private final String _apiKey;
    private final int _vendorId;
    private final int _branchId;
    private final int _terminalId;

    public SaddedClient(String apiURL, String apiKey, int vendorId, int branchId, int terminalId) {
    	_apiURL = apiURL;
        _apiKey = apiKey;
        _vendorId = vendorId;
        _branchId = branchId;
        _terminalId = terminalId;

    }

    /**
     * 
     * @param fullName
     * @param email
     * @param msisdn
     * @param amount
     * @param description
     * @param successURL (optional)
     * @param errorURL (optional)
     * @return
     */
    
    public String CreateInvoiceLink(String fullName, String email, String msisdn, double amount, String description, String successURL, String errorURL) throws Exception {
        String link = "";
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        invoiceRequest.ApiKey = _apiKey;
        invoiceRequest.VendorId = _vendorId;
        invoiceRequest.BranchId = _branchId;
        invoiceRequest.TerminalId = _terminalId;
        invoiceRequest.NotificationMode = 300; // Fixed
        invoiceRequest.SuccessUrl = successURL;
        invoiceRequest.ErrorUrl = errorURL;
        
        invoiceRequest.Msisdn = msisdn;
        invoiceRequest.Email = email;
        invoiceRequest.CustomerName = fullName;
        invoiceRequest.Amount = amount;
        invoiceRequest.Description = description;
        invoiceRequest.Date = GetCurrentTimeStamp();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(invoiceRequest);
        
        HttpPost postClient = new HttpPost(_apiURL);
        postClient.setEntity(new StringEntity(jsonString));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(postClient)) {
			link = EntityUtils.toString(response.getEntity());
			System.out.println(link);
			
		}
        return link;
    }

    private String GetCurrentTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
