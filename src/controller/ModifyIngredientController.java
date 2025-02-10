package controller;

import model.Entry;
import repo.EntryRepo;
import view.EntryView;

import javax.swing.*;

public class ModifyIngredientController extends EntryController{
    /*
    similar to EntryController, but this is just for modifying an ingredient
    in the recipe and doesn't change anything in the database
     */
    private RecipeController recipeController;
    public ModifyIngredientController(RecipeController recipeController, Entry entry){
        this.recipeController=recipeController;
        this.entry=entry;
        this.view=new EntryView(this);
        update();
        this.view.setVisibility(true);
    }

    @Override
    public void update(){
        view.update(entry,recipeController.getRecipe().getMacro());
    }

    @Override
    public void deleteEntry(){
        /*
        the method for deleting the entry
         */
        int input= JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this entry?","",
                JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        //System.out.println(input);

        if(input==0){
            //yes
            //EntryRepo.delete(this.entry.getId());
            recipeController.removeIngredient(entry);
            recipeController.update();
            clickedCheck();
        }else {
            //no
        }
    }

    @Override
    public void clickedCheck(){
        view.closeView();

        //pageController.updateMeal(mealType);
        //pageController.update();
        view.disposeView();
        recipeController.setButtonPressed(false);
    }

    @Override
    public void setQuantity(){
        String s= JOptionPane.showInputDialog("Quantity",this.entry.getQuantity());

        float quantity;
        try {
            quantity=Float.parseFloat(s);
        }catch (Exception e){
            return;
        }

        if(quantity>0){
            recipeController.removeIngredient(entry);
            this.entry.setQuantity(quantity);
            recipeController.addIngredient(entry);

            recipeController.update();
        }else {
            JOptionPane.showMessageDialog(null,
                    "Quantity must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }
}
