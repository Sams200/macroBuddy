package view;

import controller.CreateController;
import controller.RecipeController;
import main.Methods;
import model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.Math.min;

public class RecipeView {
    /*
    the view for creating a recipe
     */
    RecipeController controller;
    ImageIcon dorianY=Methods.createImageIcon(Resources.dorian,"SQUEEZE");
    Image dorian= dorianY.getImage();
    static Font totalsFont=new Font("Aptos Black",Font.BOLD,24);
    static Font mediumFont=new Font("Aptos Black",Font.BOLD,16);

    Color borderColor=new Color(57,57,57);

    JFrame frame=new JFrame();
    JPanel page=new JPanel();
    JPanel top=new JPanel();
    JPanel top2=new JPanel();
    JPanel mid=new JPanel();
    JScrollPane pane=new JScrollPane(page,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    JLabel editEntry=new JLabel("Create Food");
    JButton delete=new JButton("X");
    ImageIcon checkmarkImg= Methods.createImageIcon(Resources.checkmark,"a simple checkmark");
    JButton checkmark=new JButton(checkmarkImg);

    /////////////////////////////////
    JPanel namePanel=new JPanel();
    JLabel nameLabel=new JLabel();
    JPanel servings=new JPanel();
    JLabel servingLabel=new JLabel("Serving Quantity");
    JButton quantity=new JButton();
    //////////////////////////////////////

    //////////////////////////////////////
    JPanel sSize=new JPanel();
    JLabel sizeLabel=new JLabel("Serving Units");
    JButton measureLabel=new JButton();
    //////////////////////////////////////////////

    //////////////////////////////////////
    JPanel macroPanel=new JPanel();
    JLabel calories=new JLabel();
    JLabel caloriesLabel=new JLabel("KCal");
    JLabel protein=new JLabel();
    JLabel proteinLabel=new JLabel("Protein");
    JLabel fat=new JLabel();
    JLabel fatLabel=new JLabel("Fat");
    JLabel carbs=new JLabel();
    JLabel carbsLabel=new JLabel("Carbs");
    //////////////////////////////////////

    //////////////////////////////////////
    JPanel percentagePanel=new JPanel();
    JLabel percentageTitle=new JLabel("Percent of Your Daily Goals");

    JPanel caloriesPanel=new JPanel();
    JLabel percentageCaloriesLabel=new JLabel(" KCal");
    JProgressBar caloriesProgress=new JProgressBar(0,100);
    JLabel caloriesPercentage=new JLabel();
    JLabel caloriesTotal=new JLabel();

    JPanel proteinPanel=new JPanel();
    JLabel percentageProteinLabel=new JLabel(" Protein");
    JProgressBar proteinProgress=new JProgressBar(0,100);
    JLabel proteinPercentage=new JLabel();
    JLabel proteinTotal=new JLabel();

    JPanel fatPanel=new JPanel();
    JLabel percentageFatLabel=new JLabel(" Fat");
    JProgressBar fatProgress=new JProgressBar(0,100);
    JLabel fatPercentage=new JLabel();
    JLabel fatTotal=new JLabel();

    JPanel carbsPanel=new JPanel();
    JLabel percentageCarbsLabel=new JLabel(" Carbs");
    JProgressBar carbsProgress=new JProgressBar(0,100);
    JLabel carbsPercentage=new JLabel();
    JLabel carbsTotal=new JLabel();
    //////////////////////////////////////

    JPanel brePanel=new JPanel();
    JPanel breTitle=new JPanel();
    JLabel breLabel=new JLabel("Ingredients");
    JPanel breContent=new JPanel();
    JScrollPane brePane=new JScrollPane(breContent,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JPanel breBot=new JPanel();
    JButton breAddFood=new JButton("Add Food");

    public RecipeView(RecipeController controller){
        this.controller=controller;
        frame.setIconImage(dorian);

        //frame.setLocationRelativeTo(null);
        //frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(750, 675);
        frame.setMinimumSize(new Dimension(750,675));
        frame.setMaximumSize(new Dimension(750,675));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.clickedX();
            }
        });
        frame.setContentPane(pane);

