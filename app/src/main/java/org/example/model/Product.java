package org.example.model;
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

        public Product(int id, String name, double price, int stock){
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    public int getId(){
            return id;
        }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }
    
    
    public double getPrice(){
        return price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return stock;
    }
}
