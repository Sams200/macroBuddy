package model;
import java.sql.*;

public class Water {
    /*
    all the water consumed in a day. This object is unique to every single day
     */
    private Date date;
    private short quantity;

    public Water(Date date, short quantity) {
        this.date = date;
        this.quantity = quantity;
    }
    public Water(){
    }
    public Date getDate() {
        return date;
    }
    public short getQuantity() {
        return quantity;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }


}
