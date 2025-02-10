package controller;

import model.Entry;
import model.Food;
import model.MealType;
import repo.EntryRepo;
import repo.FoodRepo;
import view.AddView;

import java.sql.Date;

public class AddRecipeItemController extends AddController{

    /*
    this is similar to the add controller, in that you select
    a food from the database to add to the list, however now we arent
    creating a new entry, but rather just adding the food to an array.
    and also we will not be using recipes in this case since adding
    a recipe to another recipe can lead to weird recursion and complicated
    things
    keep it simple
     */
    private RecipeController recipeController;
    public AddRecipeItemController(RecipeController recipeController) {
        this.recipeController=recipeController;
        view=new AddView(this,false);
        view.setVisibility(true);
        foodList= FoodRepo.readAll();
        //recipeList=FoodRepo.readAllRecipe();

        view.addFoodList(foodList,true);
        //view.addFoodList(recipeList,false);
    }

    @Override
    public void clickedX(){
        /*
        cancelled the addition or closed the window
         */
        view.closeView();
        view.disposeView();
        recipeController.setButtonPressed(false);
    }
    @Override
    public void clickedFood(Food food){
        /*
        we choose to add this food
         */
        Entry entry=new Entry(0,date,food.getId(),1,food.getRecipeId(),mealType);
        //EntryRepo.create(entry);
        recipeController.addIngredient(entry);
        recipeController.update();
        clickedX();

    }
}
