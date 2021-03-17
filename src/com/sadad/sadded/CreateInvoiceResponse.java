package com.sadad.sadded;

import com.google.gson.annotations.SerializedName;

public class CreateInvoiceResponse {
	@SerializedName(value = "transaction-reference")
    public String TransactionReference;
	
	@SerializedName(value = "payment-url")
    public String PaymentUrl;
	
	@SerializedName(value = "notification-mode") 
	public int NotificationMode;
	
	@SerializedName(value = "invoice-id") 
	public long InvoiceId;
	
	@SerializedName(value = "error-code") 
	public int ErrorCode;
	
	@SerializedName(value = "error-message") 
	public String ErrorMessage;
}
