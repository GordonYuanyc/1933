/**
 * Created by yuanx322 on 10/4/17.
 */
import java.util.ArrayList;
import java.util.Random;
public class RateMyBooks {
    public static void main(String[] args){
        Book book1 = new Book("infiniteSynthesis","fripSide","music",10);
        Book book2 = new Book("icecreamGirl","uchida aya","music",10);
        Book book3 = new Book("unbalancedLove","printemps","music",10);

        for(int i= 0;i<50;i++){
            int random = (int )(Math.random()*5+1);
            if (random >2){
                book1.addRating(random);
            }
        }
        for(int i= 0;i<50;i++){
            int random = (int )(Math.random()*5+1);
            if (random>2){
                book2.addRating(random);
            }
        }
        for(int i= 0;i<50;i++){
            int random = (int )(Math.random()*5+1);
            if (random>2){
                book3.addRating(random);
            }
        }



        System.out.println(book1.getRatingSummary());
        System.out.println(book2.getRatingSummary());
        System.out.println(book3.getRatingSummary());
    }
}
