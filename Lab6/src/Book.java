public class Book {

    private String title;
    private String author;
    private String genre;
    private int numPages;

    private int[] ratings;

    public Book(String title, String author, String genre, int numPages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numPages = numPages;

        ratings = new int[5];
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumPages() {
        return numPages;
    }

    public void addRating(int rating) {
        ratings[rating-1]++;
    }

    public double getAverageRating() {
        int total = 0;
        double numRaters = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += ((i + 1) * ratings[i]);
            numRaters += ratings[i];
        }
        if (numRaters == 0) {
            return 0;
        } else {
            return total / numRaters;
        }
    }

    public int getNumRatings() {
        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += ratings[i];
        }
        return total;
    }

    public String getRatingSummary() {
        String summary = "Avg Rating : " + getAverageRating() + "\n";
        for (int i = 0; i < ratings.length; i++) {
            summary = summary + (i + 1) + " | ";
            for (int j = 0; j < ratings[i]; j++) {
                summary += "*";
            }
            summary += "\n";
        }
        return summary;
    }

    public boolean equals(Book other) {
        boolean authorMatches = author.equals(other.getAuthor());
        boolean titleMatches = title.equals(other.getTitle());
        boolean avgMatches = Math.abs(getAverageRating() - other.getAverageRating()) < 0.0001;
        return authorMatches && titleMatches && avgMatches;
    }

    public Integer compareTo(Book other,String sortBy){
        if(sortBy.equals("title")){
            int result = this.title.compareTo(other.getTitle());
            return result;
        }else if(sortBy.equals("author")){
            int result = this.author.compareTo(other.getAuthor());
            return result;
        }else if(sortBy.equals("genre")){
            int result = this.genre.compareTo(other.getGenre());
            return result;
        }else if(sortBy.equals("numPages")){
            int result = this.numPages-other.getNumPages();
            return result;
        }else{
            return null;
        }
    }
    

}