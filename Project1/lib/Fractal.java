import java.awt.*;
import java.util.Scanner;

public class Fractal{
    public static void main(String args[]){

        Scanner whatShape = new Scanner(System.in);
        String Shape = null;
        System.out.print("Shape: ");
        Shape = whatShape.nextLine();

        if (Shape.equals("Triangle")){
            int depthLeft = 7; //Draw Triangle
            Triangle myTriangle = new Triangle(800,1200,400,400);
            Canvas drawing = new Canvas(2000,2000);
            myTriangle.setColor(Color.BLUE);
            double AreaT = 0;
            myTriangle.drawTriangle(myTriangle,depthLeft,drawing,AreaT);
            double p = myTriangle.drawTriangle(myTriangle,depthLeft,drawing,AreaT);
            System.out.println("The area of Triangle is: " + p);

        } else if (Shape.equals("Rectangle")){
            int depthLeft = 7; //Draw Rectangle
            Rectangle myRectangle = new Rectangle(800,800,400,400);
            Canvas drawing = new Canvas(2000,2000);
            myRectangle.setColor(Color.BLUE);
            double AreaR = 0;
            myRectangle.drawRectangle(myRectangle,depthLeft,drawing,AreaR);
            double p = myRectangle.drawRectangle(myRectangle,depthLeft,drawing,AreaR);
            System.out.println("The area of Rectangle is: " + p);

        } else if (Shape.equals("Circle")){
            int depthLeft = 7; //Draw Circle
            Circle myCircle = new Circle(1000,1000,400);
            Canvas drawing = new Canvas(2000,2000);
            myCircle.setColor(Color.BLUE);
            double AreaC = 0.0;
            myCircle.drawCircle(myCircle,depthLeft,drawing,AreaC);
            double p = myCircle.drawCircle(myCircle,depthLeft,drawing,AreaC);
            System.out.println("The area of Circle is: " + p);
        }



    }
}
