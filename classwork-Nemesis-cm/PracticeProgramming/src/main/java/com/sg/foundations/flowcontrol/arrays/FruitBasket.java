package com.sg.foundations.flowcontrol.arrays;

public class FruitBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple",
                "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange",
                "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple",
                "Orange", "Orange", "Apple", "Apple", "Apple", "Banana", "Apple", "Orange",
                "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple",
                "Apple", "Apple", "Apple", "Orange", "Orange", "PawPaw", "Apple", "Orange",
                "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange",
                "Apple", "Orange", "Apple", "Kiwi", "Orange", "Apple", "Orange",
                "Dragonfruit", "Apple", "Orange", "Orange"};

        //VERSION 1

        String[] orangesArray = new String[33];
        String[] applesArray = new String[28];
        String[] otherArray = new String[4];

        int orangesCount = 0;
        int applesCount = 0;
        int otherCount= 0;

        for(String type : fruit){
            if (type.equals("Orange")){

                orangesArray[orangesCount] = type;
                orangesCount++;
            }
            if (type.equals("Apples")){
                applesArray[applesCount] = type;
                applesCount++;
            }
            if(type.equals("other")){
                otherArray[otherCount] = type;
                otherCount++;
            }

        }

        System.out.println("Number of oranges: " + orangesCount);
        System.out.println("Number of apples: " + applesCount);
        System.out.println("Number of other Fruits " + otherCount);

        System.out.println("Total# of Fruit in basket: " + fruit.length);
    }
}

