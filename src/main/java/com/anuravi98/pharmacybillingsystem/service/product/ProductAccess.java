package com.anuravi98.pharmacybillingsystem.service.product;
import com.anuravi98.pharmacybillingsystem.integration.product.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ProductAccess {
    private static Connection conn = dbconnection.DBconnect.getConnection("products");
    public static ArrayList<Product> lookupByID(int ID) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from products where id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"),rs.getString("generic_name"),
                        rs.getString("manufacturer"),rs.getString ("type"),rs.getString ("category"),
                        rs.getBigDecimal( "cp"), rs.getBigDecimal ("sp"),
                        rs.getBigDecimal ("stock"),rs.getBigDecimal(" cgst"),
                        rs.getBigDecimal(" gst"),rs.getInt("hsn_code"),rs.getString(" units_strips")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<Product> lookup(String name) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from products where name like ?");
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //String manufacturer,String type,String category,BigDecimal cp,
                //                    BigDecimal sp,BigDecimal stock,BigDecimal cgst,BigDecimal gst,int hsn_code,String units_strips
                products.add(new Product(rs.getInt("id"), rs.getString("name"),rs.getString("generic_name"),
                        rs.getString("manufacturer"),rs.getString ("type"),rs.getString ("category"),
                        rs.getBigDecimal( "cp"), rs.getBigDecimal ("sp"),
                        rs.getBigDecimal ("stock"),rs.getBigDecimal(" cgst"),
                        rs.getBigDecimal(" gst"),rs.getInt("hsn_code"),rs.getString(" units_strips")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Product> lookupAll() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"),rs.getString("generic_name"),
                        rs.getString("manufacturer"),rs.getString ("type"),rs.getString ("category"),
                        rs.getBigDecimal( "cp"), rs.getBigDecimal ("sp"),
                        rs.getBigDecimal ("stock"),rs.getBigDecimal(" cgst"),
                        rs.getBigDecimal(" gst"),rs.getInt("hsn_code"),rs.getString(" units_strips")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String[][] toTwoDArray(ArrayList<Product> items) {
        String[][] toreturn = new String[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            toreturn[i] = items.get(i).toStringArray();
        }
        return toreturn;
    }


    public static class StockStatement {
    }
}
