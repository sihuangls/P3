package Storemanager;

import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class ProductServer {
    static String dbfile = "E:\\final\\3700\\sql\\first.db";

    public static void main(String[] args) {

        int port = 1000;

        if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args)
                System.out.println(arg);
            port = Integer.parseInt(args[0]);
            dbfile = args[1];
        }

        try {
            ServerSocket server = new ServerSocket(port);

            System.out.println("Server is listening at port = " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                String command = in.nextLine();
                if (command.equals("GET")) {
                    String str = in.nextLine();
                    System.out.println("GET product with id = " + str);
                    int productID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Products WHERE ProductID = " + productID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getString("Barcode"));
//                            System.out.println(rs.getString("Barcode"));
                            out.println(rs.getString("Name")); // send back product name!
//                            System.out.println(rs.getString("Name"));
                            out.println(rs.getString("Expiration_Date"));
//                            System.out.println(rs.getString("Expiration_Date"));
                            out.println(rs.getDouble("Price")+ ""); // send back product price!
//                            System.out.println(rs.getString("Price"));
                            out.println(rs.getDouble("Tax_Rate")+ "");
//                            System.out.println(rs.getString("Tax_Rate"));
                            out.println(rs.getDouble("Quantity")+ ""); // send back product quantity!
//                            System.out.println(rs.getString("Quantity"));
                            out.println(rs.getString("Supplier")); //
//                            System.out.println(rs.getString("Supplier"));
                            out.println(rs.getString("Manufactured_Date")); 
//                            System.out.println(rs.getString("Manufactured_Date"));
                            

                            }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT")) {
                    String id = in.nextLine();  // read all information from client
//                    System.out.println(id);
                    String barcode = in.nextLine();
//                    System.out.println(barcode);
                    String name = in.nextLine();
//                    System.out.println(name);
                    String expiration_date = in.nextLine();
//                    System.out.println(expiration_date);
                    String price = in.nextLine();
//                    System.out.println(price);
                    String tax_rate = in.nextLine();
//                    System.out.println(tax_rate);
                    String quantity = in.nextLine();
//                    System.out.println(quantity);
                    String supplier = in.nextLine();
//                    System.out.println(supplier);
                    String manufactured_date = in.nextLine();
//                    System.out.println(manufactured_date);
                    
                    System.out.println("PUT command with ProductID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Products WHERE ProductID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Products WHERE ProductID = " + id);
                        }

                        sql = "INSERT INTO Products VALUES (" + id + ",\"" + barcode + "\"" + ",\"" + name + "\"" + ",\"" + expiration_date + "\","
                                + price + "," + tax_rate +"," + quantity + ",\"" + supplier + "\"," + "\"" + manufactured_date + "\"" + ")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                } else {
                    out.println(0); // logout unsuccessful!
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}