package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;
import pl.coderslab.service.MemoryBookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private MemoryBookService memoryBookService;

    @Autowired
    private BookService bookService;

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
    @PostMapping("/add-book")
    public void addBook(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");
        List<Book> list = memoryBookService.getList();
        long id = list.get(list.size() - 1).getId() + 1;
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String type = request.getParameter("type");
        String isbn = request.getParameter("isbn");
        String publisher = request.getParameter("publisher");
        Book book = new Book(id, isbn, title, author, publisher, type);
        memoryBookService.addBook(book);
    }

    // get book by ID
    @RequestMapping("/book-view/{id}")
    @ResponseBody
    public Book bookView(@PathVariable long id) {
        return memoryBookService.getBook(id);
    }

    // delete book by ID
    @DeleteMapping("/book-delete/{id}")
    public void bookDelete(Model model, @PathVariable int id, HttpServletResponse response) {
        response.setContentType("text/plain");
        memoryBookService.deleteBook(id);
    }

    // edit book
    @PutMapping("/book-edit/{id}")
    public void addBook(Model model, @PathVariable long id, HttpServletRequest request, HttpServletResponse response, @RequestBody MultiValueMap<String, String> formParams) {
        response.setContentType("text/plain");

//        BufferedReader br = null;
//        String data = "";
//
//        try {
//            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//            data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String[] parts = data.split("&");
//
//        List<Book> list = memoryBookService.getList();
//        String title = parts[1].split("=")[1];
//        String author = parts[2].split("=")[1];
//        String type = parts[3].split("=")[1];
//        String isbn = parts[4].split("=")[1];
//        String publisher = parts[5].split("=")[1];

        String isbn = formParams.getFirst("isbn");
        String title = formParams.getFirst("title");
        String author = formParams.getFirst("author");
        String publisher = formParams.getFirst("publisher");
        String type = formParams.getFirst("type");

        Book book = new Book(id, isbn, title, author, publisher, type);
        memoryBookService.editBook(book);
    }
}

