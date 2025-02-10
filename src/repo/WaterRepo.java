package repo;

import model.Water;

import java.sql.*;

import static repo.Conn.connect;

public class WaterRepo {
    /*
    crud for water in the database
     */

    public static void create(Water water) {
        /*
        creates a new entry in the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("INSERT INTO public.\"water\" (day, quantity)\n" +
                    "VALUES (?, ?);");
            prps.setDate(1,water.getDate());
            prps.setShort(2,water.getQuantity());
            prps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (prps != null)
                    prps.close();
                if (con != null)
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean update(Date date, Water water) {
        /*
        update the info of a water
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("UPDATE public.\"water\" SET \"day\"=? , \"quantity\"=? where day=? returning day");
            prps.setDate(1,water.getDate());
            prps.setShort(2, water.getQuantity());
            prps.setDate(3,date);
            ResultSet rs=prps.executeQuery();
            if(rs.next())
                return true;
            return false;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try {
                if (prps != null)
                    prps.close();
                if (con != null)
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean read(Water water,Date date){
        /*
        read from database and put into water
        return true if found and
        false if not found
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("SELECT * from \"water\" where day=?");
            prps.setDate(1,date);
            ResultSet rs= prps.executeQuery();

            if(rs.next()) {
                water.setQuantity(rs.getShort("quantity"));
                water.setDate(date);
                rs.close();
                return true;
            }
            else {
                rs.close();
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        finally{
            try {
                if (prps != null)
                    prps.close();
                if (con != null)
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void delete(Date date){
        /*
        delete this water from the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("DELETE FROM public.\"water\" WHERE day=?;");
            prps.setDate(1,date);
            prps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (prps != null)
                    prps.close();
                if (con != null)
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
