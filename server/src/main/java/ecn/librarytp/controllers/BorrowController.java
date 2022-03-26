package ecn.librarytp.controllers;

import ecn.librarytp.items.Book;
import ecn.librarytp.items.Borrow;
import ecn.librarytp.items.Person;
import ecn.librarytp.repositories.BookRepository;
import ecn.librarytp.repositories.BorrowRepository;
import ecn.librarytp.repositories.PersonRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class BorrowController {

    @Autowired
    private BorrowRepository myBorrowRepository;

    @Autowired
    @Lazy
    private BookRepository bookRepository;

    @Autowired
    @Lazy
    private PersonRepository personRepository;

    /**
     * Get int from String
     */
    private int getIntFromString(String value) {
        int intValue = -1;
        try {
            intValue = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.WARNING, null, ex);
        }
        return intValue;
    }

    @PostMapping(value = "returnBorrow.do")
    public ModelAndView handleReturn(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject returnedObject = new JSONObject();

        String borrowStr = request.getParameter("id");
        int borrowId = getIntFromString(borrowStr);
        Borrow borrow = myBorrowRepository.returnBook(borrowId);
        if (borrow != null) {
            returnedObject.append("id", borrow.getBorrowId());
        } else {
            returned.setStatus(HttpStatus.BAD_REQUEST);
        }
        returned.addObject("theResponse", returnedObject.toString());

        return returned;
    }

    @PostMapping(value = "addBorrow.do")
    public ModelAndView handleAddBorrow(HttpServletRequest request) {

        String userStr = request.getParameter("userID");
        int userID = getIntFromString(userStr);
        Person user = personRepository.getById(userID);

        String bookStr = request.getParameter("bookID");
        int bookID = getIntFromString(bookStr);
        Book book = bookRepository.getById(bookID);

        myBorrowRepository.create(user, book);
        // Refresh data
        user = personRepository.getById(userID);
        bookRepository.getById(bookID);

        List<Book> myList = bookRepository.findAll();

        ModelAndView returned = new ModelAndView("user");
        returned.addObject("user", user);
        returned.addObject("booksList", myList);

        return returned;
    }

}
