package pl.coderslab.service;

import pl.coderslab.model.Book;
import java.util.List;

public interface BookService {

    List<Book> getList();
    void setList(List<Book> list);
    Book getBook(long id);
    void addBook(Book book);
    void deleteBook(int id);
    void editBook(Book book);

}
