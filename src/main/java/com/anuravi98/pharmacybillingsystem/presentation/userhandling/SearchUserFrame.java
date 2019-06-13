package com.anuravi98.pharmacybillingsystem.presentation.userhandling;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchUserFrame extends JFrame implements ActionListener {
    JPanel header, content;
    JLabel title, usernameLabel, nameLabel;
    JTextField username, name;
    JButton getUser, getAdmins, getNonAdmins;
    String[] columnNames = { "Username", "Name", "Password", "Admin" };;
    JTable resultTable;
    JScrollPane scrollPane;
    Object[][] items;
    DefaultTableModel resultModel;

    /**
     * Constructor of the Search User Interface
     */
    public SearchUserFrame() {
        setTitle("Search");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        title = new JLabel("Search User");
        usernameLabel = new JLabel("Username");
        nameLabel = new JLabel("Name");

        username = new JTextField(8);
        name = new JTextField(10);
        getUser = new JButton("Get Users");
        getAdmins = new JButton("Get Admins");
        getNonAdmins = new JButton("Get Non Admins");

        header = new JPanel(new GridLayout(3, 1));
        JPanel header1 = new JPanel(new FlowLayout());
        JPanel header2 = new JPanel(new FlowLayout());
        JPanel header3 = new JPanel(new FlowLayout());
        JPanel footer = new JPanel(new FlowLayout());
        header1.add(title);
        header2.add(usernameLabel);
        header2.add(username);
        header3.add(nameLabel);
        header3.add(name);
        footer.add(getUser);
        footer.add(getAdmins);
        footer.add(getNonAdmins);

        header.add(header1);
        header.add(header2);
        header.add(header3);

        resultModel = new DefaultTableModel();
        for (int i = 0; i < 4; i++) {
            resultModel.addColumn(columnNames[i]);
        }
        resultTable = new JTable(resultModel);
        scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);

        content = new JPanel(new GridLayout(1, 1));
        content.add(scrollPane);

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        getUser.addActionListener(this);
        getAdmins.addActionListener(this);
        getNonAdmins.addActionListener(this);

        setSize(900, 400);
        setVisible(true);

    }

    /**
     * Event Handler for the Action Event
     *
     * @param e The Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getUser) {
            if (!username.getText().isEmpty()) {
                name.setText("");
                items = service.user.UserAccess.toTwoDArray(service.user.UserAccess.lookupByUserName(username.getText()));
                resultModel.setRowCount(0);
                for (int i = 0; i < items.length; i++) {
                    resultModel.addRow(items[i]);
                }
            } else if (!name.getText().isEmpty()) {
                username.setText("");
                items = service.user.UserAccess.toTwoDArray(service.user.UserAccess.lookupByUserName(name.getText()));
                resultModel.setRowCount(0);
                for (int i = 0; i < items.length; i++) {
                    resultModel.addRow(items[i]);
                }
            }
        } else if (e.getSource() == getAdmins) {
            name.setText("");
            username.setText("");
            items = service.user.UserAccess.toTwoDArray(service.user.UserAccess.lookupAllAdmins());
            resultModel.setRowCount(0);
            for (int i = 0; i < items.length; i++) {
                resultModel.addRow(items[i]);
            }
        } else if (e.getSource() == getNonAdmins) {
            name.setText("");
            username.setText("");
            items = service.user.UserAccess.toTwoDArray(service.user.UserAccess.lookupAllNonAdmins());
            resultModel.setRowCount(0);
            for (int i = 0; i < items.length; i++) {
                resultModel.addRow(items[i]);
            }
        }
    }
}