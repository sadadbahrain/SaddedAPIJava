package com.sadad.sadded;
import com.google.gson.annotations.SerializedName;

public class CreateInvoiceRequest {
	@SerializedName(value = "Api-key")
    public String ApiKey;
    @SerializedName(value = "Vendor-id")
    public int VendorId;
    @SerializedName(value = "Branch-id")
    public int BranchId;
    @SerializedName(value = "Terminal-id")
    public int TerminalId;
    @SerializedName(value = "notification-mode") 
    public int NotificationMode;
    @SerializedName(value = "Msisdn")
    public String Msisdn;
    @SerializedName(value = "Email")
    public String Email;
    @SerializedName(value = "Customer-name")
    public String CustomerName;
    @SerializedName(value = "Amount")
    public double Amount;
    @SerializedName(value = "Description")
    public String Description;
    @SerializedName(value = "Date")
    public String Date;
    @SerializedName(value = "External-reference")
    public String ExternalReference;
    @SerializedName(value = "Success-url")
    public String SuccessUrl;
    @SerializedName(value = "Error-url")
    public String ErrorUrl;
}
