/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ecn.librarytp.items.Book;
import ecn.librarytp.repositories.BookRepository;

/**
 *
 * @author ECN
 */
@Controller
public class BookController {

    @Autowired
    private BookRepository myBookRepository;

    @RequestMapping(value = "editBook.do", method = RequestMethod.POST)
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
     *
     * @param value
     * @return
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

    @RequestMapping(value = "saveBook.do", method = RequestMethod.POST)
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

    @RequestMapping(value = "deleteBook.do", method = RequestMethod.POST)
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

    @RequestMapping(value = "createBook.do", method = RequestMethod.POST)
    public ModelAndView handlePostCreateBook() {
        ModelAndView returned;

        // Create book
        Book book = new Book();
        returned = new ModelAndView("book");
        returned.addObject("book", book);

        return returned;
    }

    @RequestMapping(value = "switchBooks.do", method = RequestMethod.POST)
    public ModelAndView handlePost(MyUser user) {
        ModelAndView returned;

        // Get books
        List<Book> myList = myBookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("booksList", myList);

        return returned;
    }
}
