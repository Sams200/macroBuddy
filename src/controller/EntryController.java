package controller;

import main.ConfirmDialogInFrame;
import model.Entry;
import model.MealType;
import model.Page;
import repo.EntryRepo;
import view.EntryView;

import javax.swing.*;
import java.text.ParseException;
import java.util.Objects;

public class EntryController {
    /*
    the controller for modifying an entry
     */

    protected EntryView view;
    protected Entry entry;
    protected Entry entryCopy=new Entry();
    protected PageController pageController;
    protected MealType mealType;

    public EntryController(){

    }
    public EntryController(PageController pageController,Entry entry,MealType mealType) {
        this.pageController=pageController;
        this.entry = entry;
        this.mealType=mealType;
        this.view=new EntryView(this);
        update();
        this.view.setVisibility(true);

        //make a copy of the initial entry in case we need to revert
        this.entryCopy=new Entry(entry.getId(),entry.getDate(),entry.getFood_id(),
                entry.getQuantity(),entry.getRecipe_id(),entry.getMeal());
        entryCopy.setFood(entry.getFood());
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public void revertEntry(){
        this.entry=this.entryCopy;
    }
    public void clickedCheck(){
        /*
        close the tab
         */
            view.closeView();

            //pageController.updateMeal(mealType);
            //pageController.update();
            view.disposeView();
            pageController.setDatePressed(false);
    }

    public void update(){
        view.update(entry,pageController.getTotals());
    }

    public void deleteEntry(){
        /*
        the method for deleting the entry
         */
        int input=JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this entry?","",
                JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        //System.out.println(input);

        if(input==0){
            //yes
            EntryRepo.delete(this.entry.getId());
            pageController.update();
            clickedCheck();
        }else {
            //no
        }
    }

    public void setQuantity(){
        String s= JOptionPane.showInputDialog("Quantity",this.entry.getQuantity());

        float quantity;
        try {
            quantity=Float.parseFloat(s);
        }catch (Exception e){
            return;
        }

        if(quantity>0){
            this.entry.setQuantity(quantity);
            EntryRepo.update(entry.getId(),entry);
            pageController.update();
        }else {
            JOptionPane.showMessageDialog(null,
                    "Quantity must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }
}
