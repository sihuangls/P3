package Storemanager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManageCustomerUI {
    public JFrame view;
	public JButton btnLoad = new JButton("Load Customer");
    public JButton btnSave  = new JButton("Save Customer");

    public JTextField txtCustomerID = new JTextField(20);  
    public JTextField txtName = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtpayment_info = new JTextField(20);
    
	public ManageCustomerUI() {

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Customer Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));


	       // String[] labels = {"CustomerID ", "Name ", "Address", "Phone ", "payment_info"};

        	JPanel panelButtons = new JPanel(new FlowLayout());
        	panelButtons.add(btnLoad);
        	panelButtons.add(btnSave);
        	view.getContentPane().add(panelButtons);
        
	        JPanel line1 = new JPanel(new FlowLayout());
	        line1.add(new JLabel("CustomerID "));
	        line1.add(txtCustomerID);
	        view.getContentPane().add(line1);
	        
	        
	        JPanel line2 = new JPanel(new FlowLayout());
	        line2.add(new JLabel("Name "));
	        line2.add(txtName);
	        view.getContentPane().add(line2);
	        
	        JPanel line3 = new JPanel(new FlowLayout());
	        line3.add(new JLabel("Address "));
	        line3.add(txtAddress);
	        view.getContentPane().add(line3);
	      
	        JPanel line4 = new JPanel(new FlowLayout());
	        line4.add(new JLabel("Phone "));
	        line4.add(txtPhone); 
	        view.getContentPane().add(line4);
	        
	        JPanel line5 = new JPanel(new FlowLayout());
	        line5.add(new JLabel("payment_info "));
	        line5.add(txtpayment_info);
	        view.getContentPane().add(line5);
	        
	        btnLoad.addActionListener(new LoadButtonListerner());

	        btnSave.addActionListener(new SaveButtonListener());

	    
	}
    public void run() {
        view.setVisible(true);
    }
    
    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            int i;
            try {
            	
                 i = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }


            CustomerModel customer =  StoreManager.getInstance(). getDataAdapter().loadCustomer(i);
            
                if (customer == null) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                }
                else {
                    
                    txtName.setText(customer.mName);
                    txtAddress.setText(customer.mAddress);
                    txtPhone.setText(Double.toString(customer.mPhone));
                    txtpayment_info.setText(customer.mpayment_info);
                }

            

        }
    }
    

    class SaveButtonListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent actionEvent) {
            
    		CustomerModel customer = new CustomerModel();
    		//CustomerModel customer = new CustomerModel();

    		String id = txtCustomerID.getText();

    		if (id.length() == 0) {
    		    JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
    		    return;
    		}

    		try {
    			customer.mCustomerID = Integer.parseInt(id);
    		} catch (NumberFormatException e) {
    		    JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
    		    return;
    		}
    		          
    		String name = txtName.getText();
    		if (name.length() == 0) {
    		    JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
    		    return;
    		}

    		customer.mName = name;

    		String address = txtAddress.getText();
    		if (address.length() == 0) {
    		    JOptionPane.showMessageDialog(null, "Customer Address cannot be empty!");
    		    return;
    		}

    		customer.mAddress = address;

    		String phone = txtPhone.getText();
    		try {
    			customer.mPhone = Double.parseDouble(phone);
    		} catch (NumberFormatException e) {
    		    JOptionPane.showMessageDialog(null, "Phone is invalid!");
    		    return;
    		}


    		String mpayment_info = txtpayment_info.getText();
    		if (mpayment_info.length() == 0) {
    		    JOptionPane.showMessageDialog(null, "Customer payment_info cannot be empty!");
    		    return;
    		}

    		customer.mpayment_info = mpayment_info;
    		
    		int res =  StoreManager.getInstance(). getDataAdapter().saveCustomer(customer);

              if (res == IDataAdapter.CUSTOMER_SAVE_FAILED)
                  JOptionPane.showMessageDialog(null, "Customer is NOT saved successfully!");
              else
                  JOptionPane.showMessageDialog(null, "Customer is SAVED successfully!");

 	
    	}
    	
    
    }

}






























