package com.anuravi98.pharmacybillingsystem.service.product;


import javax.swing.*;
import java.util.ArrayList;

public class StockAlert extends Thread {
    /**
     * Run the thread
     */
    @Override
    public void run() {
        String statement = "";
        try {
            ArrayList<String> products = ProductAccess.stockAlert();

            for (int i = 0; i < products.size(); i++) {
                statement = statement +"\n"+ products.get(i);
            }
            JOptionPane.showMessageDialog(null, statement);

        } catch (Exception e) {
            e.printStackTrace();
        }         }
    }
