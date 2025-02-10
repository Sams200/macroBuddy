package repo;

import model.Entry;
import model.MealType;

import java.sql.*;

import static repo.Conn.connect;

public class EntryRepo {
    /*
    used for crud on entries in the database
     */

    public static void create(Entry entry){
        /*
        creates a new food in the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement(
                    "INSERT INTO public.\"Entries\" (id, \"Date\", \"Food_id\", \"Quantity\", \"Recipes_id\", \"Meal\")\n" +
                            "VALUES (DEFAULT, ?, ?, ?, ?, ?) returning \"id\"");
            prps.setDate(1,entry.getDate());
            if(entry.getFood_id()!=0 && entry.getRecipe_id()==0)
            {
                //System.out.println("EntryRepo: Food "+entry.getFood_id()+" "+entry.getRecipe_id());
                prps.setInt(2,entry.getFood_id());
                prps.setNull(4,Types.NULL);
            }
            else if(entry.getFood_id()==0 && entry.getRecipe_id()!=0)
            {
                //System.out.println("EntryRepo: Recipe "+entry.getFood_id()+" "+entry.getRecipe_id());
                prps.setNull(2,Types.NULL);
                prps.setInt(4,entry.getRecipe_id());
            }
            else
            {
                //System.out.println("EntryRepo: error "+entry.getFood_id()+" "+entry.getRecipe_id());
                throw new SQLException();
            }
            prps.setFloat(3,entry.getQuantity());
            prps.setString(5,entry.getMeal().getMeal());
            ResultSet rs= prps.executeQuery();
            rs.next();
            entry.setId(rs.getInt("id"));
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

    public static boolean read(Entry entry, int id){
        /*
        read from database and put into food
        return true if found and
        false if not found
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("SELECT * from \"Entries\" where id=?");
            prps.setInt(1,id);
            ResultSet rs= prps.executeQuery();

            if(rs.next()) {
                entry.setDate(rs.getDate("Date"));
                entry.setFood_id(rs.getInt("Food_id"));
                entry.setQuantity(rs.getInt("Quantity"));
                entry.setRecipe_id(rs.getShort("Recipes_id"));
                entry.setMeal(MealType.getInstance(rs.getString("Meal")));
                entry.setId(id);
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

    public static void update(int id, Entry entry2){
        /*
        update the info of a food
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("UPDATE public.\"Entries\"\n" +
                    "SET \"Date\"       = ?,\n" +
                    "    \"Food_id\"    = ?,\n" +
                    "    \"Quantity\"   = ?,\n" +
                    "    \"Recipes_id\" = ?,\n" +
                    "    \"Meal\"       = ?\n" +
                    "WHERE id = ?;");
            prps.setDate(1,entry2.getDate());
            if(entry2.getFood_id()!=0)
                prps.setInt(2,entry2.getFood_id());
            else
                prps.setNull(2, Types.NULL);
            prps.setFloat(3,entry2.getQuantity());
            if(entry2.getRecipe_id()!=0)
                prps.setInt(4,entry2.getRecipe_id());
            else
                prps.setNull(4,Types.NULL);
            prps.setString(5,entry2.getMeal().getMeal());
            prps.setInt(6,id);

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

    public static void delete(int id){
        /*
        delete the entry from the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("DELETE\n" +
                    "FROM public.\"Entries\"\n" +
                    "WHERE id = ?;");
            prps.setInt(1,id);
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
