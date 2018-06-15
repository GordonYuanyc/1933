import java.awt.*;
import java.awt.geom.Area;

public class Circle{
    private double xposit = 0;
    private double yposit = 0;
    private double radius = 0;
    double perimeter;
    double area;
    Color c = Color.BLUE;

    public Circle(double a, double b, double c){
        xposit = a;
        yposit = b;
        radius = c;
    }

    public double calculatePerimeter(){
        double perimeter = 2*Math.PI*radius;
        return perimeter;
    }

    public double calculateArea(){
        double area = Math.PI*radius*radius;
        return area;
    }

    public void setColor(Color a) {
        c = a;
    }

    public void setPos(double a, double b){
        xposit = a;
        yposit = b;
    }

    public void setRadius(double value){
        radius = value;
    }

    public Color getColor(){
        return c;
    }

    public double getXPos(){
        return xposit;
    }

    public double getYPos(){
        return yposit;
    }

    public double getRadius(){
        return radius;
    }

    public double drawCircle(Circle c, int l,Canvas drawing,double AreaC){
        drawing.drawShape(c);
        double smallArea = calculateArea();
        AreaC = calculateArea();
        if (l - 1 < 0.001){
            return AreaC;
        } else{
            Circle x = new Circle(c.getXPos(), c.getYPos()-c.getRadius()*3/2, c.getRadius()/2);
            x.setColor(Color.BLUE);
            x.drawCircle(x, l-1, drawing, AreaC);

            Circle y = new Circle(c.getXPos(), c.getYPos()+c.getRadius()*3/2, c.getRadius()/2);
            y.setColor(Color.BLUE);
            y.drawCircle(y, l-1, drawing, AreaC);

            Circle z = new Circle(c.getXPos()-c.getRadius()*3/2, c.getYPos(), c.getRadius()/2);
            z.setColor(Color.BLUE);
            z.drawCircle(z, l-1, drawing, AreaC);

            Circle u = new Circle(c.getXPos()+c.getRadius()*3/2, c.getYPos(), c.getRadius()/2);
            u.setColor(Color.BLUE);
            u.drawCircle(u, l-1, drawing, AreaC);

            return AreaC = AreaC + x.drawCircle(x, l-1, drawing, AreaC) + y.drawCircle(y, l-1, drawing, AreaC) + z.drawCircle(z, l-1, drawing, AreaC) + u.drawCircle(u, l-1, drawing, AreaC);

        }
    }

}