/**
 * Created by yuanx322 on 10/4/17.
 */
public class Bookshelf {
    Book[] bookshelf;
    public Bookshelf(Book[] books){
        bookshelf = books;
    }

    public Book[] getBooksByAuthor(String author){
        int num = 0;
        int p = 0;
        for(int i=0;i<bookshelf.length;i++){
            if(bookshelf[i].author == author){
                num ++;
            }
        }
        Book[] booksByAuthor = new Book[num];
        for(int i=0;i<bookshelf.length;i++){
            if(bookshelf[i].author == author){
                booksByAuthor[p] = bookshelf[i];
                p++;
            }
        }
        return booksByAuthor;
    }
    public Book[] getBooksFor(ReaderProfile reader){
        int num = 0;
        int p = 0;
        for(int i=0;i<bookshelf.length;i++){
            if(reader.favoriteAuthor==bookshelf[i].author&&reader.favoriteGenre==bookshelf[i].genre){
                if(bookshelf[i].numPages*reader.pagesPerMinute<reader.desiredTime){
                    num++;
                }
            }
        }
        Book[] booksFor = new Book[num];
        for(int i=0;i<bookshelf.length;i++){
            if(reader.favoriteAuthor==bookshelf[i].author&&reader.favoriteGenre==bookshelf[i].genre){
                if(bookshelf[i].numPages*reader.pagesPerMinute<=reader.desiredTime){
                    booksFor[p] = bookshelf[i];
                    p++;
                }
            }
        }
        return booksFor;
    }
    public Book getMostPopularBook(){
        int num = 1;
        int p = 0;
        for(int i=0;i<bookshelf.length;i++){
            if(bookshelf[i].getNumRatings()>num){
                num = bookshelf[i].getNumRatings();
                p = i;
            }
        }
        return bookshelf[p];
    }
}
