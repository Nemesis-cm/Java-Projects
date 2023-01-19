package Shape;

public class Square extends Shape{
    double height = 5;
    double width = 5;

    @Override
    public double getArea(double height, double width){
        return height * width;
    }


    @Override
    public double getPerimeter(double height, double width){
        return (height + width) * 2;
    }

}