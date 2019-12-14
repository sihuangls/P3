package Storemanager;

public class ProductModel{

	public int mProductID;
	public String mName,mBarcode, mVendor, mDescription, mExpiration_Date, mSupplier, mManufactured_Date;
	public double mPrice, mQuantity, mTax_Rate;
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(mProductID).append(",");
		sb.append("\"").append(mBarcode).append("\"").append(",");
		sb.append("\"").append(mName).append("\"").append(",");
		sb.append("\"").append(mExpiration_Date).append("\"").append(",");
		sb.append(mPrice).append(",");
		sb.append(mTax_Rate).append(",");
		sb.append(mQuantity).append(",");
		sb.append("\"").append(mSupplier).append("\"").append(",");
		sb.append("\"").append(mManufactured_Date).append("\"").append(")");
		return sb.toString();
	}
}
//CREATE TABLE "Products" (
//"ProductID"	INTEGER,
//"Barcode"	TEXT,
//"Name"	TEXT,
//"Expiration_Date"	TEXT,
//"Price"	REAL,
//"Tax_Rate"	REAL,
//"Quantity"	REAL,
//"Supplier"	TEXT,
//"Manufactured_Date"	TEXT
//)