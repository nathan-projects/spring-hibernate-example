package poc.springbootexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import poc.springbootexample.models.Group.GroupOneMany;
import poc.springbootexample.models.Group.GroupOneManyDao;
import poc.springbootexample.models.User.UserOneMany;
import poc.springbootexample.models.User.UserOneManyDao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by norner on 11/03/2017.
 */

@Controller
public class IndexOneManyController {
    //for navigation to different page

    @Autowired
    private UserOneManyDao userOneManyDao;

    @Autowired
    private GroupOneManyDao groupOneManyDao;

    @RequestMapping("/")
    public ModelAndView home(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, String> msgMap = new HashMap<>();
        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<UserOneMany> users = userOneManyDao.findAll();
        model.put("users", users);

        Iterable<GroupOneMany> groups = groupOneManyDao.findAll();
        model.put("groups", groups);

        return new ModelAndView("homeOneMany","model",model);
    }

    @RequestMapping("/addUserOneToMany")
    public ModelAndView addUser(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        Iterable<GroupOneMany> groups = groupOneManyDao.findAll();
        model.put("groups", groups);

        return new ModelAndView("addUserOneToMany","model",model);
    }

    @RequestMapping("/addGroupOneToMany")
    public ModelAndView addGroup(@RequestParam(value = "msg", required = false) String msg) {
        Map<String, Object> model = new HashMap<String, Object>();

        if (msg != null) {
            model.put("msg", msg);
        }

        return new ModelAndView("addGroupOneToMany", "model", model);
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath()
                + "?sslmode=require";
        return DriverManager.getConnection(dbUrl, username, password);
    }



}