        page.setLayout(new BorderLayout());
        page.add(top,BorderLayout.PAGE_START);
        page.add(mid,BorderLayout.CENTER);

        //top
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        top.add(Box.createVerticalStrut(15));
        top.add(top2);
        top.add(Box.createVerticalStrut(15));
        top.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor,3,true),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));

        top2.setLayout(new BoxLayout(top2,BoxLayout.LINE_AXIS));
        top2.add(Box.createHorizontalStrut(20));
        top2.add(delete);
        top2.add(Box.createHorizontalStrut(20));
        editEntry.setFont(totalsFont);
        top2.add(editEntry);
        top2.add(Box.createHorizontalGlue());
        top2.add(checkmark);
        top2.add(Box.createHorizontalStrut(20));

        delete.addActionListener(e -> {
            controller.clickedX();
        });
        checkmark.addActionListener(e -> {
            controller.clickedCheck();
        });
        ////////////////////////////////////

        mouseAdapterGenerator(namePanel,1);
        mouseAdapterGenerator(servings,2);
        mouseAdapterGenerator(sSize,3);
        mouseAdapterGenerator(macroPanel,4);
        mouseAdapterGenerator(percentagePanel,5);

        mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
        mid.add(Box.createVerticalStrut(30));
        mid.add(namePanel);
        mid.add(Box.createVerticalStrut(30));
        mid.add(servings);
        mid.add(sSize);
        mid.add(Box.createVerticalStrut(30));
        mid.add(macroPanel);
        mid.add(percentagePanel);
        mid.add(Box.createVerticalGlue());

        namePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        servings.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        sSize.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        macroPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        percentagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        Dimension panelSize=new Dimension(600,75);
        namePanel.setMinimumSize(panelSize);
        namePanel.setPreferredSize(panelSize);
        namePanel.setMaximumSize(panelSize);

        servings.setMinimumSize(panelSize);
        servings.setPreferredSize(panelSize);
        servings.setMaximumSize(panelSize);

        sSize.setMinimumSize(panelSize);
        sSize.setPreferredSize(panelSize);
        sSize.setMaximumSize(panelSize);

        macroPanel.setMinimumSize(panelSize);
        macroPanel.setPreferredSize(panelSize);
        macroPanel.setMaximumSize(panelSize);

        Dimension percentageSize=new Dimension(650,100);
        percentagePanel.setMinimumSize(percentageSize);
        percentagePanel.setPreferredSize(percentageSize);
        percentagePanel.setMaximumSize(percentageSize);

        Dimension buttonSize=new Dimension(75,50);

        //namePanel
        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
        namePanel.add(Box.createHorizontalStrut(20));
        nameLabel.setFont(totalsFont);
        namePanel.add(nameLabel);
        namePanel.add(Box.createHorizontalGlue());


        ////////////////////////////////

        //servings
        servings.setLayout(new BoxLayout(servings,BoxLayout.X_AXIS));
        servings.add(Box.createHorizontalStrut(20));
        servingLabel.setFont(mediumFont);
        servings.add(servingLabel);
        servings.add(Box.createHorizontalGlue());
        quantity.setFont(mediumFont);
        servings.add(quantity);
        servings.add(Box.createHorizontalStrut(20));

        quantity.addActionListener(e -> {
            //controller.setServingSize();
            System.out.println(1);
        });
        ////////////////////////////////////////

        //serving size
        sSize.setLayout(new BoxLayout(sSize,BoxLayout.X_AXIS));
        sSize.add(Box.createHorizontalStrut(20));
        sizeLabel.setFont(mediumFont);
        sSize.add(sizeLabel);
        sSize.add(Box.createHorizontalGlue());
        measureLabel.setFont(mediumFont);
        //measureLabel.setPreferredSize(buttonSize);
        sSize.add(measureLabel);
        sSize.add(Box.createHorizontalStrut(20));

        measureLabel.addActionListener(e -> {
            //controller.setServingUnit();
            System.out.println(2);
        });
        ////////////////////////////////////////

        macroPanel.setLayout(new GridLayout(2,4));
        macroPanel.add(calories);
        calories.setHorizontalAlignment(0);
        calories.setFont(totalsFont);

        macroPanel.add(protein);
        protein.setHorizontalAlignment(0);
        protein.setFont(mediumFont);

        macroPanel.add(fat);
        fat.setHorizontalAlignment(0);
        fat.setFont(mediumFont);

        macroPanel.add(carbs);
        carbs.setFont(mediumFont);
        carbs.setHorizontalAlignment(0);

        macroPanel.add(caloriesLabel);
        caloriesLabel.setFont(totalsFont);
        caloriesLabel.setHorizontalAlignment(0);

        macroPanel.add(proteinLabel);
        proteinLabel.setFont(mediumFont);
        proteinLabel.setHorizontalAlignment(0);

        macroPanel.add(fatLabel);
        fatLabel.setFont(mediumFont);
        fatLabel.setHorizontalAlignment(0);

        macroPanel.add(carbsLabel);
        carbsLabel.setFont(mediumFont);
        carbsLabel.setHorizontalAlignment(0);
        ///////////////////////////////////////////////////////

        percentagePanel.setLayout(new BoxLayout(percentagePanel,BoxLayout.Y_AXIS));

        JPanel first=new JPanel();
        first.setLayout(new BoxLayout(first,BoxLayout.X_AXIS));
        JPanel second=new JPanel();
        second.setLayout(new BoxLayout(second,BoxLayout.X_AXIS));
        percentagePanel.add(Box.createVerticalStrut(10));
        percentagePanel.add(first);
        percentagePanel.add(second);
        percentagePanel.add(Box.createVerticalGlue());

        percentageTitle.setFont(mediumFont);
        first.add(Box.createHorizontalStrut(20));
        first.add(percentageTitle);
        first.add(Box.createHorizontalGlue());


        second.add(Box.createHorizontalStrut(20));
        caloriesPanel=createMacroPanel(percentageCaloriesLabel,caloriesProgress,
                caloriesPercentage,caloriesTotal);
        second.add(caloriesPanel);

        proteinPanel=createMacroPanel(percentageProteinLabel,proteinProgress,
                proteinPercentage,proteinTotal);
        second.add(proteinPanel);

        fatPanel=createMacroPanel(percentageFatLabel,fatProgress,
                fatPercentage,fatTotal);
        second.add(fatPanel);

        carbsPanel=createMacroPanel(percentageCarbsLabel,carbsProgress,
                carbsPercentage,carbsTotal);
        second.add(carbsPanel);
        second.add(Box.createHorizontalGlue());

        mealPanel(brePanel, breTitle, breLabel, breContent, brePane, breBot, breAddFood);
        mid.add(brePanel);
        breAddFood.addActionListener(e -> {
            controller.addFood();
            //System.out.println(111);
        });

    }

    private JPanel createMacroPanel(JLabel label, JProgressBar progress, JLabel percentage, JLabel total){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        c.gridx=0;
        c.gridy=0;
        c.weightx=0;
        c.gridwidth=1;
        panel.add(label,c);
        c.gridx=1;
        c.gridwidth=1;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);

        c.gridx=0;
        c.gridy=1;
        c.weightx=0;
        c.gridwidth=4;
        panel.add(progress,c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth=1;
        panel.add(percentage,c);
        c.gridx=1;
        c.weightx=1;
        panel.add(Box.createHorizontalGlue(),c);
        c.gridx=3;
        c.weightx=0;
        c.gridwidth=1;
        panel.add(total,c);

        c.gridx=4;
        c.weightx=1;
        panel.add(Box.createHorizontalStrut(5),c);

        //panel.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
        return panel;
    }

    public static void mealPanel(JPanel brePanel, JPanel breTitle, JLabel breLabel, JPanel breContent,
                                 JScrollPane brePane, JPanel breBot, JButton breAddFood) {
        brePanel.setLayout(new BorderLayout());
        brePanel.setBorder(BorderFactory.createLineBorder(
                new Color(57,57,57),3,true));
        brePanel.setMaximumSize(new Dimension(650,400));
        brePanel.setMinimumSize(new Dimension(650,400));

        breTitle.setLayout(new GridBagLayout());
        breTitle.setMinimumSize(new Dimension(650,100));
        breTitle.setPreferredSize(new Dimension(650,100));
        breTitle.setMaximumSize(new Dimension(650,100));

        GridBagConstraints breC=new GridBagConstraints();
        breTitle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        brePanel.add(breTitle,BorderLayout.PAGE_START);

        breC.fill=GridBagConstraints.HORIZONTAL;

        breC.gridx=0;
        breC.gridy=0;
        breC.gridwidth=1;
        breTitle.add(Box.createRigidArea(new Dimension(20,1)),breC);

        breC.gridx=1;
        breC.gridy=0;
        breC.gridwidth=6;
        breC.weightx=1;
        breTitle.add(breLabel,breC);
        breLabel.setFont(totalsFont);

        breTitle.add(Box.createRigidArea(new Dimension(20,1)),breC);

        breContent.setLayout(new BoxLayout(breContent,BoxLayout.Y_AXIS));
        brePane.setBorder(BorderFactory.createEmptyBorder());
        //brePane.setMaximumSize(new Dimension(650,200));

        breBot.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        breBot.setMaximumSize(new Dimension(650,50));
        breBot.setLayout(new FlowLayout(FlowLayout.LEFT));
        breBot.add(Box.createRigidArea(new Dimension(10,0)));
        breAddFood.setAlignmentX(0.0f);
        breBot.add(breAddFood);


        //brePanel.add(Box.createRigidArea(new Dimension(100,10)),BorderLayout.LINE_START);
        brePanel.add(brePane,BorderLayout.CENTER);
        brePanel.add(breBot,BorderLayout.PAGE_END);
    }

    public void setBreFood(ArrayList<Entry> breakfast){
        breContent.removeAll();
        if(breakfast.isEmpty()) {
            breContent.setMaximumSize(new Dimension(650,0));
            brePane.setMaximumSize(new Dimension(650,0));
            brePane.setMinimumSize(new Dimension(650,0));

            brePanel.remove(brePane);
            brePanel.setMaximumSize(new Dimension(650, breTitle.getSize().height
                    +breBot.getSize().height));
            return;
        }
        brePanel.add(brePane,BorderLayout.CENTER);

        breContent.setMaximumSize(new Dimension(650,50*breakfast.size()));
        brePane.setMaximumSize(new Dimension(650,min(250,50*breakfast.size())));
        brePane.setMinimumSize(new Dimension(650,min(250,50*breakfast.size())));
        brePane.setPreferredSize(new Dimension(650,min(250,50*breakfast.size())));
        brePanel.setMaximumSize(new Dimension(650, min(400,breTitle.getSize().height
                +50*breakfast.size()+breBot.getSize().height)));
        for(Entry entry : breakfast){

            JPanel item=addFoodPanelButton(entry);
            breContent.add(item);

            //brePane.setPreferredSize(new Dimension(650,50*breakfast.size()+10));
        }
    }

    public void showMessage(String message,int option){

        switch (option){
            case 0:
                JOptionPane.showMessageDialog(frame,message,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                break;
        }
    }

    public JPanel addFoodPanelButton(Entry entry){
        Border raised=BorderFactory.createBevelBorder(0);
        Border lowered=BorderFactory.createBevelBorder(1);

        JPanel jButton=new JPanel();
        jButton.setAlignmentX(0.2f);
        jButton.setBorder(raised);

        MouseAdapter mouseAdapter=new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jButton.setBorder(lowered);
                //super.mouseClicked(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //System.out.println(mealType.getMeal());
                //controller.entryView(entry,mealType);
                controller.modifyIngredient(entry);
                jButton.setBorder(raised);
                //super.mouseReleased(e);
            }
        };



        JLabel name=new JLabel(entry.getFood().getName());
        JLabel calories=new JLabel(String.valueOf(
                (int)(entry.getFood().getKcal()*entry.getQuantity())));
        JLabel below=new JLabel(entry.getFood().getProducer()+", "+
                entry.getQuantity()*entry.getFood().getServing_size()+" "+
                entry.getFood().getServing_units());

        jButton.addMouseListener(mouseAdapter);
