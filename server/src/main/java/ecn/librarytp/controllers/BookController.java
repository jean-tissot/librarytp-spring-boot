package ecn.librarytp.controllers;

import ecn.librarytp.items.Book;
import ecn.librarytp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class BookController {

    @Autowired
    private BookRepository myBookRepository;

    @PostMapping(value = "editBook.do")
    public ModelAndView handlePostEditBook(HttpServletRequest request) {
        ModelAndView returned;

        // Get ID
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);

        // Get book if exists
        if (id > 0) {
            Book book = myBookRepository.getById(id);
            returned = new ModelAndView("book");
            returned.addObject("book", book);
        } else {
            List<Book> myList = myBookRepository.findAll();
            returned = new ModelAndView("books");
            returned.addObject("booksList", myList);
        }

        return returned;
    }

    /**
     * Get int from String
     */
    private int getIntFromString(String value) {
        int intValue = -1;
        try {
            intValue = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.WARNING, null, ex);
        }
        return intValue;
    }

    @PostMapping(value = "saveBook.do")
    public ModelAndView handlePostSaveBook(HttpServletRequest request) {
        ModelAndView returned;

        // Update or create book
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        String title = request.getParameter("Title");
        String authors = request.getParameter("Authors");
        if (id > 0) {
            myBookRepository.update(id, title, authors);
        } else {
            myBookRepository.create(title, authors);
        }

        // Return view
        returned = new ModelAndView("books");
        List<Book> myList = myBookRepository.findAll();
        returned.addObject("booksList", myList);

        return returned;
    }

    @PostMapping(value = "deleteBook.do")
    public ModelAndView handlePostDeleteBook(HttpServletRequest request) {
        ModelAndView returned;

        // Get book
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        myBookRepository.delete(id);

        // Remove book
        returned = new ModelAndView("books");
        List<Book> myList = myBookRepository.findAll();
        returned.addObject("booksList", myList);

        return returned;
    }

    @PostMapping(value = "createBook.do")
    public ModelAndView handlePostCreateBook() {
        ModelAndView returned;

        // Create book
        Book book = new Book();
        returned = new ModelAndView("book");
        returned.addObject("book", book);

        return returned;
    }

    @PostMapping(value = "switchBooks.do")
    public ModelAndView handlePost(MyUser user) {
        ModelAndView returned;

        // Get books
        List<Book> myList = myBookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("booksList", myList);

        return returned;
    }
}
