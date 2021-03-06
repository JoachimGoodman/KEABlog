package dk.kea.blog.Controller;

import dk.kea.blog.Models.User;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value="/login")
    public String login(@ModelAttribute (name="User") User user, HttpSession session) {

        if (userService.verifyUser(user)) {
            session.setAttribute("email",       user.getEmail());
            session.setAttribute("firstName",   user.getFirstname());
            session.setAttribute("lastName",    user.getLastname());
            session.setAttribute("city",        user.getCity());
            session.setAttribute("roleName",    user.getRoleName());
            session.setAttribute("level",       user.getLevel());
            session.setAttribute("id",          user.getId());
            session.setAttribute("age",         user.getAge());
            session.setAttribute("date",        user.getDate());
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
