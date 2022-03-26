/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ecn.librarytp.items.Person;
import ecn.librarytp.repositories.PersonRepository;

/**
 *
 * @author ECN
 */
@Controller
public class LoginController {
    @Autowired
    private PersonRepository myPersonRepository;

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public ModelAndView handleGet() {
        ModelAndView returned;

        returned = new ModelAndView("index");
        return returned;
    }
/*
    @RequestMapping(value = "index.do", method = RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request) {
        ModelAndView returned;

        String login = request.getParameter("user");
        String pass = request.getParameter("passwd");

        if ((login != null) && (pass != null)
                && (login.equals("admin")) && (pass.equals("admin"))) {
            returned = new ModelAndView("users");
        } else {
            returned = new ModelAndView("index");
        }

        return returned;
    }
*/
    
    @RequestMapping(value = "index.do", method = RequestMethod.POST)
    public ModelAndView handlePost(MyUser user) {
        ModelAndView returned;

        String login = user.getUser();
        String pass = user.getPasswd();

        if ((login != null) && (pass != null)
                && (login.equals("admin")) && (pass.equals("admin"))) {
            List<Person> myList = myPersonRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("usersList", myList);
        } else {
            returned = new ModelAndView("index");
        }

        return returned;
    }
}
