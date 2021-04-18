package com.example.jayghodasara.myyyyyy;

/**
 * Created by Jay Ghodasara on 7/23/2017.
 */
public class food2 {
    private String ItemName;
    private String Quantity;
    private String Price;


    public food2()
    {

    }

    public food2(String name, String price, String quantity)
    {
        this.ItemName=name;
        this.Price=price;
       this.Quantity=quantity;

    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
