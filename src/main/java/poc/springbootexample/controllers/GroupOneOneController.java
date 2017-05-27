package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.GroupOneOne;
import poc.springbootexample.models.Group.GroupOneOneDao;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class GroupOneOneController {

    @Autowired
    private GroupOneOneDao groupDao;


    @RequestMapping("/createGroupOneToOne")
    @ResponseBody
    public ModelAndView createGroupOneToOne(String groupName) {
        if (groupName.isEmpty()) {
            return new ModelAndView("redirect:/addGroupOneToOne", "msg", "Please enter a group name");
        }
        GroupOneOne group = new GroupOneOne(groupName);
        try {
            groupDao.save(group);
        } catch (Exception ex) {
            String msg = "Failed to add group";
            return new ModelAndView("/OneToOne","msg",msg);
        }
        String msg = "Successfully added group: " + group.getGroupName();
        return new ModelAndView("redirect:/OneToOne","msg",msg);
    }

    @RequestMapping("/deleteGroupOneToOne")
    @ResponseBody
    public ModelAndView deleteGroupOneToOne(Long groupId) {
        if (groupId == null) { return new ModelAndView("redirect:/OneToOne","msg","Please select a group"); }

        try {
            groupDao.delete(groupId);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ModelAndView("redirect:/OneToOne","msg","Failed to delete group");
        }

        return new ModelAndView("redirect:/OneToOne","msg","Group successfully deleted");
    }


}
