package com.example.jayghodasara.myyyyyy;

/**
 * Created by Jay Ghodasara on 7/23/2017.
 */
public class food {
    private String name;
    private String image;
    private String price;


    public food()
    {

    }

    public food(String name, String price, String image)
    {
        this.name=name;
        this.price=price;
        this.image=image;

    }

    public String getPrice() {
        return price;
    }



    public String getImg() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImg(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
