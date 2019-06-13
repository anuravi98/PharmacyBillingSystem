package com.anuravi98.pharmacybillingsystem.presentation.userhandling;

import com.anuravi98.pharmacybillingsystem.exception.UserNotFoundException;
import com.anuravi98.pharmacybillingsystem.integration.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserFrame extends JFrame implements ActionListener {
    JLabel title, usernameLabel, nameLabel;
    JTextField username, name;
    JButton deleteButton, getUserButton;
    JPanel header, footer, content;

    /**
     * Constructor of the Delete user Interface
     */
    public DeleteUserFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete User");


        title = new JLabel("Delete User");
        usernameLabel = new JLabel("Username");
        nameLabel = new JLabel("Name");
        username = new JTextField();
        name = new JTextField();
        name.setEditable(false);
        getUserButton = new JButton("Get User");
        deleteButton = new JButton("Delete");

        header = new JPanel(new FlowLayout());
        content = new JPanel(new SpringLayout());
        footer = new JPanel(new FlowLayout());

        header.add(title);
        content.add(usernameLabel);
        content.add(username);
        content.add(nameLabel);
        content.add(name);
        presentation.springlayout.SpringUtilities.makeGrid(content, 2, 2, 10, 20, 10, 10);
        footer.add(getUserButton);
        footer.add(deleteButton);

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        getUserButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setSize(200, 200);
        setVisible(true);
    }

    /**
     * Event Handler for the Action Event
     *
     * @param e The Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getUserButton) {
            if (!username.getText().isEmpty()) {
                User user = service.user.UserAccess.lookupByUserName(username.getText()).get(0);
                if (user != null) {
                    name.setText(user.getName());
                }
            }
        } else if (e.getSource() == deleteButton) {
            if (!username.getText().isEmpty()) {
                User user = service.user.UserAccess.lookupByUserName(username.getText()).get(0);
                if (user != null) {
                    try {
                        if (user.deleteUser()) {
                            dispose();
                        }
                    } catch (UserNotFoundException unfe) {
                        unfe.printStackTrace();
                    }
                }
            }
        }
    }
}
