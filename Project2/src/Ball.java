import java.lang.Math;
import java.awt.*;
public class Ball {
	double x,y,r;
	private double speedX;
	private double speedY;
	double timeNumber;
	Color color;
	public Ball(double x, double y, double r, Color c){
		this.x=x;
		this.y=y;
		this.r=r;
		this.color=c;
	}
	public double calculatePerimeter(){
		return 2*Math.PI*r;
	}
	public double calculateArea(){
		return Math.PI*r*r;		
	}
	public void setColor(Color c){
		color=c;
	}
	public void setPos(double x,double y){
		this.x=x;
		this.y=y;
	}
	public void setRadius(double r){
		this.r=r;
	}
	public Color getColor(){
		return color;
	}
	public double getXPos(){
		return x;
	}
	public double getYPos(){
		return y;
	}
	public double getRadius(){
		return r;
	}
	public void setSpeedX(double sx){
		speedX = sx;
	}
	public void setSpeedY(double sy){
		speedY = sy;
	}
	public double getSpeedX(){
		return speedX;
	}
	public double getSpeedY(){
		return speedY;
	}
	public void updatePosition(double times){
		timeNumber = times;
	}
	public Boolean intersect(Ball anotherBall){
		if(getRadius()+anotherBall.getRadius() < Math.sqrt(Math.pow((anotherBall.getXPos()-getXPos()),2)+Math.pow((anotherBall.getYPos()-getYPos()),2))){
			return false;
		}else{
			return true;
		}
	}
	public void collide(Ball anotherBall){//change the xyspeed of both xy balls
		double xj = anotherBall.x;
		double yj = anotherBall.y;
		double vix = getSpeedX();
		double viy = getSpeedY();
		double vjx = anotherBall.getSpeedX();
		double vjy = anotherBall.getSpeedY();
		double d = Math.sqrt(Math.pow((x-xj),2)+Math.pow((y-yj),2));
		double deltax = (x-xj)/d;
		double deltay = (y-yj)/d;

		if (intersect(anotherBall)){
			double a = (vjx*deltax+vjy*deltay)*deltax-(-vix*deltay+viy*deltax)*deltay;
			double b = (vjx*deltax+vjy*deltay)*deltay+(-vix*deltay+viy*deltax)*deltax;
			double c = (vix*deltax+viy*deltay)*deltax-(-vjx*deltay+vjy*deltax)*deltay;
			double e = (vix*deltax+viy*deltay)*deltay+(-vjx*deltay+vjy*deltax)*deltax;
			setSpeedX(a);
			setSpeedY(b);
			anotherBall.setSpeedX(c);
			anotherBall.setSpeedY(e);
		}
    }
	public void changeColor(Ball anotherBall){
	    if (getColor() == Color.RED || anotherBall.getColor() == Color.RED){
	        color = color.RED;
	        anotherBall.setColor(color.RED);
        }
    }
}


