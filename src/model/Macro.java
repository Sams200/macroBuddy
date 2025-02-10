package model;

public class Macro {
    /*
    a macro is like a structure of kcal, protein, fat, and carbs
    since these values are often needed all four at once
     */

    protected int kcal;
    protected float protein;
    protected float fat;
    protected float carbs;

    public Macro(int kcal, float protein, float fat, float carbs) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public Macro(){
//        this.kcal=0;
//        this.protein=0;
//        this.fat=0;
//        this.carbs=0;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void addMacro(Macro macro, float multiplier){
        /*
        this.macro+=macro*multiplier
        add the given macro to this macro while also multiplying
        by multiplier
         */
        this.kcal+= (int) (macro.kcal*multiplier);
        this.protein+= macro.protein*multiplier;
        this.fat+= macro.fat*multiplier;
        this.carbs+=macro.carbs*multiplier;
    }

    public void setMacro(int kcal, float protein, float fat, float carbs){
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}
