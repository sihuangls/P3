package Storemanager;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLiteDataAdapter implements IDataAdapter  {


    Connection conn = null;

    public int connect(String dbfile) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + dbfile;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_OPEN_FAILED;
        }
        return CONNECTION_OPEN_OK;
    }

   
    @Override
    public int disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_CLOSE_FAILED;
        }
        return CONNECTION_CLOSE_OK;
    }
    
    
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;

        try {
            String sql = "SELECT ProductID, Barcode, Name, Expiration_Date, Price, Tax_Rate, Quantity, Supplier, "
            		+ "Manufactured_Date FROM Products WHERE ProductID = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                product = new ProductModel();
            	product.mProductID = rs.getInt("ProductID");
            	product.mBarcode = rs.getString("Barcode");          
            	product.mName = rs.getString("Name");
            	product.mExpiration_Date = rs.getString("Expiration_Date");
            	product.mPrice = rs.getDouble("Price");
            	product.mTax_Rate = rs.getDouble("Tax_Rate");
            	product.mQuantity = rs.getDouble("Quantity");            
            	product.mSupplier = rs.getString("Supplier");
            	product.mManufactured_Date = rs.getString("Manufactured_Date");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
   
    public int saveProduct(ProductModel product) {
        try {            
        	
        	Statement stmt = conn.createStatement();
            ProductModel p = loadProduct(product.mProductID); // check if this product exists
            if (p != null) {
                stmt.executeUpdate("DELETE FROM Products WHERE ProductID = " + product.mProductID);
            }
            String sql = "INSERT INTO Products(ProductID, Barcode, Name, Expiration_Date, Price, Tax_Rate, "
            		+ "Quantity, Supplier, Manufactured_Date) VALUES " + product;
            System.out.println(sql);

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PRODUCT_SAVE_FAILED;
        }

        return PRODUCT_SAVED_OK;
    }
    
    
    public CustomerModel loadCustomer(int id) {
    	CustomerModel customer = null;

        try {
            String sql = "SELECT * FROM Customers WHERE CustomerId = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.mCustomerID = id; 
            	customer.mName = rs.getString("Name");
            	customer.mAddress = rs.getString("Address");
            	customer.mPhone = rs.getDouble("Phone");
            	customer.mpayment_info = rs.getString("payment_info");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }
   
    public int saveCustomer(CustomerModel customer) {
        try {            
        	
        	Statement stmt = conn.createStatement();
        	CustomerModel p = loadCustomer(customer.mCustomerID); // check if this product exists
            
        	if (p != null) {
                stmt.executeUpdate("DELETE FROM Customers WHERE CustomerID = " + customer.mCustomerID);
            }
            String sql = "INSERT INTO Customers(CustomerID, Name, Address, Phone, payment_info) VALUES " + customer;
            System.out.println(sql);

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return CUSTOMER_SAVE_FAILED;
        }

        return CUSTOMER_SAVE_OK;
    }
    
    @Override
    public int savePurchase(PurchaseModel purchase) {
        try {
            String sql = "INSERT INTO Purchase VALUES " + purchase;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PURCHASE_SAVE_FAILED;
        }

        return PURCHASE_SAVED_OK;

    }
    public ProductListModel searchProduct(String name, double minPrice, double maxPrice) {
        ProductListModel res = new ProductListModel();
        try {
            String sql = "SELECT * FROM Products WHERE Name LIKE \'%" + name + "%\' "
                    + "AND Price >= " + minPrice + " AND Price <= " + maxPrice;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product = new ProductModel();
            	product.mProductID = rs.getInt("ProductID");
            	product.mBarcode = rs.getString("Barcode");          
            	product.mName = rs.getString("Name");
            	product.mExpiration_Date = rs.getString("Expiration_Date");
            	product.mPrice = rs.getDouble("Price");
            	product.mTax_Rate = rs.getDouble("Tax_Rate");
            	product.mQuantity = rs.getDouble("Quantity");            
            	product.mSupplier = rs.getString("Supplier");
            	product.mManufactured_Date = rs.getString("Manufactured_Date");
                res.products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public UserModel loadUser(String username) {
        UserModel user = null;

        try {
            String sql = "SELECT * FROM Users WHERE Username = \"" + username + "\"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new UserModel();
                user.mUsername = username;
                user.mPassword = rs.getString("Password");
                user.mFullname = rs.getString("Fullname");
                user.mUserType = rs.getInt("Usertype");
                if (user.mUserType == UserModel.CUSTOMER)
                    user.mCustomerID = rs.getInt("CustomerID");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }


	@Override
	public PurchaseListModel loadPurchaseHistory(int id) {
        PurchaseListModel res = new PurchaseListModel();
        try {
            String sql = "SELECT * FROM Purchase WHERE CustomerId = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PurchaseModel purchase = new PurchaseModel();
                purchase.mCustomerID = id;
                purchase.mPurchaseID = rs.getInt("PurchaseID");
                purchase.mProductID = rs.getInt("ProductID");
                purchase.mPrice = rs.getDouble("Price");
                purchase.mQuantity = rs.getDouble("Quantity");
                purchase.mCost = rs.getDouble("Cost");
                purchase.mTax_Rate = rs.getDouble("Tax_Rate");
                purchase.mTotal = rs.getDouble("Total");
                purchase.mDate = rs.getString("Date");

                res.purchases.add(purchase);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
	}
    
    
    
}

