package controller;

import model.*;
import repo.EntryRepo;
import repo.PageRepo;
import repo.WaterRepo;
import view.PageView;

import javax.swing.*;
import java.io.*;
import java.sql.Date;
import java.util.StringTokenizer;

public class PageController {
    /*
    the controller for the main page of the application
     */
    private Page page;
    private PageView view;
    private Date date;
    private boolean buttonPressed;

    private Macro totals=new Macro();
    private boolean initialize;

    public PageController(Date date){
        this.page=new Page(date);
        PageRepo.updatePage(page,date);
        this.date=date;
        this.buttonPressed=false;

        readConfig();

    }

    public Macro getTotals() {
        return totals;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public PageView getView() {
        return view;
    }

    public void setView(PageView view) {
        this.view = view;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDatePressed() {
        return buttonPressed;
    }

    public void setDatePressed(boolean datePressed) {
        this.buttonPressed = datePressed;
    }

    public void nextDate(){
        //add a day to the date
        this.date.setTime(this.date.getTime()+1000*60*60*24);
        update();
    }

    public void prevDate(){
        //subtract a day from the date
        this.date.setTime(this.date.getTime()-1000*60*60*24);
        update();
    }

    public void readConfig(){
        //read the config with the app settings
        //if there is an error restore to the default settings
        try {
            BufferedReader reader=new BufferedReader(
                    new FileReader("./config/config.txt."));

            String s=reader.readLine(); //initialize
            StringTokenizer strtok=new StringTokenizer(s," ");
            strtok.nextToken();

            if(Integer.parseInt(strtok.nextToken())==1) {
                this.initialize=true;
            }else {
                this.initialize=false;
            }

            s=reader.readLine();
            strtok=new StringTokenizer(s," ");
            strtok.nextToken();
            this.totals.setKcal(Integer.parseInt(strtok.nextToken()));

            s=reader.readLine();
            strtok=new StringTokenizer(s," ");
            strtok.nextToken();
            this.totals.setProtein(Float.parseFloat(strtok.nextToken()));

            s=reader.readLine();
            strtok=new StringTokenizer(s," ");
            strtok.nextToken();
            this.totals.setFat(Float.parseFloat(strtok.nextToken()));

            s=reader.readLine();
            strtok=new StringTokenizer(s," ");
            strtok.nextToken();
            this.totals.setCarbs(Float.parseFloat(strtok.nextToken()));



            reader.close();
        } catch (IOException e) {
            writeConfig(false, new Macro(2000,100,50,100));
        }
    }
    public void writeConfig(boolean initialize, Macro macro){
        /*
        write to the config with the given settings
         */
        File file=new File("./config/config.txt.");
        int value;
        if(initialize)
            value=1;
        else
            value=0;

        try {
            FileWriter writer=new FileWriter(file);
            writer.write("initialize: "+value+"\n" +
                    "kcalTotal: "+macro.getKcal()+"\n" +
                    "proteinTotal: "+macro.getProtein()+"\n" +
                    "fatTotal: "+macro.getFat()+"\n" +
                    "carbTotal: "+macro.getCarbs());

            this.totals=macro;
            this.initialize=initialize;

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        /*
        update all the values and items in the page
         */
        PageRepo.updatePage(page,date);
        view.setDateCurr(page.getDate());

        ////day totals
        view.setKcalDay(page.getDayMacroTotal().getKcal(),this.totals.getKcal());
        view.setProteinDay(page.getDayMacroTotal().getProtein(),
                totals.getProtein());
        view.setFatDay(page.getDayMacroTotal().getFat(),
                totals.getFat());
        view.setCarbsDay(page.getDayMacroTotal().getCarbs(),
                totals.getCarbs());
        /////////////////////////////////////////

        ///breakfast
        view.setBreTotals(page.getBreMacroTotal());
        view.setBreFood(page.getBreakfast());
        /////////////////////////////////////////
        //lunch
        view.setLunTotals(page.getLunMacroTotal());
        view.setLunFood(page.getLunch());
        /////////////////////////////////////////
        //dinner
        view.setDinTotals(page.getDinMacroTotal());
        view.setDinFood(page.getDinner());
        /////////////////////////////////////////
        //snacks
        view.setSnaTotals(page.getSnaMacroTotal());
        view.setSnaFood(page.getSnack());
        /////////////////////////////////////////
        ///water
        view.setWatTotal(page.getWater());
        /////////////////////////////////////////
    }

    public void updateMeal(MealType mealType){
        /*
        update the values only for the specific meal, and
        update the totals accordingly
         */
        ////day totals
        view.setKcalDay(page.getDayMacroTotal().getKcal(),this.totals.getKcal());
        view.setProteinDay(page.getDayMacroTotal().getProtein(),
                totals.getProtein());
        view.setFatDay(page.getDayMacroTotal().getFat(),
                totals.getFat());
        view.setCarbsDay(page.getDayMacroTotal().getCarbs(),
                totals.getCarbs());
        /////////////////////////////////////////

        switch (mealType){
            case BREAKFAST -> {
                view.setBreTotals(page.getBreMacroTotal());
                view.setBreFood(page.getBreakfast());
                break;
            }
            case LUNCH -> {
                view.setLunTotals(page.getLunMacroTotal());
                view.setLunFood(page.getLunch());
                break;
            }
            case DINNER -> {
                view.setDinTotals(page.getDinMacroTotal());
                view.setDinFood(page.getDinner());
                break;
            }
            case SNACK -> {
                view.setSnaTotals(page.getSnaMacroTotal());
                view.setSnaFood(page.getSnack());
                break;
            }
            default -> {
                view.setWatTotal(page.getWater());
                break;
            }
        }
    }

    public void addFood(MealType mealType){
        /*
        add a new food to the given meal
         */
        //System.out.println(mealType);
        if(buttonPressed)
            return;

        buttonPressed=true;
        AddController addController=new AddController(this,mealType,date);
    }

    public void setDayKcal(){
        String s=JOptionPane.showInputDialog("Total Daily Calories",
                this.totals.getKcal());

        int kcal;
        try {
            kcal=Integer.parseInt(s);
        }catch (Exception e){
            return;
        }

        if(kcal>0){
            this.totals.setKcal(kcal);
            writeConfig(initialize,totals);
        } else{
            JOptionPane.showMessageDialog(null,"Calories must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDayProtein(){
        String s=JOptionPane.showInputDialog("Total Daily Protein",
                (int)(this.totals.getProtein()));

        float protein;
        try {
            protein=Integer.parseInt(s);
        }catch (Exception e){
            return;
        }

        if(protein>0){
            this.totals.setProtein(protein);
            writeConfig(initialize,totals);
        } else{
            JOptionPane.showMessageDialog(null,"Value must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDayFat(){
        String s=JOptionPane.showInputDialog("Total Daily Fat",
                (int)(this.totals.getFat()));

        float fat;
        try {
            fat=Integer.parseInt(s);
        }catch (Exception e){
            return;
        }

        if(fat>0){
            this.totals.setFat(fat);
            writeConfig(initialize,totals);
        } else{
            JOptionPane.showMessageDialog(null,"Value must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDayCarbs(){
        String s=JOptionPane.showInputDialog("Total Daily Carbs",
                (int)(this.totals.getCarbs()));

        float carbs;
        try {
            carbs=Integer.parseInt(s);
        }catch (Exception e){
            return;
        }

        if(carbs>0){
            this.totals.setCarbs(carbs);
            writeConfig(initialize,totals);
        } else{
            JOptionPane.showMessageDialog(null,"Value must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dateView(){
        /*
        open the frame with the date view
         */
        if(buttonPressed)
            return;

        buttonPressed=true;
        DateController dateController=new DateController(this,this.date);
    }

    public void entryView(Entry entry,MealType mealType){
        /*
        modify the given entry
         */
        if(buttonPressed)
            return;

        buttonPressed=true;
        EntryController entryController=new EntryController(this,entry,mealType);
    }

    public void createFoodView(){
        /*
        create a new food item
         */
        if(buttonPressed)
            return;

        buttonPressed=true;
        CreateController createController=new CreateController(this);
    }

    public void createRecipeView(){
        /*
        create a new recipe item
         */
        if(buttonPressed)
            return;

        buttonPressed=true;
        RecipeController recipeController=new RecipeController(this);
    }

    public void setWater(){
        /*
        modify the water amount for the page
         */
        String s= JOptionPane.showInputDialog("Water Amount",this.page.getWater().getQuantity());

        short quantity;
        try {
            quantity=Short.parseShort(s);
        }catch (Exception e){
            return;
        }

        Water water=page.getWater();
        water.setQuantity(quantity);
        if(quantity>0){
            this.page.setWater(water);
            if(!WaterRepo.update(water.getDate(),water))
                WaterRepo.create(water);
        }else {
            JOptionPane.showMessageDialog(null,
                    "Quantity must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

}
