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

    // Taking advantage of there being only five possible ratings
    // ratings[0] = # of 1 star ratings
    // ratings[1] = # of 2 star ratings
    // ratings[2] = # of 3 star ratings
    // so and so so forth.
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

        // Can use ==, but using this just in case of weird double comparisons
        boolean avgMatches = Math.abs(getAverageRating() - other.getAverageRating()) < 0.0001;
        return authorMatches && titleMatches && avgMatches;
    }
}