/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ecn.librarytp.items.Book;
import ecn.librarytp.items.Borrow;
import ecn.librarytp.items.Person;
import ecn.librarytp.repositories.BookRepository;
import ecn.librarytp.repositories.BorrowRepository;
import ecn.librarytp.repositories.PersonRepository;

/**
 *
 * @author ECN
 */
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
     *
     * @param value
     * @return
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

    @RequestMapping(value = "returnBorrow.do", method = RequestMethod.POST)
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

    @RequestMapping(value = "addBorrow.do", method = RequestMethod.POST)
    public ModelAndView handleAddBorrow(HttpServletRequest request) {

        String userStr = request.getParameter("userID");
        int userID = getIntFromString(userStr);
        Person user = personRepository.getById(userID);

        String bookStr = request.getParameter("bookID");
        int bookID = getIntFromString(bookStr);
        Book book = bookRepository.getById(bookID);

        Borrow borrow = myBorrowRepository.create(user, book);
        // Refresh data
        user = personRepository.getById(userID);
        book = bookRepository.getById(bookID);
        
        List<Book> myList = bookRepository.findAll();

        ModelAndView returned = new ModelAndView("user");
        returned.addObject("user", user);
        returned.addObject("booksList", myList);

        return returned;
    }

}
