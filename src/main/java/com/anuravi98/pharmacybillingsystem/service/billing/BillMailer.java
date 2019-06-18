package com.anuravi98.pharmacybillingsystem.service.billing;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Class providins service to store and mail bills
 */
public class BillMailer {
    String username;
    String password;
    Properties props;
    Session session;
    JSONObject jsonObject;

    /**
     * Constructor for BillMailer. Initialises necessary objects
     *
     * @param password The Gmail password for connecting to smtp.gmail.com
     */
    public BillMailer(String password) {
        this.password = password;
        JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader("billing.json");
            Object obj = parser.parse(fr);
            jsonObject = (JSONObject) obj;
            fr.close();
            username = (String) jsonObject.get("emailID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Username set.");
        props = new Properties();
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                final PasswordAuthentication passwordAuthentication = new PasswordAuthentication(username, password);
                return passwordAuthentication;
            }
        });
    }

    /**
     * Function to mail the bill
     *
     * @param bill          The bill as String
     * @param customerEmail The Email ID of the customer
     * @param billNo        The bill number of the bill
     * @return boolean Success of the task
     */
    public boolean mailBill(String bill, String customerEmail, String billNo) {

        try {
            System.out.println("Creating file for bill");
            PrintWriter writer;
            writer = new PrintWriter("bills/" + billNo + ".txt", "UTF-8");
            writer.println(bill);
            writer.close();
            System.out.println("Bill file created");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");

        try {
            System.out.println("Sending mail");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("johndoe@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerEmail));
            message.setSubject("Bill No: " + billNo);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file = "bills/" + billNo + ".txt";
            String fileName = "Bill";
            DataSource source = (DataSource) new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
            messageBodyPart.setFileName(fileName);

            messageBodyPart2.setText("Find attached, your bill.");

            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Mail sent");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

