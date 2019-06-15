package com.anuravi98.pharmacybillingsystem.integration.product;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
//import static groovy.xml.Entity.para;

public class Product {
    private int ID;
    private String name,generic_name,manufacturer,type,category;
    private BigDecimal cp, sp, stock,cgst,gst;
    private int hsn_code;
    private String units_strips;

    private static Connection conn;
    private Date ExpiryDate;
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    /**
     *
     * @param ID id of product
     */
    public Product(int ID)
    {
        this.ID=ID;
    }

    /**
     *
     * @param name name of the medicine
     * @param generic_name generic name of the medicine
     * @param manufacturer manufacturer name
     * @param type type of drug
     * @param category category of medicine
     * @param cp costprice
     * @param sp sellprice
     * @param stock stocks
     * @param cgst central gst
     * @param gst gst
     * @param hsn_code hsn_code
     * @param units_strips choose
     */
    public Product(String name,String generic_name,String manufacturer,String type,String category,BigDecimal cp,String expdate,
                    BigDecimal sp,BigDecimal stock,BigDecimal cgst,BigDecimal gst,int hsn_code,String units_strips) throws Exception
    {
        this.category= category;
        this.generic_name=generic_name;
        this.cgst=cgst;
        this.gst=gst;
        this.stock=stock;
        this.name=name;
        this.manufacturer=manufacturer;
        this.type=type;
        this.cp=cp;
        this.sp=sp;
        this.hsn_code=hsn_code;
        this.units_strips=units_strips;
        this.ExpiryDate=format.parse(expdate);


    }

    /**
     *
     * @param ID  id of the medicine
     * @param name name of the medicine
     * @param generic_name generic name of the medicine
     * @param manufacturer manufacturer name
     * @param type type of drug
     * @param category category of medicine
     * @param cp costprice
     * @param sp sellprice
     * @param stock stocks
     * @param cgst central gst
     * @param gst gst
     * @param hsn_code hsn_code
     * @param units_strips choose
     */
    public Product(int ID,String name,String generic_name,String manufacturer,String type,String category,BigDecimal cp,String expdate,
                    BigDecimal sp,BigDecimal stock,BigDecimal cgst,BigDecimal gst,int hsn_code,String units_strips) throws Exception
    { this.ID=ID;
        this.category= category;
        this.generic_name=generic_name;
        this.cgst=cgst;
        this.gst=gst;
        this.stock=stock;
        this.name=name;
        this.manufacturer=manufacturer;
        this.type=type;
        this.cp=cp;
        this.sp=sp;
        this.hsn_code=hsn_code;
        this.units_strips=units_strips;
        this.ExpiryDate=format.parse(expdate);


    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getCp() {
        return cp;
    }

    public void setCp(BigDecimal cp) {
        this.cp = cp;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getCgst() {
        return cgst;
    }

    public void setCgst(BigDecimal cgst) {
        this.cgst = cgst;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public int getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(int hsn_code) {
        this.hsn_code = hsn_code;
    }

    public String getUnits_strips() {
        return units_strips;
    }

    public void setUnits_strips(String units_strips) {
        this.units_strips = units_strips;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        ExpiryDate = expiryDate;
    }

    /**
     * Function to create the table for products
     *int hsn_code,String units_strips
     * @return boolean Success of the task
     */
    public static boolean createTable() {
        conn = dbconnection.DBconnect.getConnection("products");
        try {
            assert conn != null;
            Statement s = conn.createStatement();
            s.executeUpdate(
                    "create table products (ID integer primary key, name varchar(50),g_name varchar(50),category varchar(50)," +
                            "expdate DATE,manufacturer varchar(50),gst decimal,cgst decimal,hsn_code integer,units_strips varchar(20)" +
                            "cp Decimal, sp decimal, type varchar(5), stock decimal)");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Function to add products to the table
     *
     * @return boolean Success of the task
     */
    public boolean insertProduct() {
        conn = dbconnection.DBconnect.getConnection("products");
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement("insert into products values(?,?,?,?,?,?)");
            ps.setInt(1, ID);
            ps.setString(2, name);
            ps.setString(3,generic_name);
            ps.setString(4, category);
            ps.setDate(5, (java.sql.Date) ExpiryDate);
            ps.setString(6, manufacturer);
            ps.setBigDecimal(7,gst);
            ps.setBigDecimal(8,cgst);
            ps.setInt(9,hsn_code);
            ps.setString(10,units_strips);
            ps.setBigDecimal(11,cp);
            ps.setBigDecimal(12,sp);
            ps.setString(13,type);
            ps.setBigDecimal(14,stock);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Function to delete products from the table
     *
     * @return boolean Success of the task
     */
    public boolean deleteProduct() {
        conn = dbconnection.DBconnect.getConnection("products");
        PreparedStatement ps;
        try {
            assert conn != null;
            ps = conn.prepareStatement("delete from products where ID = ?");
            ps.setInt(1, ID);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Function to update products in the table
     *
     * @return boolean Success of the task
     */
    public boolean updateProduct() {
        conn = dbconnection.DBconnect.getConnection("products");
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(
                    "update products set name = ?, cp = ?, sp = ?, gst = ?, stock = ? where id = ? ");
            ps.setString(1, name);
            ps.setBigDecimal(2, cp);
            ps.setBigDecimal(3, sp);
            ps.setBigDecimal(4, gst);
            ps.setBigDecimal(5, stock);
            ps.setInt(6, ID);
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String[] toStringArray() {
        String[] toreturn = { Integer.toString(ID), name, cp.toString(), sp.toString(), type,
                stock.toString() };
        return toreturn;
    }
}