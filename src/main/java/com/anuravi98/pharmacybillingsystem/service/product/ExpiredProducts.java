package com.anuravi98.pharmacybillingsystem.service.product;

import com.anuravi98.pharmacybillingsystem.integration.product.Product;

import javax.swing.*;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpiredProducts extends Thread {
    /**
     * Run the thread
     */
    @Override
    public void run() {
        String fileName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String statement = "";
        try {
            PrintWriter stockWriter = new PrintWriter("ExpiredProducts/" + fileName + ".txt", "UTF-8");
            ArrayList<Product> products = ProductAccess.expiredProducts();
            JOptionPane.showMessageDialog(null, "Generating expired products list You will be notified once it is complete");
            System.out.println(
                    "Expired products Generation initiated. Please dont close the application till you get the confirmation of completion of this task");
            for (int i = 0; i < products.size(); i++) {
                statement = statement + products.get(i).toString();
            }
            stockWriter.write(statement);
            stockWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "Expired products list Generation Complete");
            System.out.println("Expired products list Generation Complete");
        }
    }
}