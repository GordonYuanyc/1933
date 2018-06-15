import java.awt.*;

public class Triangle{
    private double xposit = 0;
    private double yposit = 0;
    private double width = 0;
    private double height = 0;
    double perimeter;
    double area;
    Color c = Color.BLUE;

    public Triangle(double a, double b, double c, double d){
        xposit = a;
        yposit = b;
        width = c;
        height = d;
    }

    public double calculatePerimeter(){
        double perimeter = width + 2*Math.sqrt(height*height+width*width/4);
        return perimeter;
    }

    public double calculateArea(){
        double area = width*height/2;
        return area;
    }

    public void setColor(Color a){
        c = a;
    }

    public void setPos(double a,double b){
        xposit = a;
        yposit = b;
    }

    public void setHeight(double a){
        height = a;
    }

    public void setWidth(double a){
        width = a;
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

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public double drawTriangle(Triangle t, int l,Canvas drawing, double AreaT){
        drawing.drawShape(t);
        double smallArea = calculateArea();
        AreaT = calculateArea();
        if (l - 1 < 0.001){
            return AreaT;
        } else{
            Triangle x = new Triangle(t.getXPos()-t.getWidth()/2, t.getYPos(), t.getWidth()/2, t.getHeight()/2);
            x.setColor(Color.BLUE);
            x.drawTriangle(x, l-1, drawing, AreaT);

            Triangle y = new Triangle(t.getXPos()+t.getWidth(), t.getYPos(), t.getWidth()/2, t.getHeight()/2);
            y.setColor(Color.BLUE);
            y.drawTriangle(y, l-1, drawing, AreaT);

            Triangle z = new Triangle(t.getXPos()+t.getWidth()*1/4, t.getYPos()-t.getHeight(), t.getWidth()/2, t.getHeight()/2);
            z.setColor(Color.BLUE);
            z.drawTriangle(z, l-1, drawing, AreaT);

            return AreaT = AreaT + x.drawTriangle(x, l-1, drawing, AreaT) + y.drawTriangle(y, l-1, drawing, AreaT) + z.drawTriangle(z, l-1, drawing, AreaT);
        }
    }

}