package com.anuravi98.pharmacybillingsystem.presentation.producthandling;

import com.anuravi98.pharmacybillingsystem.integration.product.Product;
import com.anuravi98.pharmacybillingsystem.service.product.ProductAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class UpdateProductFrame extends JFrame implements ActionListener, ItemListener {
    JPanel header, content, footer;
    JLabel title, idLabel, nameLabel, buyLabel, sellLabel, typeLabel, stockLabel,generic_nameLabel,manufacturerLabel,gstLabel,
            cgstLabel,unit_stripsLabel,categoryLabel,hsn_codeLabel;
    JTextField id, name, buy, sell, stock,generic_name,manufacturer,gst,cgst,category,hsn_code;
    JCheckBox type,units_strips;
    JButton updateButton, clearButton, getProduct;
    Product product;
    boolean typeValue,units_strips_value;


    public UpdateProductFrame() {
        setTitle("Update Product");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        title = new JLabel("Update Product");
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        buyLabel = new JLabel("Buy Price");
        sellLabel = new JLabel("Sell Price");
        typeLabel = new JLabel("Type");
        stockLabel = new JLabel("Stock");
        generic_nameLabel=new JLabel("Generic name");
        manufacturerLabel=new JLabel("manufacturer");
        gstLabel=new JLabel("gst value");
        cgstLabel=new JLabel("cgst value");
        unit_stripsLabel=new JLabel("units");
        categoryLabel=new JLabel("category");
        hsn_codeLabel=new JLabel("Hsn_code");
        id = new JTextField();
        name = new JTextField();
        buy = new JTextField();
        sell = new JTextField();
        stock = new JTextField();
        manufacturer= new JTextField();
        gst= new JTextField();
        cgst=new JTextField();
        category=new JTextField();
        hsn_code=new JTextField();
        type = new JCheckBox("No.s"); // If unchecked, Considers as Kgs
        typeValue = false;
        units_strips=new JCheckBox("units");
        units_strips_value=false;
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");
        getProduct = new JButton("Get Product");
        header = new JPanel(new FlowLayout());
        content = new JPanel(new SpringLayout());
        footer = new JPanel(new FlowLayout());

        content.add(idLabel);
        content.add(nameLabel);
        content.add(buyLabel);
        content.add(sellLabel);
        content.add(typeLabel);
        content.add(stockLabel);
        content.add(generic_nameLabel);content.add(manufacturerLabel);content.add(gstLabel);content.add(cgstLabel);content.add(unit_stripsLabel);
        content.add(categoryLabel);content.add(hsn_codeLabel);
        content.add(id);
        content.add(name);
        content.add(buy);
        content.add(sell);
        content.add(type);
        content.add(stock);
        content.add(generic_name);content.add(manufacturer);content.add(gst);content.add(cgst);content.add(units_strips);
        content.add(category);content.add(hsn_code);
        presentation.springlayout.SpringUtilities.makeGrid(content, 2, 13, 10, 20, 10, 10);
        header.add(title);
        footer.add(getProduct);
        footer.add(updateButton);
        footer.add(clearButton);
        content.setSize(800, 300);
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        updateButton.addActionListener(this);
        clearButton.addActionListener(this);
        getProduct.addActionListener(this);
        type.addItemListener(this);

        setVisible(true);
        setSize(800, 170);

    }

    /**
     * Event Handler for the Action Event
     *
     * @param e The Action Event
     */
    @Override
    //int ID,String name,String generic_name,String manufacturer,String type,String category,BigDecimal cp,
    //                    BigDecimal sp,BigDecimal stock,BigDecimal cgst,BigDecimal gst,int hsn_code,String units_strips
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            name.setText("");
            buy.setText("");
            sell.setText("");
            stock.setText("");
        } else if (e.getSource() == updateButton && !id.getText().isEmpty()) {
            product = new Product(Integer.parseInt(id.getText()),name.getText(),generic_name.getText(),manufacturer.getText(),type.getText(),category.getText(),
                    new BigDecimal (buy.getText()), new BigDecimal (sell.getText()),new BigDecimal(stock.getText()),
                    new BigDecimal (cgst.getText()),new BigDecimal (gst.getText()),Integer.parseInt(hsn_code.getText()),units_strips.getText());
            if (product.updateProduct()) {
                id.setText(Integer.parseInt(id.getText()) + "");
                name.setText("");
                buy.setText("");
                sell.setText("");
                stock.setText("");
            }
        } else if (e.getSource() == getProduct) {
            product = ProductAccess.lookupByID(Integer.parseInt(id.getText())).get(0);
            name.setText(product.getName());
            sell.setText(Double.toString(product.getSp().doubleValue()));
            buy.setText(Double.toString(product.getCp().doubleValue()));
            stock.setText(Double.toString((product.getStock().doubleValue())));
            typeValue = Boolean.parseBoolean(product.getType());
            type.setSelected(typeValue);
        }
    }

    /**
     * Event Handler for the Item Event
     *
     * @param e The Item Event
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == type) {
            typeValue = !typeValue;
        }
        if (e.getSource() == units_strips) {
            units_strips_value = !units_strips_value;
        }
    }
}

