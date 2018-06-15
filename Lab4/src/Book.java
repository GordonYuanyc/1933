/**
 * Created by yuanx322 on 10/4/17.
 */
import java.util.ArrayList;

public class Book {
    String title;
    String author;
    String genre;
    int numPages;
    int[] rate = new int[5];
    private int numRating;

    public Book(String t, String a, String g, int n){
        title = t;
        author = a;
        genre = g;
        numPages = n;
    }

    public int getNumRatings(){
        numRating = 1;
        numRating = rate[0]+rate[1]+rate[2]+rate[3]+rate[4];
        if (numRating == 0){
            numRating = 1;
            return numRating;
        }else{
            return numRating;
        }
    }
    public void addRating(int rating){
        rate[rating-1]++;
    }
    public double getAverageRating(){
        int allRate = 1;
        allRate = allRate+rate[0]*1+rate[1]*2+rate[2]*3+rate[3]*4+rate[4]*5-1;
        double averageRating = allRate/getNumRatings();
        return averageRating;
    }
    public boolean equals(Book other){
        if(title == other.title && author == other.author && getAverageRating() == other.getAverageRating()){
            return true;
        }else{
            return false;
        }
    }
    public String getRatingSummary(){
        String rateStar1 = "1 |";
        String rateStar2 = "2 |";
        String rateStar3 = "3 |";
        String rateStar4 = "4 |";
        String rateStar5 = "5 |";
        for (int i = 0; i<rate[0];i++) {
            rateStar1 = rateStar1 + "*";
        }
        for (int i = 0; i<rate[1];i++){
            rateStar2 = rateStar2 + "*";
        }
        for (int i = 0; i<rate[2];i++) {
            rateStar3 = rateStar3 + "*";
        }
        for (int i = 0; i<rate[3];i++) {
            rateStar4 = rateStar4 + "*";
        }
        for (int i = 0; i<rate[4];i++) {
            rateStar5 = rateStar5 + "*";
        }

        return "Ave Rating"+getAverageRating()+"\n"+rateStar1+"\n"+rateStar2+"\n"+rateStar3+"\n"+rateStar4+"\n"+rateStar5;
    }
    public int getNumPages(){
        return numPages;
    }


}
