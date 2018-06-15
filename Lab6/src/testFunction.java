public class testFunction {
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


        System.out.println("genre comparison"+books[0].compareTo(books[0],"genre"));
        System.out.println("genre comparison"+books[1].compareTo(books[0],"genre"));
        System.out.println("title comparison"+books[0].compareTo(books[1],"title"));
        System.out.println("author comparison"+books[0].compareTo(books[5],"author"));
        System.out.println("numPage comparison"+books[0].compareTo(books[4],"numPages"));
    }

}
