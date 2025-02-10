package repo;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import static repo.Conn.connect;

public class PageRepo {

    public static void updatePage(Page page, Date date){
        /*
        select entries with date date and update info in the page
         */
        Connection con=null;
        PreparedStatement prps=null;


        try{
            con=connect();
            prps=con.prepareStatement("select * from \"Entries\" where \"Date\"=? order by \"id\"");
            prps.setDate(1,date);

            ResultSet rs= prps.executeQuery();
            MealType mealType;
            Food food;

            //reset the page
            page.setBreMacroTotal(new Macro(0,0,0,0));
            page.setLunMacroTotal(new Macro(0,0,0,0));
            page.setDinMacroTotal(new Macro(0,0,0,0));
            page.setSnaMacroTotal(new Macro(0,0,0,0));
            page.setDayMacroTotal(new Macro(0,0,0,0));

            page.setBreakfast(new ArrayList<Entry>());
            page.setLunch(new ArrayList<Entry>());
            page.setDinner(new ArrayList<Entry>());
            page.setSnack(new ArrayList<Entry>());

            page.setWater(new Water());

            while(rs.next()){
                Entry entry=new Entry(rs.getInt("id"),
                                        rs.getDate("Date"),
                                        rs.getInt("Food_id"),
                                        rs.getFloat("Quantity"),
                                        rs.getShort("Recipes_id"),
                                        MealType.getInstance(rs.getString("Meal")));

                mealType=entry.getMeal();
                ArrayList<Entry> arrayList;
                food=entry.getFood();
                Macro macro1=new Macro();

                switch (mealType){
                    case BREAKFAST -> {
                        arrayList=page.getBreakfast();
                        arrayList.add(entry);
                        page.setBreakfast(arrayList);

                        macro1=page.getBreMacroTotal();
                        macro1.addMacro(food.getMacro(),entry.getQuantity());
                        page.setBreMacroTotal(macro1);
                        page.getDayMacroTotal().addMacro(food.getMacro(),entry.getQuantity());
                        break;
                    }
                    case LUNCH -> {
                        arrayList=page.getLunch();
                        arrayList.add(entry);
                        page.setLunch(arrayList);

                        macro1=page.getLunMacroTotal();
                        macro1.addMacro(food.getMacro(),entry.getQuantity());
                        page.setLunMacroTotal(macro1);
                        page.getDayMacroTotal().addMacro(food.getMacro(),entry.getQuantity());
                        break;
                    }
                    case DINNER -> {
                        arrayList=page.getDinner();
                        arrayList.add(entry);
                        page.setDinner(arrayList);

                        macro1=page.getDinMacroTotal();
                        macro1.addMacro(food.getMacro(),entry.getQuantity());
                        page.setDinMacroTotal(macro1);
                        page.getDayMacroTotal().addMacro(food.getMacro(),entry.getQuantity());
                        break;
                    }
                    default -> {
                        arrayList=page.getSnack();
                        arrayList.add(entry);
                        page.setSnack(arrayList);

                        macro1=page.getSnaMacroTotal();
                        macro1.addMacro(food.getMacro(),entry.getQuantity());
                        page.setSnaMacroTotal(macro1);
                        page.getDayMacroTotal().addMacro(food.getMacro(),entry.getQuantity());
                        break;
                    }
                }
            }
            /*
            water
            */
            rs.close();

            prps=con.prepareStatement("select * from \"water\" where \"day\"=?");
            prps.setDate(1,date);
            rs= prps.executeQuery();
            page.getWater().setDate(date);

            if(rs.next()){
                page.getWater().setQuantity(rs.getShort("quantity"));
            }
            else{
                page.getWater().setQuantity((short) 0);
            }
            rs.close();
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
