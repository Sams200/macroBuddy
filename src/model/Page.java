package model;

import java.sql.*;
import java.util.ArrayList;

public class Page {
    /*
    a page is a collection of entries which are separated into multiple
    categories: breakfast, lunch, dinner, and snack

    the page also has the water and the current date

    the macro totals represent the sum of the macros for each individual
    meal
     */
    private ArrayList<Entry> breakfast;
    private ArrayList<Entry> lunch;
    private ArrayList<Entry> dinner;
    private ArrayList<Entry> snack;
    private Water water;
    private Macro breMacroTotal;
    private Macro lunMacroTotal;
    private Macro dinMacroTotal;
    private Macro snaMacroTotal;
    private Macro dayMacroTotal;
    private Date date;


    public Page(Date date){
        this.date=date;
        this.breakfast=new ArrayList<>();
        this.lunch=new ArrayList<>();
        this.dinner=new ArrayList<>();
        this.snack=new ArrayList<>();
        this.water=new Water();

        this.breMacroTotal=new Macro(0,0,0,0);
        this.lunMacroTotal=new Macro(0,0,0,0);
        this.dinMacroTotal=new Macro(0,0,0,0);
        this.snaMacroTotal=new Macro(0,0,0,0);this.breMacroTotal=new Macro(0,0,0,0);

        this.water=new Water();
    }

    public ArrayList<Entry> getBreakfast() {
        return breakfast;
    }

    public ArrayList<Entry> getLunch() {
        return lunch;
    }

    public ArrayList<Entry> getDinner() {
        return dinner;
    }

    public ArrayList<Entry> getSnack() {
        return snack;
    }

    public Water getWater() {
        return water;
    }

    public Macro getBreMacroTotal() {
        return breMacroTotal;
    }

    public Macro getLunMacroTotal() {
        return lunMacroTotal;
    }

    public Macro getDinMacroTotal() {
        return dinMacroTotal;
    }

    public Macro getSnaMacroTotal() {
        return snaMacroTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setBreakfast(ArrayList<Entry> breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(ArrayList<Entry> lunch) {
        this.lunch = lunch;
    }

    public void setDinner(ArrayList<Entry> dinner) {
        this.dinner = dinner;
    }

    public void setSnack(ArrayList<Entry> snack) {
        this.snack = snack;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public void setBreMacroTotal(Macro breMacroTotal) {
        this.breMacroTotal = breMacroTotal;
    }

    public void setLunMacroTotal(Macro lunMacroTotal) {
        this.lunMacroTotal = lunMacroTotal;
    }

    public void setDinMacroTotal(Macro dinMacroTotal) {
        this.dinMacroTotal = dinMacroTotal;
    }

    public void setSnaMacroTotal(Macro snaMacroTotal) {
        this.snaMacroTotal = snaMacroTotal;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Macro getDayMacroTotal() {
        return dayMacroTotal;
    }

    public void setDayMacroTotal(Macro dayMacroTotal) {
        this.dayMacroTotal = dayMacroTotal;
    }
}
