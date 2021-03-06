package com.thien.controller;

import com.thien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired
    private UserService uig;

    @GetMapping("/dashboard/{username}")
    public String getUserDashboard(){
        return "userdashboard";
    }



    @GetMapping("/dashboard/")
    public String getDashboardRedirect(){
        return "dashboardredirect";
    }

    @PostMapping("/user/{username}")
    @ResponseBody
    public boolean userExists(@PathVariable String username){
        return uig.userExists(username);
    }

    @PostMapping("/user/{username}/playcount")
    @ResponseBody
    public int getPlayCount(@PathVariable String username){
        return uig.getPlayCount(username);
    }

    @PostMapping("/user/{username}/playcount/{game}")
    @ResponseBody
    public int getPlayCountByGame(@PathVariable String username, @PathVariable String game){
        int gameID = Integer.parseInt(game);
        return uig.getPlayCountByGame(username, gameID);
    }

    @PostMapping("/user/{username}/highscore/{game}")
    @ResponseBody
    public int getHighscoreByGame(@PathVariable String username, @PathVariable String game){
        int gameID = Integer.parseInt(game);
        return uig.getHighScoreByGame(username, gameID);
    }

    @PostMapping("/user/{username}/recentscores/{game}")
    @ResponseBody
    public int[] getRecentScoresByGame(@PathVariable String username, @PathVariable String game){
        int gameID = Integer.parseInt(game);
        return uig.getRecentScoresByGame(username, gameID, 10);
    }



    @PostMapping(value = "/user/getusername")
    @ResponseBody
    public String currentUsername(Principal principal) {
        return principal.getName();
    }

}
