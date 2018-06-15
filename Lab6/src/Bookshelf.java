import java.util.Arrays;
import java.util.Comparator;

public class Bookshelf {

    private Book[] books;
    //private int numComparisions = 0;

    /*
    public int getNumComparisions() {
        return numComparisions;
    }
    */

    public Bookshelf(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public Book[] getBooksByAuthor(String author) {
        int numMatches = 0;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                numMatches++;
            }
        }

        Book[] authored = new Book[numMatches];
        int where = 0;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                authored[where] = book;
                where++;
            }
        }

        return authored;
    }

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
        Book mostPopular = null;
        for (Book book : books) {
            if (mostPopular == null || book.getNumRatings() > mostPopular.getNumRatings()) {
                mostPopular = book;
            }
        }
        return mostPopular;
    }

    public Bookshelf mergeBookshelves(Bookshelf bookshelf, String sortBy){
        Book[] thisBooks = this.books;
        Book[] bookshelfBooks = bookshelf.books;
        Book[] mergedBooks = new Book[thisBooks.length + bookshelfBooks.length];

        int ptrThis = 0;
        int ptrBookshelf = 0;

        while(ptrThis < thisBooks.length && ptrBookshelf < bookshelfBooks.length){
            //Compare the books each pointer is pointing to
            //Put the appropriate book in the mergedBooks array
            //Increment the correct pointer
            if(thisBooks[ptrThis].compareTo(bookshelfBooks[ptrBookshelf],sortBy)<0){
                mergedBooks[ptrThis+ptrBookshelf]=thisBooks[ptrThis];
                ptrThis++;
            }else{
                mergedBooks[ptrThis+ptrBookshelf]=bookshelfBooks[ptrBookshelf];
                ptrBookshelf++;
            }
        }

        if(ptrThis < thisBooks.length){
            //Copy over the rest of "this" books into mergedBooks array
            for(int i=ptrThis;i<thisBooks.length;i++){
                mergedBooks[i+ptrBookshelf]=thisBooks[i];
            }
        }
        else {
            //Copy over the rest of "bookshelf" books into mergedBooks array
            for(int i=ptrBookshelf;i<bookshelfBooks.length;i++){
                mergedBooks[ptrThis+i]=bookshelfBooks[i];
            }
        }

        return new Bookshelf(mergedBooks);
    }

    public Bookshelf mergeSortBookshelf(String sortBy){
        if (this.books.length>1){
            Book[] books1 = new Book[books.length/2];//first half of the bookshelf's books
            Book[] books2 = new Book[books.length-books.length/2];//last half of the bookshelf's books
            books1 = Bookshelf.cloneBookArray(this.books,0,books.length/2);
            books2 = Bookshelf.cloneBookArray(this.books,books.length/2,books.length);

            //Make two recursive calls (one the first half of the books and one for the second)
            //return the merged bookshelves
            Bookshelf bookshelf1 = new Bookshelf(books1);
            Bookshelf bookshelf2 = new Bookshelf(books2);

            bookshelf1 = bookshelf1.mergeSortBookshelf(sortBy);
            bookshelf2 = bookshelf2.mergeSortBookshelf(sortBy);
            Bookshelf finalBookshelf = bookshelf1.mergeBookshelves(bookshelf2,sortBy);
            return finalBookshelf;
        }
        else{
            //Base case (it's complete, do not change anything here):
            return this;
        }
    }

    public static void printBookTitles(Book[] books){
        System.out.println("");
        for (int i = 0; i < books.length; i++){
            System.out.println("title: " + books[i].getTitle());
        }
    }

    public static Book[] cloneBookArray(Book[] books, int startIndex, int endIndex){
        int diff = endIndex - startIndex;
        Book[] returnBooks = new Book[diff];
        for (int i = 0; i < returnBooks.length; i++){
            returnBooks[i] = books[startIndex + i];
        }
        return returnBooks;
    }


    public Bookshelf bubbleSortBookshelf(String sortBy){
        for(int i = books.length; i > 0; i--){
            for(int j = 0; j < i-1; j++){
                if(books[j].compareTo(books[j+1], sortBy) > 0){
                    Book temp = books[j+1];
                    books[j+1] = books[j];
                    books[j] = temp;
                }
            }
        }
        return this;
    }

    public Bookshelf selectionSortBookshelf(String sortBy){
        for(int i=0;i<books.length-1;i++){
            int index=i;
            for (int j=i+1;j<books.length;j++){
                if(books[j].compareTo(books[index],sortBy)<0){
                    index=j;
                }
            }
            Book temp = books[index];
            books[index]=books[i];
            books[i]=temp;
        }
        return this;
    }

    public static void main(String[] args) {

        Book[] books = {new Book("1984", "Orwell", "Fiction", 528),
        new Book("A Brief History Of Time", "Hawking", "Astronomy", 212),
        new Book("Alice's Adventures in Wonderland", "Carroll", "Fantasy", 272),
        new Book("Harry Potter : The Philosopher's Stone", "Rowling", "Fantasy", 256),
        new Book("Harry Potter : The Chamber of Secrets", "Rowling", "Fantasy", 368),
        new Book("Harry Potter : The Prisoner of Azkaban", "Rowling", "Fantasy", 464),
        new Book("JK Rowling : Autobiography", "Rowling", "Non-Fiction", 500),
        new Book("The Dark Tower: The Gunslinger", "King", "Horror", 224),
        new Book("The Dark Tower: The Drawing of the Three", "King", "Horror", 400),
        new Book("It", "King", "Horror", 1138),
        new Book("Amazing Spider-Man #1", "Ditko", "Comic", 25)};

        Bookshelf bookshelf1 = new Bookshelf(books);
        Bookshelf bookshelf2 = new Bookshelf(books);

        bookshelf1.selectionSortBookshelf("title");
        printBookTitles(bookshelf1.books);

        bookshelf2.mergeSortBookshelf("title");
        printBookTitles(bookshelf2.books);

    }
}