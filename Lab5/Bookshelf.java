public class Bookshelf {

    private Book[] books;

    public Bookshelf(Book[] books) {
        this.books = books;
    }

    public Book[] getBooksByAuthor(String author) {
        int numMatches = 0;
        // Count the books that matched so we can make an array whose length
        // is exactly the number of books authored by author
        //
        // For each or for(int i = 0; i < books.length; i++) is acceptable here
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                numMatches++;
            }
        }

        Book[] authored = new Book[numMatches];

        // We need to keep track of where we are in the array we are going to return
        int where = 0;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                authored[where] = book;
                where++;
            }
        }

        return authored;
    }

    // More or less the same thing as getBooksByAuthor -- just a more complicated if-statement
    public Book[] getBooksFor(ReaderProfile reader) {
        int numMatches = 0;
        for (Book book : books) {
            boolean authorMatches = book.getAuthor().equals(reader.getFavoriteAuthor());
            boolean genreMatches = book.getGenre().equals(reader.getFavoriteGenre());

            double ppm = reader.getPagesPerMinute();
            boolean timeMatches = (book.getNumPages() / ppm) <= reader.getDesiredTime();

            if (authorMatches && genreMatches && timeMatches) {
                numMatches++;
            }
        }

        Book[] matches = new Book[numMatches];
        int where = 0;
        for (Book book : books) {
            boolean authorMatches = book.getAuthor().equals(reader.getFavoriteAuthor());
            boolean genreMatches = book.getGenre().equals(reader.getFavoriteGenre());

            double ppm = reader.getPagesPerMinute();
            boolean timeMatches = (book.getNumPages() / ppm) <= reader.getDesiredTime();

            if (authorMatches && genreMatches && timeMatches) {
                matches[where] = book;
                where++;
            }
        }

        return matches;
    }

    public Book getMostPopularBook() {
        // Current most popular book
        Book mostPopular = null;
        for (Book book : books) {
            // If we haven't seen a book yet or this book has more ratings than the most popular
            // we have seen so far, update the mostPopular book to be this one.
            if (mostPopular == null || book.getNumRatings() > mostPopular.getNumRatings()) {
                mostPopular = book;
            }
        }
        return mostPopular;
    }
}