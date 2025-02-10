package model;

public class Food extends Macro{
    /*
    the food class
    setters also update the table
     */
    private String name;
    private int producer_id;
    private String producer;
    private float serving_size;
    private String serving_units;
    private int id;
    private short recipeId;

    public Food(String name, int producer_id, float serving_size, String serving_units, int kcal, float protein, float fat, float carbs, int id, short recipeId, String producer) {
        /*
        create a new food with the given data
         */
        super(kcal, protein, fat, carbs);
        this.name = name;
        this.producer_id = producer_id;
        this.serving_size = serving_size;
        this.serving_units = serving_units;
        this.id = id;
        this.recipeId=recipeId;
        this.producer=producer;
    }

    public Food() {
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Macro getMacro(){
        return new Macro(this.kcal, this.protein, this.fat, this.carbs);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public float getServing_size() {
        return serving_size;
    }

    public void setServing_size(float serving_size) {
        this.serving_size = serving_size;
    }

    public String getServing_units() {
        return serving_units;
    }

    public void setServing_units(String serving_units) {
        this.serving_units = serving_units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(short recipeId) {
        this.recipeId = recipeId;
    }
}