//        name.addMouseListener(mouseAdapter);
//        calories.addMouseListener(mouseAdapter);
//        below.addMouseListener(mouseAdapter);

//        jButton.add(name);
//        jButton.add(below);
//        jButton.add(Box.createHorizontalStrut(400));
//        jButton.add(calories);

        jButton.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();


        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=0;
        c.gridx=0;
        c.gridy=0;
        jButton.add(Box.createHorizontalStrut(30),c);
        c.gridx=1;
        jButton.add(name,c);
        c.gridx=2;
        c.weightx=1;
        jButton.add(Box.createHorizontalStrut(10),c);
        c.gridx=3;
        c.weightx=0;
        jButton.add(calories,c);
        c.gridx=4;
        jButton.add(Box.createHorizontalStrut(30),c);

        c.gridx=1;
        c.gridy=1;
        jButton.add(below,c);
        c.gridx=2;
        c.weightx=1;
        jButton.add(Box.createVerticalStrut(10),c);


        jButton.setPreferredSize(new Dimension(630,50));
        jButton.setMaximumSize(new Dimension(630,50));

        return jButton;
    }


    public void update(Food food, Macro totals){
        nameLabel.setText(food.getName()+" ("+
                food.getProducer()+")");

        if(food.getServing_size()==(int)(food.getServing_size()))
            quantity.setText("  "+(int)(food.getServing_size())+"  ");
        else
            quantity.setText(String.format("%.1f", food.getServing_size()));


        measureLabel.setText("  "+food.getServing_units()+"  ");

        calories.setText((int)(food.getKcal())+"");
        protein.setText((int)(food.getProtein())+"g");
        fat.setText((int)(food.getFat())+"g");
        carbs.setText((int)(food.getCarbs())+"g");

        caloriesProgress.setValue((int) ((food.getKcal())*100/
                totals.getKcal()));
        caloriesPercentage.setText(caloriesProgress.getValue()+"%");
        caloriesTotal.setText(totals.getKcal()+"");

        proteinProgress.setValue((int) ((food.getProtein())*100/
                totals.getProtein()));
        proteinPercentage.setText(proteinProgress.getValue()+"%");
        proteinTotal.setText(totals.getProtein()+"");

        fatProgress.setValue((int) ((food.getFat())*100/
                totals.getFat()));
        fatPercentage.setText(fatProgress.getValue()+"%");
        fatTotal.setText(totals.getFat()+"");

        carbsProgress.setValue((int) ((food.getCarbs())*100/
                totals.getCarbs()));
        carbsPercentage.setText(carbsProgress.getValue()+"%");
        carbsTotal.setText(totals.getCarbs()+"");

        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
    public void setVisibility(boolean isVisible){
        frame.setVisible(isVisible);
    }

    public void closeView(){
        frame.setVisible(false);
        //frame.dispose();
    }

    public void disposeView(){
        //frame.setVisible(false);
        frame.dispose();
    }

    public void mouseAdapterGenerator(JPanel panel, int type){
        Border raised=BorderFactory.createBevelBorder(0);
        Border lowered=BorderFactory.createBevelBorder(1);

        MouseAdapter mouseAdapter=new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBorder(lowered);
                //super.mouseClicked(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setBorder(raised);
                //super.mouseReleased(e);
                switch (type){
                    case 1:
                        controller.setNameAndProducer();
                        break;
                    case 2:
                        //controller.setServingSize();
                        break;
                    case 3:
                        //controller.setServingUnit();
                        break;
                    case 4:
                        //controller.setMacro();
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        };

        panel.addMouseListener(mouseAdapter);
    }

}