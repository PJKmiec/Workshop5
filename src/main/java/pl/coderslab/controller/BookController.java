package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.model.Book;
import pl.coderslab.service.MemoryBookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private MemoryBookService memoryBookService;

    // hello book
    @RequestMapping("/helloBook")
    @ResponseBody
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
    }

    // show book list
    @RequestMapping("/show-books")
    public String showBooks(Model model) {
        model.addAttribute("books", memoryBookService.getList());
        return "books";
    }

    // add new book
    @RequestMapping("/add-book")
    @ResponseBody
    public String addBook(Model model, HttpServletRequest request) {
        List<Book> list = memoryBookService.getList();
        long id = list.get(list.size() - 1).getId() + 1;
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String type = request.getParameter("type");
        String isbn = request.getParameter("isbn");
        String publisher = request.getParameter("publisher");
        Book book = new Book(id, isbn, title, author, publisher, type);
        memoryBookService.addBook(book);
        return "";
    }

    // get book by ID
    @RequestMapping("/book-view/{id}")
    @ResponseBody
    public Book bookView(@PathVariable long id) {
        return memoryBookService.getBook(id);
    }

    // delete book by ID
    @RequestMapping("/book-delete/{id}")
    @ResponseBody
    public String bookDelete(Model model, @PathVariable int id) {
        memoryBookService.deleteBook(id);
        return "";
    }


}

