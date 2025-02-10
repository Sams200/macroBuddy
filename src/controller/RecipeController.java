package controller;

import model.Entry;
import model.Food;
import model.Macro;
import model.MealType;
import repo.FoodRepo;
import view.CreateView;
import view.RecipeView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecipeController {
    /*
    the controller for creating a new recipe
     */
    private RecipeView view;
    private Food recipe;
    private ArrayList<Entry> ingredients;
    //private Macro totals;
    private PageController pageController;
    private boolean buttonPressed;

    public RecipeController(PageController pageController){
        this.pageController=pageController;
        this.recipe = new Food("Name",0, 1f,"Serving",0,0f,0f,0f,0, (short) 0,"User");
        this.ingredients=new ArrayList<>();
        //this.totals=new Macro(0,0,0,0);
        this.view=new RecipeView(this);
        update();
        this.view.setVisibility(true);
    }

    public void clickedX(){
        /*
        cancelled the food creation or closed the window
         */
        //ask the user if are you sure

        int input= JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?","",
                JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        //System.out.println(input);

        if(input==0){
            //yes
            view.closeView();
            view.disposeView();
            pageController.setDatePressed(false);
        }
    }

    public void clickedCheck(){
        /*
        finalized the food creation
         */
        //add an entry with this food in 1970

        int input=JOptionPane.showConfirmDialog(null,
                "Are you sure you want to add this recipe?","",
                JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(input);

        if(ingredients.size()<2){
            view.showMessage("Recipe must have at least 2 ingredients",0);
            return;
        }

        if(input==0){
            //yes
            //FoodRepo.create(food);
            FoodRepo.createRecipe(recipe,ingredients);
            view.closeView();
            view.disposeView();
            pageController.setDatePressed(false);
        }
    }

    public void update(){
        view.setBreFood(ingredients);
        view.update(recipe,pageController.getTotals());
    }

    public void addIngredient(Entry entry){
        ingredients.add(entry);
        Macro macro=recipe.getMacro();
        macro.addMacro(entry.getFood().getMacro(),entry.getQuantity());
        recipe.setMacro(macro.getKcal(), macro.getProtein(),
                macro.getFat(),macro.getCarbs());
        //recipe.setMacro(macro);
        //update();
        //modifyIngredient(entry);
    }

    public void removeIngredient(Entry entry){
        ingredients.remove(entry);
        Macro macro=recipe.getMacro();
        macro.addMacro(entry.getFood().getMacro(),-1*entry.getQuantity());
        recipe.setMacro(macro.getKcal(), macro.getProtein(),
                macro.getFat(),macro.getCarbs());
        //update();
    }

    public void setNameAndProducer(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        JTextField name=new JTextField(recipe.getName(),5);
//        JTextField producer=new JTextField(recipe.getProducer(),5);

        c.gridx=0;
        c.gridy=0;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("Name "),c);
        c.gridx=2;
        panel.add(name,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

//        c.gridx=0;
//        c.gridy=1;
//        c.weightx=0;
//        panel.add(Box.createHorizontalStrut(20),c);
//        c.gridx=1;
//        panel.add(new JLabel("Producer "),c);
//        c.gridx=2;
//        panel.add(producer,c);
//        c.gridx=3;
//        c.weightx=1;
//        panel.add(Box.createHorizontalGlue(),c);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Enter Value", JOptionPane.OK_CANCEL_OPTION);
        String namev,producerv;

        if(result==JOptionPane.OK_OPTION){
            try {
                namev=name.getText();
                //producerv=producer.getText();
            }catch (Exception e){
                return;
            }

            if(!namev.isEmpty()){
                recipe.setName(namev);
                update();
            }else {
                JOptionPane.showMessageDialog(null,
                        "Invalid Value","",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void addFood(){
        /*
        add a new food to the given meal
         */
        //System.out.println(mealType);
        if(buttonPressed)
            return;

        buttonPressed=true;
        AddRecipeItemController addRecipeItemController=new AddRecipeItemController(this);
    }

    public void modifyIngredient(Entry entry){
        /*
        add a new food to the given meal
         */
        //System.out.println(mealType);
        if(buttonPressed)
            return;

        buttonPressed=true;
        ModifyIngredientController modifyIngredientController=new ModifyIngredientController(this,entry);
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public Food getRecipe() {
        return recipe;
    }
}
