package com.anuravi98.pharmacybillingsystem.presentation.producthandling;

import com.anuravi98.pharmacybillingsystem.integration.product.Product;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import org.json.simple.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;

public class AddProductFrame extends JFrame implements ActionListener, ItemListener {
    JPanel header, content, footer;
    JLabel title, idLabel, nameLabel, buyLabel, sellLabel, typeLabel, stockLabel,generic_nameLabel,manufacturerLabel,gstLabel,
            cgstLabel,unit_stripsLabel,categoryLabel,hsn_codeLabel;;
    JTextField id, name, buy, sell, stock,generic_name,manufacturer,gst,cgst,category,hsn_code;
    JCheckBox type,units_strips;
    JButton addButton, clearButton;
    Product product;
    boolean typeValue,units_strips_value;
    JSONObject jsonObject;

    /**
     * Constructor of the Add Product Interface
     */
    public AddProductFrame() {
        setTitle("Add Product");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        title = new JLabel("Add Product");
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
        id.setEditable(false);
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
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
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
        footer.add(addButton);
        footer.add(clearButton);
        content.setSize(800, 300);
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader("billing.json");
            Object obj = parser.parse(fr);
            jsonObject = (JSONObject) obj;
            fr.close();
            Long ID = (Long) jsonObject.get("productID");
            id.setText(Long.toString(ID));
        } catch (Exception e) {
            e.printStackTrace();
        }

        addButton.addActionListener(this);
        clearButton.addActionListener(this);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            name.setText("");
            buy.setText("");
            sell.setText("");
            stock.setText("");
        } else if (e.getSource() == addButton) {
            product = new Product(Integer.parseInt(id.getText()),name.getText(),generic_name.getText(),manufacturer.getText(),type.getText(),category.getText(),
                    new BigDecimal (buy.getText()), new BigDecimal (sell.getText()),new BigDecimal(stock.getText()),
                    new BigDecimal (cgst.getText()),new BigDecimal (gst.getText()),Integer.parseInt(hsn_code.getText()),units_strips.getText());
            if (product.insertProduct()) {
                int newID = Integer.parseInt(id.getText()) + 1;
                id.setText(newID + "");
                try {
                    FileWriter fw = new FileWriter("billing.json");
                    jsonObject.put("productID", new Long(newID));
                    fw.write(jsonObject.toJSONString());
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    name.setText("");
                    buy.setText("");
                    sell.setText("");
                    stock.setText("");
                }
            }
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
