package Storemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ManageProductUI {
	   
		public JFrame view;

	    public JButton btnLoad  = new JButton("Load Product");
	    public JButton btnSave  = new JButton("Save Product");

	    public JTextField txtProductID = new JTextField(20);
	    public JTextField txtBarcode = new JTextField(20);    
	    public JTextField txtName = new JTextField(20);
	    public JTextField txtExpiration_Date = new JTextField(20);
	    public JTextField txtPrice = new JTextField(20);
	    public JTextField txtTax_Rate = new JTextField(20);
	    public JTextField txtQuantity = new JTextField(20);
	    public JTextField txtSupplier = new JTextField(20);
	    public JTextField txtManufactured_Date = new JTextField(20);
	    
	public ManageProductUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.setTitle("Update Product Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
//        String[] labels = {"ProductID ", "Barcode", "Name ", "Expiration_Date", "Price ", "Tax_Rate", "Quantity ", "Supplier", "Manufactured_Date"};
        
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);
        
        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("ProductID "));
        line1.add(txtProductID);
        view.getContentPane().add(line1);
        
        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Barcode "));
        line2.add(txtBarcode);
        view.getContentPane().add(line2);
        
        
        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Name "));
        line3.add(txtName);
        view.getContentPane().add(line3);
        
        
        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Expiration_Date "));
        line4.add(txtExpiration_Date);
        view.getContentPane().add(line4);
      
        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Price "));
        line5.add(txtPrice);
        view.getContentPane().add(line5);
        
        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(new JLabel("Tax_Rate "));
        line6.add(txtTax_Rate);
        view.getContentPane().add(line6);

        JPanel line7 = new JPanel(new FlowLayout());
        line7.add(new JLabel("Quantity "));
        line7.add(txtQuantity);
        view.getContentPane().add(line7);

        JPanel line8 = new JPanel(new FlowLayout());
        line8.add(new JLabel("Supplier "));
        line8.add(txtSupplier);
        view.getContentPane().add(line8);
   
        JPanel line9 = new JPanel(new FlowLayout());
        line9.add(new JLabel("Manufactured_Date "));
        line9.add(txtManufactured_Date);
        view.getContentPane().add(line9);
        


        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }
    
    class LoadButtonListerner implements ActionListener {
    	 @Override
         public void actionPerformed(ActionEvent actionEvent) {
             ProductModel product = new ProductModel();

             String id = txtProductID.getText();

             if (id.length() == 0) {
                 JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                 return;
             }

             try {
                 product.mProductID = Integer.parseInt(id);
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                 return;
             }
             
             // call data access!

             product = StoreManager.getInstance().getDataAdapter().loadProduct(product.mProductID);

             if (product == null) {
                 JOptionPane.showMessageDialog(null, "Product NOT exists!");
             } else {
            	 txtBarcode.setText(product.mBarcode);
                 txtName.setText(product.mName);
                 txtExpiration_Date.setText(product.mExpiration_Date);
                 txtPrice.setText(Double.toString(product.mPrice));
                 txtTax_Rate.setText(Double.toString(product.mTax_Rate));
                 txtQuantity.setText(Double.toString(product.mQuantity));
            	 txtSupplier.setText(product.mSupplier);
                 txtManufactured_Date.setText(product.mManufactured_Date);
             }
         
             	//move these to save part
//             String barcode = txtName.getText();
//             if (barcode.length() == 0) {
//                 JOptionPane.showMessageDialog(null, "Product barcode cannot be empty!");
//                 return;
//             }
//
//             product.mBarcode = barcode;
//             
//             String name = txtBarcode.getText();
//             if (name.length() == 0) {
//                 JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
//                 return;
//             }
//
//             product.mName = name;
//             
//             String expiration_date = txtExpiration_Date.getText();
//             if (expiration_date.length() == 0) {
//                 JOptionPane.showMessageDialog(null, "Product expiration_date cannot be empty!");
//                 return;
//             }
//
//             product.mExpiration_Date = expiration_date;
//             
//             String price = txtPrice.getText();
//             try {
//                 product.mPrice = Double.parseDouble(price);
//             } catch (NumberFormatException e) {
//                 JOptionPane.showMessageDialog(null, "Price is invalid!");
//                 return;
//             }
//             
//             String tax_rate = txtTax_Rate.getText();
//             try {
//                 product.mTax_Rate = Double.parseDouble(price);
//             } catch (NumberFormatException e) {
//                 JOptionPane.showMessageDialog(null, "Tax_Rate is invalid!");
//                 return;
//             }
//             
//             String quant = txtQuantity.getText();
//             try {
//                 product.mQuantity = Double.parseDouble(quant);
//             } catch (NumberFormatException e) {
//                 JOptionPane.showMessageDialog(null, "Quantity is invalid!");
//                 return;
//             }
//            
//             String suppler = txtSupplier.getText();
//             if (expiration_date.length() == 0) {
//                 JOptionPane.showMessageDialog(null, "Product Suppler cannot be empty!");
//                 return;
//             }
//
//             product.mSupplier = suppler;
//             
//             String manufactured_date = txtManufactured_Date.getText();
//             if (expiration_date.length() == 0) {
//                 JOptionPane.showMessageDialog(null, "Product mManufactured_Date cannot be empty!");
//                 return;
//             }
//
//             product.mManufactured_Date = manufactured_date;

//             switch (StoreManager.getInstance().getDataAdapter().saveProduct(product)) {
//                 case SQLiteDataAdapter.PRODUCT_DUPLICATE_ERROR:
//                     JOptionPane.showMessageDialog(null, "Product NOT added successfully! Duplicate product ID!");
//                 default:
//                     JOptionPane.showMessageDialog(null, "Product added successfully!" + product);
//             }
             
         }
	    
	}
    
    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ProductModel product = new ProductModel();
            String id = txtProductID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                product.mProductID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

          String barcode = txtName.getText();
         if (barcode.length() == 0) {
             JOptionPane.showMessageDialog(null, "Product barcode cannot be empty!");
              return;
          }

          product.mBarcode = barcode;
          
          String name = txtBarcode.getText();
          if (name.length() == 0) {
              JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
              return;
          }

          product.mName = name;
          
          String expiration_date = txtExpiration_Date.getText();
                if (expiration_date.length() == 0) {
              JOptionPane.showMessageDialog(null, "Product expiration_date cannot be empty!");
              return;
          }

          product.mExpiration_Date = expiration_date;          
          String price = txtPrice.getText();
          try {
              product.mPrice = Double.parseDouble(price);
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Price is invalid!");
              return;
          }
          
              String tax_rate = txtTax_Rate.getText();
          try {
              product.mTax_Rate = Double.parseDouble(tax_rate);
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Tax_Rate is invalid!");
              return;
          }
          
          String quant = txtQuantity.getText();
          try {
              product.mQuantity = Double.parseDouble(quant);
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Quantity is invalid!");
              return;
          }
         
          String suppler = txtSupplier.getText();
          if (expiration_date.length() == 0) {
              JOptionPane.showMessageDialog(null, "Product Suppler cannot be empty!");
              return;
          }

          product.mSupplier = suppler;
          
          String manufactured_date = txtManufactured_Date.getText();
          if (expiration_date.length() == 0) {
              JOptionPane.showMessageDialog(null, "Product mManufactured_Date cannot be empty!");
              return;
          }

          product.mManufactured_Date = manufactured_date;

          int  res = StoreManager.getInstance().getDataAdapter().saveProduct(product);

          if (res == IDataAdapter.PRODUCT_SAVE_FAILED)
              JOptionPane.showMessageDialog(null, "Product is NOT saved successfully!");
          else
              JOptionPane.showMessageDialog(null, "Product is SAVED successfully!");
        }
    }
    

}
