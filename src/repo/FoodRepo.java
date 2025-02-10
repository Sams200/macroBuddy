package repo;
import model.Entry;
import model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static repo.Conn.connect;
public class FoodRepo {

    /*
    used for crud on both food and recipes in the database
     */

    public static ArrayList<Food> readAll(){
        /*
        read all the foods in the database
        sorted alphabetically
         */
        ArrayList<Food> foodList=new ArrayList<>();
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("select * from \"Food\" order by \"Name\"");

            ResultSet rs= prps.executeQuery();

            while(rs.next()) {
                Food food=new Food();
                food.setName(rs.getString("Name"));
                food.setProducer_id(rs.getInt("Producer_Id"));
                food.setKcal(rs.getInt("Kcal"));
                food.setProtein(rs.getFloat("Protein"));
                food.setFat(rs.getFloat("Fat"));
                food.setCarbs(rs.getFloat("Carbs"));
                food.setServing_size(rs.getFloat("Serving_Size"));
                food.setServing_units(rs.getString("Serving_Units"));
                food.setId(rs.getInt("id"));
                food.setRecipeId((short)0);

                PreparedStatement prps2=null;
                prps2=con.prepareStatement("select * from \"Producers\" where id=?");
                prps2.setInt(1,food.getProducer_id());
                ResultSet rs2=prps2.executeQuery();
                if(rs2.next()){
                    String s=rs2.getString("Name");
                    char[] sChar=s.toCharArray();
                    sChar[0]=Character.toUpperCase(sChar[0]);
                    s=String.valueOf(sChar);
                    food.setProducer(s);
                }else{
                    food.setProducer("Unknown");
                }
                rs2.close();
                prps2.close();
                foodList.add(food);
            }
            rs.close();
            return foodList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
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
    public static ArrayList<Food> getFoodByName(String text){
        /*
        read specific foods in the database
        sorted alphabetically
         */
        ArrayList<Food> foodList=new ArrayList<>();
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("select * from \"Food\"\n" +
                    "where \"Name\" ilike ? \n" +
                    "order by \"Name\"");
            prps.setString(1,"%"+text+"%");
            ResultSet rs= prps.executeQuery();

            while(rs.next()) {
                Food food=new Food();
                food.setName(rs.getString("Name"));
                food.setProducer_id(rs.getInt("Producer_Id"));
                food.setKcal(rs.getInt("Kcal"));
                food.setProtein(rs.getFloat("Protein"));
                food.setFat(rs.getFloat("Fat"));
                food.setCarbs(rs.getFloat("Carbs"));
                food.setServing_size(rs.getFloat("Serving_Size"));
                food.setServing_units(rs.getString("Serving_Units"));
                food.setId(rs.getInt("id"));
                food.setRecipeId((short)0);

                PreparedStatement prps2=null;
                prps2=con.prepareStatement("select * from \"Producers\" where id=?");
                prps2.setInt(1,food.getProducer_id());
                ResultSet rs2=prps2.executeQuery();
                if(rs2.next()){
                    String s=rs2.getString("Name");
                    char[] sChar=s.toCharArray();
                    sChar[0]=Character.toUpperCase(sChar[0]);
                    s=String.valueOf(sChar);
                    food.setProducer(s);
                }else{
                    food.setProducer("Unknown");
                }
                rs2.close();
                prps2.close();
                foodList.add(food);
            }
            rs.close();
            return foodList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
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
    public static ArrayList<Food> readAllRecipe(){
        /*
        read all the recipes in the database
        sorted alphabetically
         */
        ArrayList<Food> foodList=new ArrayList<>();
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement(
                    "select * from recipes_calories\n" +
                    "order by \"Name\"");

            ResultSet rs= prps.executeQuery();

            while(rs.next()) {
                Food food=new Food();
                food.setName(rs.getString("Name"));
                food.setProducer_id(0);
                food.setKcal(rs.getInt("Kcal"));
                food.setProtein(rs.getFloat("Protein"));
                food.setFat(rs.getFloat("Fat"));
                food.setCarbs(rs.getFloat("Carbs"));
                food.setServing_size(1);
                food.setServing_units("Serving");
                food.setId(0);
                food.setRecipeId(rs.getShort("Recipe_id"));
                food.setProducer("User");
                foodList.add(food);
            }
            rs.close();
            return foodList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
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

    public static ArrayList<Food> readRecipeName(String text){
        /*
        read all the recipes in the database
        sorted alphabetically
         */
        ArrayList<Food> foodList=new ArrayList<>();
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("select * from recipes_calories\n" +
                    "where \"Name\" ilike ? \n" +
                    "order by \"Name\"");
            prps.setString(1,"%"+text+"%");
            ResultSet rs= prps.executeQuery();

            while(rs.next()) {
                Food food=new Food();
                food.setName(rs.getString("Name"));
                food.setProducer_id(0);
                food.setKcal(rs.getInt("Kcal"));
                food.setProtein(rs.getFloat("Protein"));
                food.setFat(rs.getFloat("Fat"));
                food.setCarbs(rs.getFloat("Carbs"));
                food.setServing_size(1);
                food.setServing_units("Serving");
                food.setId(0);
                food.setRecipeId(rs.getShort("Recipe_id"));
                food.setProducer("User");
                foodList.add(food);
            }
            rs.close();
            return foodList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
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

    public static void create(Food food){
        /*
        creates a new food in the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();

            //first do stuff with the producer
            String producerName=food.getProducer().toLowerCase();
            prps=con.prepareStatement("select id from \"Producers\" where \"Name\"=?");
            prps.setString(1,producerName);
            ResultSet rss=prps.executeQuery();
            if(rss.next()) {
                food.setProducer_id(rss.getInt("id"));
            } else {
                //create a producer with this name in the database
                PreparedStatement prps2= con.prepareStatement(
                        "INSERT INTO public.\"Producers\" (\"Name\", id)\n" +
                                "VALUES (?, DEFAULT) returning \"id\"");
                prps2.setString(1,producerName);
                ResultSet rs2= prps2.executeQuery();
                rs2.next();
                food.setProducer_id(rs2.getInt("id"));
                rs2.close();
            }
            rss.close();
            prps.close();

            prps=con.prepareStatement("INSERT INTO public.\"Food\" (\"Name\", \"Producer_Id\", \"KCal\", \"Protein\", \"Fat\", \"Carbs\", \"Serving_Size\", \"Serving_Units\",\n" +
                    "                           id)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, DEFAULT) returning \"id\"");
            prps.setString(1,food.getName());
            prps.setInt(2,food.getProducer_id());
            prps.setInt(3,food.getKcal());
            prps.setFloat(4,food.getProtein());
            prps.setFloat(5,food.getFat());
            prps.setFloat(6,food.getCarbs());
            prps.setFloat(7,food.getServing_size());
            prps.setString(8,food.getServing_units());
            ResultSet rs= prps.executeQuery();
            rs.next();
            food.setId(rs.getInt("id"));
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

    public static void update(Food food, Food food2){
        /*
        update the info of a food
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("UPDATE public.\"Food\"\n" +
                    "SET \"Name\"          = ?,\n" +
                    "    \"Producer_Id\"   = ?,\n" +
                    "    \"KCal\"          = ?,\n" +
                    "    \"Protein\"       = ?,\n" +
                    "    \"Fat\"           = ?,\n" +
                    "    \"Carbs\"         = ?,\n" +
                    "    \"Serving_Size\"  = ?,\n" +
                    "    \"Serving_Units\" = ?\n" +
                    "WHERE id = ?;");
            prps.setString(1,food2.getName());
            prps.setInt(2,food2.getProducer_id());
            prps.setInt(3,food2.getKcal());
            prps.setFloat(4,food2.getProtein());
            prps.setFloat(5,food2.getFat());
            prps.setFloat(6,food2.getCarbs());
            prps.setFloat(7,food2.getServing_size());
            prps.setString(8,food2.getServing_units());
            prps.setInt(9,food.getId());

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

    public static boolean read(Food food, int id, short recipe_id){
        /*
        read from database and put into food
        return true if found and
        false if not found
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            if(id!=0) {
                prps=con.prepareStatement("SELECT * from \"Food\" where id=?");
                prps.setInt(1,id);
                ResultSet rs= prps.executeQuery();

                if(rs.next()) {
                    food.setName(rs.getString("Name"));
                    food.setProducer_id(rs.getInt("Producer_Id"));
                    food.setKcal(rs.getInt("Kcal"));
                    food.setProtein(rs.getFloat("Protein"));
                    food.setFat(rs.getFloat("Fat"));
                    food.setCarbs(rs.getFloat("Carbs"));
                    food.setServing_size(rs.getFloat("Serving_Size"));
                    food.setServing_units(rs.getString("Serving_Units"));
                    food.setId(id);
                    food.setRecipeId((short)0);
                    rs.close();

                    PreparedStatement prps2=null;
                    prps2=con.prepareStatement("select * from \"Producers\" where id=?");
                    prps2.setInt(1,food.getProducer_id());
                    ResultSet rs2=prps2.executeQuery();
                    if(rs2.next()){
                        String s=rs2.getString("Name");
                        char[] sChar=s.toCharArray();
                        sChar[0]=Character.toUpperCase(sChar[0]);
                        s=String.valueOf(sChar);
                        food.setProducer(s);
                    }else{
                        food.setProducer("Unknown");
                        rs2.close();
                    }
                    rs2.close();
                    prps2.close();
                    return true;
                }
                else {
                    rs.close();
                    return false;
                }
            }
            else if(recipe_id!=0) {
                prps=con.prepareStatement("select * from recipes_calories where \"Recipe_id\"=?");
                prps.setShort(1,recipe_id);
                ResultSet rs= prps.executeQuery();

                if(rs.next()) {
                    food.setName(rs.getString("Name"));
                    food.setProducer_id(0);
                    food.setKcal(rs.getInt("Kcal"));
                    food.setProtein(rs.getFloat("Protein"));
                    food.setFat(rs.getFloat("Fat"));
                    food.setCarbs(rs.getFloat("Carbs"));
                    food.setServing_size(1);
                    food.setServing_units("Serving");
                    food.setId(0);
                    food.setRecipeId(recipe_id);
                    food.setProducer("User");
                    rs.close();
                    return true;
                }
                else {
                    rs.close();
                    return false;
                }
            }
            else{
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

    public static void delete(int id){
        /*
        delete this food from the database
         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement("DELETE\n" +
                    "FROM public.\"Food\"\n" +
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

    public static void createRecipe(Food recipe, ArrayList<Entry> ingredients) {
        /*
        create a recipe with the name of the given recipe
        and the ingredients of ingredients array

         */
        Connection con=null;
        PreparedStatement prps=null;

        try{
            con=connect();
            prps=con.prepareStatement(
                    "INSERT INTO public.\"Recipes\" (id, \"Name\")\n" +
                    "VALUES (DEFAULT, ?)\n" +
                    "returning \"id\"");
            prps.setString(1,recipe.getName());
            ResultSet rs= prps.executeQuery();
            if(rs.next()){
                System.out.println(1111);
                int recipeId=rs.getInt("id");

                for(Entry entry:ingredients){
                    prps=con.prepareStatement("" +
                            "INSERT INTO public.\"Ingredients_List\" (\"Food_id\", \"Recipe_id\", \"Quantity\")\n" +
                            "VALUES (?, ?, ?);");
                    prps.setInt(1,entry.getFood_id());
                    prps.setInt(2,recipeId);
                    prps.setFloat(3,entry.getQuantity());
                    prps.executeUpdate();
                }
            }else {
                throw new SQLException();
            }
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
