package Shape;

public class Circle {

    private double radius = 10;
    private double diameter = 4;

    //i don't actually use the diameter to calculate
    public double getPerimeter(double radius, double diameter){
        return 2 * 3.14 * radius;
    }
    public double getArea(double radius, double diameter){
        return 3.14 * (radius * radius);
    }
}
