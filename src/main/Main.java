package main;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controller.PageController;
import model.*;
import repo.*;
import view.*;

import javax.crypto.Mac;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        /*
        Please read the readme.txt for a description
        about the project

        Main function
        setup flatlaf, the page controller and page view
         */

        FlatMacDarkLaf.setup();

//        File directory = new File("./resources/TomPlatz.png");
//        System.out.println(directory.getAbsolutePath());

        java.util.Date date1=new java.util.Date();
        Date date=new Date(date1.getTime());
        //date=Date.valueOf("2023-11-20"); ///CHANGE THIS WHEN URE DONE
        date= Date.valueOf(java.time.LocalDate.now());


        PageController pageController=new PageController(date);
        pageController.readConfig();
        PageView pageView=new PageView(pageController);

        pageController.setView(pageView);
        pageView.setVisibility(true);

        pageController.update();

    }

    public static void testWater(){

        String strDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        WaterRepo waterRepo=new WaterRepo();
        Water water;
        Date date=new Date(123,11,8);

        water=new Water(date,(short) 1000);

        waterRepo.delete(water.getDate());

    }

    public static void testPage(){

        String strDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(123,10,19);

        Page page=new Page(date);
        PageRepo.updatePage(page,date);


        ArrayList<Entry> breakfast=page.getBreakfast();
        Macro breMacro=page.getBreMacroTotal();

        ArrayList<Entry> lunch=page.getLunch();
        Macro lunMacro=page.getLunMacroTotal();

        ArrayList<Entry> dinner=page.getDinner();
        Macro dinMacro=page.getDinMacroTotal();

        ArrayList<Entry> snack=page.getSnack();
        Macro snaMacro=page.getSnaMacroTotal();

        for (Entry entry : breakfast) {
            System.out.println(entry.getId());
            System.out.println(entry.getFood().getKcal()*entry.getQuantity());
            System.out.println(entry.getFood().getProtein()*entry.getQuantity());
        }
        System.out.println(breMacro.getKcal());

        System.out.println("========================");
        for (Entry entry : lunch) {
            System.out.println(entry.getId());
            System.out.println(entry.getFood().getKcal()*entry.getQuantity());
            System.out.println(entry.getFood().getProtein()*entry.getQuantity());
        }
        System.out.println(lunMacro.getKcal());

        System.out.println("========================");
        for (Entry entry : dinner) {
            System.out.println(entry.getId());
            System.out.println(entry.getFood().getKcal()*entry.getQuantity());
            System.out.println(entry.getFood().getProtein()*entry.getQuantity());
        }
        System.out.println(dinMacro.getKcal());

        System.out.println("========================");
        for (Entry entry : snack) {
            System.out.println(entry.getId());
            System.out.println(entry.getFood().getKcal()*entry.getQuantity());
            System.out.println(entry.getFood().getProtein()*entry.getQuantity());
        }
        System.out.println(snaMacro.getKcal());
        System.out.println("========================");
        System.out.println(page.getDayMacroTotal().getKcal());

        System.out.println(page.getWater().getQuantity());

    }

    public static void testing(){
        String fontlist[];
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontlist= ge.getAvailableFontFamilyNames();
        for (String s : fontlist) System.out.println(s);
    }
}
