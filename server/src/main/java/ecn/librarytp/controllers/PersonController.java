/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.controllers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import ecn.librarytp.items.Person;
import ecn.librarytp.repositories.BookRepository;
import ecn.librarytp.repositories.PersonRepository;

/**
 *
 * @author ECN
 */
@Controller
public class PersonController {

    @Autowired
    private PersonRepository myPersonRepository;

    @Autowired
    private BookRepository myBookRepository;

    /**
     * Get Date from string
     *
     * @param aDate
     * @param format
     * @return
     */
    private Date getDateFromString(String aDate, String format) {
        Date returnedValue = null;
        try {
            // try to convert
            SimpleDateFormat aFormater = new SimpleDateFormat(format);
            returnedValue = aFormater.parse(aDate);
        } catch (ParseException ex) {
        }

        if (returnedValue != null) {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(returnedValue);
        }
        return returnedValue;
    }

    @RequestMapping(value = "editUser.do", method = RequestMethod.POST)
    public ModelAndView handlePostEditUser(HttpServletRequest request) {
        ModelAndView returned;

        // Get user
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);

        if (id > 0) {
            Person person = myPersonRepository.getById(id);
            List<Book> myList = myBookRepository.findAll();
            
            returned = new ModelAndView("user");
            returned.addObject("user", person);
            returned.addObject("booksList", myList);
        } else {
            List<Person> myList = myPersonRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("usersList", myList);
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
            Logger.getLogger(PersonController.class.getName()).log(Level.WARNING, null, ex);
        }
        return intValue;
    }

    @RequestMapping(value = "saveUser.do", method = RequestMethod.POST)
    public ModelAndView handlePostSaveUser(HttpServletRequest request) {
        ModelAndView returned;

        // Create or update user
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String birthdateStr = request.getParameter("Birthdate");
        Date birthdate = getDateFromString(birthdateStr, "yyyy-MM-dd");
        if (id > 0) {
            myPersonRepository.update(id, firstName, lastName, birthdate);
        } else {
            myPersonRepository.create(firstName, lastName, birthdate);
        }

        // return view
        returned = new ModelAndView("users");
        List<Person> myList = myPersonRepository.findAll();
        returned.addObject("usersList", myList);

        return returned;
    }

    @RequestMapping(value = "deleteUser.do", method = RequestMethod.POST)
    public ModelAndView handlePostDeleteUser(HttpServletRequest request) {
        ModelAndView returned;

        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        myPersonRepository.delete(id);

        returned = new ModelAndView("users");
        List<Person> myList = myPersonRepository.findAll();
        returned.addObject("usersList", myList);

        return returned;
    }

    @RequestMapping(value = "createUser.do", method = RequestMethod.POST)
    public ModelAndView handlePostCreateUser() {
        ModelAndView returned;

        Person person = new Person();
        returned = new ModelAndView("user");
        returned.addObject("user", person);

        return returned;
    }

    @RequestMapping(value = "switchUsers.do", method = RequestMethod.POST)
    public ModelAndView handlePost(MyUser user) {
        ModelAndView returned;

        List<Person> myList = myPersonRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("usersList", myList);

        return returned;
    }
}
