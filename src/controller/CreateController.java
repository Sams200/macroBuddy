package controller;

import main.ConfirmDialogInFrame;
import model.Entry;
import model.Food;
import model.MealType;
import model.Page;
import repo.EntryRepo;
import repo.FoodRepo;
import view.CreateView;
import view.EntryView;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Objects;

public class CreateController {
    /*
    the controller for creating a new food
     */

    private CreateView view;
    private Food food;
    private PageController pageController;

    public CreateController(PageController pageController) {
        this.pageController=pageController;
        this.food = new Food("Name",0, 100f,"g",100,0f,0f,0f,0, (short) 0,"User");
        this.view=new CreateView(this);
        update();
        this.view.setVisibility(true);
    }

    public void clickedX(){
        /*
        cancelled the food creation or closed the window
         */
        //ask the user if are you sure

        int input=JOptionPane.showConfirmDialog(null,
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
                "Are you sure you want to add this food?","",
                JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(input);

        if(input==0){
            //yes
            FoodRepo.create(food);
            view.closeView();
            view.disposeView();
            pageController.setDatePressed(false);
        }
    }

    public void update(){
        view.update(food,pageController.getTotals());
    }

    public void setServingUnit(){
        String s= JOptionPane.showInputDialog("Serving Units",
                this.food.getServing_units());

        try{
            if(s.length()<21){
                this.food.setServing_units(s);
                //EntryRepo.update(entry.getId(),entry);
                update();
            }else {
                JOptionPane.showMessageDialog(null,
                        "Length must be lower than 20","",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){
            return;
        }
    }

    public void setServingSize(){
        String s= JOptionPane.showInputDialog("Serving Size",this.food.getServing_size());

        float quantity;
        try {
            quantity=Float.parseFloat(s);
        }catch (Exception e){
            return;
        }

        if(quantity>0){
            this.food.setServing_size(quantity);
            //EntryRepo.update(entry.getId(),entry);
            update();
        }else {
            JOptionPane.showMessageDialog(null,
                    "Serving Size must be greater than 0","",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setMacro(){
        /*
        set the calories and the macros of the food
         */
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        JTextField kcal=new JTextField(food.getKcal()+"",5);
        JTextField protein=new JTextField(food.getProtein()+"",5);
        JTextField fat=new JTextField(food.getFat()+"",5);
        JTextField carbs=new JTextField(food.getCarbs()+"",5);

        c.gridx=0;
        c.gridy=0;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("KCal "),c);
        c.gridx=2;
        panel.add(kcal,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

        c.gridx=0;
        c.gridy=1;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("Protein "),c);
        c.gridx=2;
        panel.add(protein,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

        c.gridx=0;
        c.gridy=2;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("Fat "),c);
        c.gridx=2;
        panel.add(fat,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

        c.gridx=0;
        c.gridy=3;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("Carbs "),c);
        c.gridx=2;
        panel.add(carbs,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);




        int result = JOptionPane.showConfirmDialog(null, panel,
                "Enter Values", JOptionPane.OK_CANCEL_OPTION);

        int kcalv;
        float proteinv,fatv,carbsv;

        if(result==JOptionPane.OK_OPTION){
            try {
                kcalv=Integer.parseInt(kcal.getText());
                proteinv=Float.parseFloat(protein.getText());
                fatv=Float.parseFloat(fat.getText());
                carbsv=Float.parseFloat(carbs.getText());
            }catch (Exception e){
                return;
            }

            if(kcalv>=0 && proteinv>=0 && fatv>=0 && carbsv>=0){
                food.setMacro(kcalv,proteinv,fatv,carbsv);
                update();
            }else {
                JOptionPane.showMessageDialog(null,
                        "Invalid Values","",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setNameAndProducer(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        JTextField name=new JTextField(food.getName(),5);
        JTextField producer=new JTextField(food.getProducer(),5);

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

        c.gridx=0;
        c.gridy=1;
        c.weightx=0;
        panel.add(Box.createHorizontalStrut(20),c);
        c.gridx=1;
        panel.add(new JLabel("Producer "),c);
        c.gridx=2;
        panel.add(producer,c);
        c.gridx=3;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Enter Values", JOptionPane.OK_CANCEL_OPTION);
        String namev,producerv;

        if(result==JOptionPane.OK_OPTION){
            try {
                namev=name.getText();
                producerv=producer.getText();
            }catch (Exception e){
                return;
            }

            if(!namev.isEmpty() && !producerv.isEmpty()){
                food.setName(namev);
                food.setProducer(producerv);
                update();
            }else {
                JOptionPane.showMessageDialog(null,
                        "Invalid Values","",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
