package Storemanager;

public class CustomerModel{

	public int mCustomerID;
	public String mName, mAddress,mpayment_info;
	public double mPhone;
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(mCustomerID).append(",");
		sb.append("\"").append(mName).append("\"").append(",");
		sb.append("\"").append(mAddress).append("\"").append(",");
		sb.append(mPhone).append(",");
		sb.append(mpayment_info).append(")");
		return sb.toString();
	}
}

// String[] labels = {"CustomerID ", "Name ", "Address", "Phone ", "payment_info"};
