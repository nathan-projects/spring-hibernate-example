package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.AdminGroupOneMany;
import poc.springbootexample.models.Group.GroupOneMany;
import poc.springbootexample.models.Group.GroupOneManyDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class GroupOneManyController {

    @Autowired
    private GroupOneManyDao groupOneManyDao;


    @RequestMapping("/createGroupOneToMany")
    @ResponseBody
    public ModelAndView createGroup(String groupName) {
        if (groupName.isEmpty()) {
            return new ModelAndView("redirect:/addGroupOneToMany", "msg", "Please enter a group name");
        }
        GroupOneMany groupOneMany = new AdminGroupOneMany(groupName);
        try {
            groupOneManyDao.save(groupOneMany);
        } catch (Exception ex) {
            String msg = "Failed to add group";
            return new ModelAndView("/","msg",msg);
        }
        String msg = "Successfully added group: " + groupOneMany.getGroupName();
        return new ModelAndView("redirect:/","msg",msg);
    }

    @RequestMapping("/deleteGroup")
    @ResponseBody
    public ModelAndView deleteGroup(Long groupId) {
        if (groupId == null) { return new ModelAndView("redirect:/","msg","Please select a group"); }

        try {
            groupOneManyDao.delete(groupId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/","msg","Failed to delete group");
        }

        return new ModelAndView("redirect:/","msg","Group successfully deleted");
    }


}
