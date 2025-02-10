package controller;

import view.DateView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class DateController {
    /*
    the controller for writing a date
     */
    DateView dateView=new DateView();
    PageController pageController;
    Date date;

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


    public DateController(PageController pageController, Date date){
        this.dateView.setVisibility(true);
        this.dateView.setDateController(this);
        this.pageController=pageController;
        this.date=date;
    }

    public void clickedCheck(String s){
        /*
        get the date from the textbox and switch to
        that date
         */
        try{
            java.util.Date date1;

            dateView.closeView();

            if(!Objects.equals(s, ""))
            {
                date1=sdf1.parse(s);
                this.date.setTime(date1.getTime());
                pageController.setDate(this.date);
                pageController.update();
            }

            dateView.disposeView();
            pageController.setDatePressed(false);

        } catch (ParseException e) {
            dateView.showMessage("Incorrect date!",0);
        }
    }

}
