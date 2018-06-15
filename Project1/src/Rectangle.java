import java.awt.*;

public class Rectangle{
    private double xposit = 0;
    private double yposit = 0;
    private double width = 0;
    private double height = 0;
    double perimeter;
    double area;
    Color c = Color.BLUE;

    public Rectangle(double a, double b, double c, double d){
        xposit = a;
        yposit = b;
        width = c;
        height = d;
    }

    public double calculatePerimeter(){
        perimeter = 2*(width + height);
        return perimeter;
    }

    public double calculateArea(){
        double area = width*height;
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

    public double drawRectangle(Rectangle r, int l,Canvas drawing, double AreaR){
        drawing.drawShape(r);
        double smallArea = calculateArea();
        AreaR = calculateArea();
        if (l - 1 < 0.001){
            return AreaR;
        } else{
            Rectangle x = new Rectangle(r.getXPos()-r.getWidth()/2, r.getYPos()-r.getHeight()/2, r.getWidth()/2, r.getHeight()/2);
            x.setColor(Color.BLUE);
            x.drawRectangle(x, l-1, drawing, AreaR);

            Rectangle y = new Rectangle(r.getXPos()+r.getWidth(), r.getYPos()-r.getHeight()/2, r.getWidth()/2, r.getHeight()/2);
            y.setColor(Color.BLUE);
            y.drawRectangle(y, l-1, drawing, AreaR);

            Rectangle z = new Rectangle(r.getXPos()-r.getWidth()/2, r.getYPos()+r.getHeight(), r.getWidth()/2, r.getHeight()/2);
            z.setColor(Color.BLUE);
            z.drawRectangle(z, l-1, drawing, AreaR);

            Rectangle u = new Rectangle(r.getXPos()+r.getWidth(), r.getYPos()+r.getHeight(), r.getWidth()/2, r.getHeight()/2);
            u.setColor(Color.BLUE);
            u.drawRectangle(u, l-1, drawing, AreaR);

            return AreaR = AreaR + x.drawRectangle(x, l-1, drawing, AreaR) + y.drawRectangle(y, l-1, drawing, AreaR) + z.drawRectangle(z, l-1, drawing, AreaR) + u.drawRectangle(u, l-1, drawing, AreaR);
        }
    }

}