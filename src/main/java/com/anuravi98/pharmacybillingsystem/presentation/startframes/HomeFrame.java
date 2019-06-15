package com.anuravi98.pharmacybillingsystem.presentation.startframes;

import com.anuravi98.pharmacybillingsystem.integration.user.User;
import com.anuravi98.pharmacybillingsystem.presentation.billing.BillingFrame;
import com.anuravi98.pharmacybillingsystem.presentation.producthandling.*;
import com.anuravi98.pharmacybillingsystem.presentation.userhandling.UserHomeFrame;
import com.anuravi98.pharmacybillingsystem.service.product.ExpiredProducts;
import com.anuravi98.pharmacybillingsystem.service.product.ProductAccess;
import com.anuravi98.pharmacybillingsystem.service.product.StockStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The JFrame for the GUI of Main Menu
 */
public class HomeFrame extends JFrame implements ActionListener {

    private  JButton expiredproducts;
    private JLabel title;
    private JButton addProduct, deleteProduct, updateProduct, billing, searchProduct, stockStatement,userManagement;
    private JPanel top,bottom;
    private GridBagConstraints gbc;
    User user;

    /**
     * Constructor of the Main Menu
     * @param user The user of the session
     */
    public HomeFrame(User user){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new SpringLayout());
        this.user = user;

        top = new JPanel();
        top.setLayout(new FlowLayout());
        bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        title = new JLabel("Shop Management");
        addProduct = new JButton("Add Product");
        deleteProduct = new JButton("Delete Product");
        updateProduct = new JButton("Update Product");
        billing = new JButton("Billing");
        searchProduct = new JButton("Search");
        stockStatement = new JButton("Stock Statement");
        expiredproducts= new JButton("Expired Products");


        top.add(title);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottom.add(addProduct,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        bottom.add(deleteProduct,gbc);
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        bottom.add(updateProduct,gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        bottom.add(billing,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        bottom.add(searchProduct,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        bottom.add(stockStatement,gbc);
        gbc.gridx=2;
        gbc.gridy=2;
        bottom.add(expiredproducts,gbc);


        if (user.getAdmin()){
            userManagement = new JButton("Users");
            gbc.gridx = 0;
            gbc.gridy = 4;
            bottom.add(userManagement,gbc);
            userManagement.addActionListener(this);
        }

        addProduct.addActionListener(this);
        deleteProduct.addActionListener(this);
        updateProduct.addActionListener(this);
        billing.addActionListener(this);
        searchProduct.addActionListener(this);
        stockStatement.addActionListener(this);
        expiredproducts.addActionListener(this);
        setVisible(true);
        add(top,BorderLayout.NORTH);
        add(bottom,BorderLayout.CENTER);
        setSize(300,300);
    }

    /**
     * Event Handler for the Action Event
     *
     * @param e The Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addProduct){
            AddProductFrame apf = new AddProductFrame();
        }
        else if(e.getSource() == deleteProduct){
            DeleteProductFrame dpf = new DeleteProductFrame();
        }
        else if(e.getSource() == updateProduct){
            UpdateProductFrame upf = new UpdateProductFrame();
        }
        else if(e.getSource() == billing){
            BillingFrame bf = new BillingFrame(user);
        }
        else if(e.getSource() == searchProduct){
            SearchProductFrame spf = new SearchProductFrame();
        }
        else if(e.getSource() == stockStatement){
            StockStatement sStatement = new StockStatement();
            sStatement.start();
        }
        else if (e.getSource()== expiredproducts)
        {
            ExpiredProducts eStatement=new ExpiredProducts();
            eStatement.start();
        }
        else if(e.getSource() == userManagement){
            UserHomeFrame uhf = new UserHomeFrame();
        }

    }
}
