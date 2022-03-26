package ecn.librarytp.controllers;

import ecn.librarytp.items.Person;
import ecn.librarytp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private PersonRepository myPersonRepository;

    @GetMapping(value = "index.do")
    public ModelAndView handleGet() {
        ModelAndView returned;

        returned = new ModelAndView("index");
        return returned;
    }

    @PostMapping(value = "index.do")
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
