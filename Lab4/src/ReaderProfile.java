/**
 * Created by yuanx322 on 10/4/17.
 */
public class ReaderProfile {
    String favoriteAuthor;
    String favoriteGenre;
    double pagesPerMinute;
    int desiredTime;

    public ReaderProfile(String f1,String f2, double p, int d){
        favoriteAuthor = f1;
        favoriteGenre = f2;
        pagesPerMinute = p;
        desiredTime = d;
    }
}
