package org.launchcode.patentinvestor.controllers;

import org.launchcode.patentinvestor.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kamdem
 * Main page of the application
 */
@Controller
public class HomeController {

    public static final String BASE_URL = "http://localhost:8080/";

    //General messages for the application
    public static final String NOT_LOGGED_IN_MSG = "You are not logged in and may not perform this operation! " +
            "<a title=\"click here to log in and access full features\" href=\"" + BASE_URL + "login\">Log in now</a>";

    public static final String NOT_TAG_MSG = "It seems you do not have sufficient Investment Fields! " +
            "<a title=\"click here to create a new Investment Field\" href=\"" + BASE_URL + "tags/create\">create a new Investment Field now</a>";

    public static final String PORTFOLIO_LINK_IN_MSG = "<a title=\"click here to access portfolio\" href=\"" +
            BASE_URL + "stocks/portfolio\">portfolio</a>";

    @Autowired
    AuthenticationController authenticationController;

    @RequestMapping(value = "")
    public String index(
            Model model,
            HttpServletRequest request) {

        User loggedInUser = authenticationController.getUserFromSession(request.getSession());
        if (loggedInUser != null) {//user is logged in
            model.addAttribute("isLoggedIn", true);
        }

        Map<String, String> actionChoices = new LinkedHashMap<>();
        actionChoices.put("stocks/search", "Search stock");
        actionChoices.put("stocks", "All Stocks");
        actionChoices.put("tags", "All investment fields");
        actionChoices.put("stocks/portfolio", "Portfolio");

        model.addAttribute("actions", actionChoices);
        model.addAttribute("title", "Home");

        return "index";
    }
}
