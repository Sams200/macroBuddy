package controller;

import model.Entry;
import model.Food;
import model.MealType;
import repo.EntryRepo;
import repo.FoodRepo;
import view.AddView;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddController {
    /*
    the controller for adding a new food
    The mealtype represents the meal to which we add the food
    foodlist represents the options available to add from
     */
    protected MealType mealType;
    protected AddView view;
    protected PageController pageController;
    protected ArrayList<Food> foodList;
    protected ArrayList<Food> recipeList;
    protected Date date;

    public AddController(){

    }
    public AddController(PageController pageController, MealType mealType, Date date){
        this.pageController=pageController;
        this.date=date;
        this.mealType=mealType;
        view=new AddView(this,true);
        view.setVisibility(true);
        foodList= FoodRepo.readAll();
        recipeList=FoodRepo.readAllRecipe();

        view.addFoodList(foodList,true);
        view.addFoodList(recipeList,false);
    }

    public void clickedX(){
        /*
        cancelled the addition or closed the window
         */
        view.closeView();
        view.disposeView();
        pageController.setDatePressed(false);
    }

    public void searchFood(JTextField searchBar)
    {
        /*
        search the food with the name on the searchbar
         */
        String s=searchBar.getText();
        foodList=FoodRepo.getFoodByName(s);

        view.addFoodList(foodList,true);
    }

    public void searchRecipe(JTextField searchBar)
    {
        /*
        search the recipe with the name on the searchbar
         */
        System.out.println(2222);
        String s=searchBar.getText();
        //System.out.println(s);
        recipeList=FoodRepo.readRecipeName(s);

        view.addFoodList(recipeList,false);
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void clickedFood(Food food){
        /*
        add this food as an entry to current date
         */
        Entry entry=new Entry(0,date,food.getId(),1,food.getRecipeId(),mealType);
        EntryRepo.create(entry);
        clickedX();
        pageController.entryView(entry,mealType);
        pageController.update();
    }
}
