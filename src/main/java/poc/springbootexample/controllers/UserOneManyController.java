package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupOneMany;
import poc.springbootexample.models.Group.GroupOneManyDao;
import poc.springbootexample.models.User.UserOneMany;
import poc.springbootexample.models.User.UserOneManyDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class UserOneManyController {

    @Autowired
    private UserOneManyDao userOneManyDao;

    @Autowired
    private GroupOneManyDao groupOneManyDao;


    @RequestMapping("/createUserOneToMany")
    @ResponseBody
    public ModelAndView create(String email, String name, Role roleVal, Long groupVal) {
        if (groupVal == null) {
            return new ModelAndView("redirect:/addUserOneToMany", "msg", "Please select a group");
        }
        if (name.isEmpty()) {
            return new ModelAndView("redirect:/addUserOneToMany", "msg", "Please enter a name");
        }
        GroupOneMany foundGroupOneMany = groupOneManyDao.findOne(groupVal);
        UserOneMany userOneMany = new UserOneMany(email, name, roleVal, foundGroupOneMany);
        try {
            userOneManyDao.save(userOneMany);
        } catch (Exception e) {
            String msg = "Failed to add user";
            return new ModelAndView("/","msg",msg);
        }
        String msg = "Successfully added user: " + userOneMany.getName();
        return new ModelAndView("redirect:/","msg",msg);
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/deleteUserOneToMany")
    @ResponseBody
    public ModelAndView delete(Long userId) {
        if (userId == null) { return new ModelAndView("redirect:/","msg","Please select a user"); }

        try {
            userOneManyDao.delete(userId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/","msg","Failed to delete user");
        }

        return new ModelAndView("redirect:/","msg","User successfully deleted");
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            UserOneMany userOneMany = userOneManyDao.findByEmail(email);
            userId = String.valueOf(userOneMany.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            UserOneMany userOneMany = userOneManyDao.findOne(id);
            userOneMany.setEmail(email);
            userOneMany.setName(name);
            userOneManyDao.save(userOneMany);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User successfully updated!";
    }


}
