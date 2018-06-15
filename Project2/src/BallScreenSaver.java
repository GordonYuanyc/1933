import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class BallScreenSaver extends AnimationFrame {
    private double ballX, ballY, ballXSpeed, ballYSpeed;
    private int ballSize=18;
    private int START_SPEED=300;
    public final int BORDER=30;
    public int n = 25;
    private CollisionLogger collisionLogger;
    private static final int COLLISION_BUCKET_WIDTH = 20;
    private int saveCounter=0;

    Ball[] ball = new Ball[n];

    public BallScreenSaver(){
//        super();
        Ball[] ball = new Ball[n];
        for(int i=0;i<ball.length;i++){
            ball[i].setPos(randdouble(BORDER,getWidth()-BORDER),randdouble(BORDER,getHeight()-BORDER));
            setRandDir(START_SPEED);
            setFPS(30);
        }
        collisionLogger = new CollisionLogger(this.getWidth(),this.getHeight(), COLLISION_BUCKET_WIDTH);
    }
    public BallScreenSaver(int w, int h, String name, int n){
 //       super(w,h,name);
        ball = new Ball[n];
        for(int i=0;i<ball.length;i++){
            if(i !=8){
                ball[i] = new Ball(randdouble(BORDER,getWidth()-BORDER),randdouble(BORDER,getHeight()-BORDER),9,Color.GREEN);
                setRandDir(START_SPEED);
                ball[i].setSpeedX(ballXSpeed);
                ball[i].setSpeedY(ballYSpeed);
                setFPS(30);
            }else{
                ball[i] = new Ball(randdouble(BORDER,getWidth()-BORDER),randdouble(BORDER,getHeight()-BORDER),9,Color.RED);
                setRandDir(START_SPEED);
                ball[i].setSpeedX(ballXSpeed);
                ball[i].setSpeedY(ballYSpeed);
                setFPS(30);
            }
        }
        collisionLogger = new CollisionLogger(this.getWidth(), this.getHeight(), COLLISION_BUCKET_WIDTH);
    }
    public static void main(String[] args) {
        BallScreenSaver bss = new BallScreenSaver(1800, 1800, "Bouncing Ball",25);
        bss.start();
    }

    public void action() {
        //This method is called once every frame to update the state of the BouncingBall.
        //update both X and Y positions
        for(int i=0; i<ball.length;i++){
            ball[i].setPos(ball[i].getXPos()+ball[i].getSpeedX()/getFPS(),ball[i].getYPos()+ball[i].getSpeedY()/getFPS());
        }
        //handle collisions with the border
        for(int i=0; i<ball.length; i++){
            if (ball[i].getXPos()< BORDER || ball[i].getXPos() + ballSize > getWidth() - BORDER) {
                ball[i].setSpeedX(ball[i].getSpeedX()* -1);
            }
            if (ball[i].getYPos()< BORDER || ball[i].getYPos() + ballSize > getHeight() - BORDER) {
                ball[i].setSpeedY(ball[i].getSpeedY()* -1);
            }
        }
        //handle collisions between balls
        for(int i=0; i<ball.length; i++){
            for(int j=0; j<ball.length;j++){
                if(j>i){
                    if (ball[i].intersect(ball[j])) {
                        ball[i].collide(ball[j]);
                        ball[i].changeColor(ball[j]);
                        collisionLogger.collide(ball[i],ball[j]);
                    }
                }
            }
        }
    }

    public void draw(Graphics g){
        //This method is called once every frame to draw the Frame.
        //This is how you use the graphics object to draw
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.white);
        g.drawRect(BORDER,BORDER,getWidth()-BORDER*2,getHeight()-BORDER*2);
        for(int i=0;i<ball.length;i++){
            g.setColor(ball[i].getColor());
            g.fillOval((int)ball[i].getXPos(), (int)ball[i].getYPos(), ballSize, ballSize);
        }
    }
    public void processKeyEvent(KeyEvent k){
        int keyCode = k.getKeyCode();
        // This captures the user pressing the "p" key and prints out the current collisionLog to an image.
        if (k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_P) {
            EasyBufferedImage image = EasyBufferedImage.createImage(collisionLogger.getNormalizedHeatMap());
            try {
                image.save("heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }
        }
        // This is to control the speed of the ball by pressing the left key to decrease the speed by 10% and by
        //pressing the right key to increase the speed by 10%.
        for(int i=0; i<ball.length;i++){
            if(k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_LEFT){
                ball[i].setSpeedX(ball[i].getSpeedX()*0.9);
                ball[i].setSpeedY(ball[i].getSpeedY()*0.9);
            }else if(k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_RIGHT){
                ball[i].setSpeedX(ball[i].getSpeedX()*1.1);
                ball[i].setSpeedY(ball[i].getSpeedY()*1.1);
            }
        }
        //This is to change color by pressing "w" or "g".
        if (k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_G) {
            for(int i=0;i<ball.length;i++){
                if ( ball[i].getColor() == Color.WHITE){
                    ball[i].setColor(Color.GREEN);
                }
            }
        }
        if (k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_W) {
            for(int i=0;i<ball.length;i++){
                if ( ball[i].getColor() == Color.GREEN){
                    ball[i].setColor(Color.WHITE);
                }
            }
        }
    }

    //Methods to get random staff.
    public void setRandDir(double speed){
        double dir=randdouble(0,Math.PI*2);
        ballXSpeed=Math.cos(dir)*speed;
        ballYSpeed=Math.sin(dir)*speed;
    }
    public int randInt(int min, int max){
        //a utility method to get a random int between min and max.
        return (int)(Math.random()*(max-min)+min);
    }
    public double randdouble(double min, double max){
        //a utility method to get a random double between min and max.
        return (Math.random()*(max-min)+min);
    }
}
