package com.example.dbm0204.assignment93;

/**
 * Created by dbm0204 on 6/10/17.
 */
public class Item {
    private String Name;
    private String Number;
    public Item(String name, String Number){
        super();
        this.Name=name;
        this.Number=Number;
    }

    public void setName(String name){
        this.Name=name;
    }
    public void setPhNumber(String Number){
        this.Number=Number;
    }
    public String getName(){
        return this.Name;
    }
    public String getNumber(){
        return this.Number;
    }
}
