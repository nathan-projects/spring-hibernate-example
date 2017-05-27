package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupOneOne;
import poc.springbootexample.models.Group.GroupOneOneDao;
import poc.springbootexample.models.User.UserOneOne;
import poc.springbootexample.models.User.UserOneOneDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class UserOneOneController {

    @Autowired
    private UserOneOneDao userDao;

    @Autowired
    private GroupOneOneDao groupDao;


    @RequestMapping("/createOneToOne")
    @ResponseBody
    public ModelAndView createOneToOne(String email, String name, Role roleVal, Long groupVal) {
        if (groupVal == null) {
            return new ModelAndView("redirect:/addUserOneToOne", "msg", "Please select a group");
        }
        if (name.isEmpty()) {
            return new ModelAndView("redirect:/addUserOneToOne", "msg", "Please enter a name");
        }
        GroupOneOne foundGroup = groupDao.findOne(groupVal);
        UserOneOne user = new UserOneOne(email, name, roleVal, foundGroup);
        try {
            userDao.save(user);
        } catch (Exception e) {
            String msg = "Failed to add user";
            return new ModelAndView("/OneToOne","msg",msg);
        }
        String msg = "Successfully added user: " + user.getName();
        return new ModelAndView("redirect:/OneToOne","msg",msg);
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/deleteUserOneToOne")
    @ResponseBody
    public ModelAndView delete(Long userId) {
        if (userId == null) { return new ModelAndView("redirect:/OneToOne","msg","Please select a user"); }

        try {
            userDao.delete(userId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/OneToOne","msg","Failed to delete user");
        }

        return new ModelAndView("redirect:/OneToOne","msg","User successfully deleted");
    }

}
