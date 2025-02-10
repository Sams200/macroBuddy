package model;

import repo.FoodRepo;

import java.sql.*;

public class Entry {
    /*
    A single food item/recipe entry in the page
    setters also update the database
     */
    private int id;
    private Date date;
    private int food_id;
    private float quantity;
    private short recipe_id;
    private MealType meal;
    private Food food;

    public Entry(int id, Date date, int food_id, float quantity, short recipe_id, MealType meal) {
        /*
        create a new entry with the given data
         */
        this.id = id;
        this.date = date;
        this.food_id = food_id;
        this.quantity = quantity;
        this.recipe_id = recipe_id;
        this.meal = meal;

        this.food=new Food();
        FoodRepo.read(this.food,this.food_id,this.recipe_id);
    }

    public Entry() {
        /*
        create a new entry with no data
         */
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public short getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(short recipe_id) {
        this.recipe_id = recipe_id;
    }

    public MealType getMeal() {
        return meal;
    }

    public void setMeal(MealType meal) {
        this.meal = meal;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
