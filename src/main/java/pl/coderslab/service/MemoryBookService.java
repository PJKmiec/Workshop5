package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryBookService implements BookService {

    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    // Pobieranie listy danych
    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    // Pobieranie obiektu po wskazanym identyfikatorze
    public Book getBook(long id) {
        Book book = list.stream()
                .filter(b -> id == (b.getId()))
                .findFirst()
                .orElse(null);

        return book;
    }

    // Dodawanie obiektu
    public void addBook(Book book) {
        list.add(new Book(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getType()));
    }

    //    Usuwanie obiektu
    public void deleteBook(int id) {
        List<Book> list2 = list.stream()
                .filter(b -> id != (b.getId()))
                .collect(Collectors.toList());
        setList(list2);
    }

    // Edycja obiektu
    public void editBook(Book book) {
        int index = -1;
        for (Book b : list) {
            if (b.getId() == book.getId()) {
                index = list.indexOf(b);
            }
        }
        list.set(index, book);
    }


}
