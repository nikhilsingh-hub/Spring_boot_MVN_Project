package com.lab.labtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.labtest.entity.UserEntity;
import com.lab.labtest.service.UserService;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String sendLoginPage(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("admin", userEntity);
        return "login";
    }

    @GetMapping("/handleLogin")
    public String handleLogin(@ModelAttribute("admin") UserEntity userEntity, Model model) {
        UserEntity myAdmin = userService.checkCredentials(userEntity);
        if(myAdmin != null)
        {   model.addAttribute("admin", myAdmin);
            return  "index";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/getHomePage")
    public String getHomePage(@RequestParam(value = "name") String name, Model model) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        model.addAttribute("admin", userEntity);
        return  "index";
    }

    @GetMapping("/searchCandidatesPage")
    public String sendSearchPage(Model model) {

        List<UserEntity> candidates = userService.getAllUsers();
        model.addAttribute("candidates", candidates);
        return "search";
    }

    @GetMapping("/addNewCandidateForm")
    public String sendCandidateForm(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("heading", "Add User Form");
        model.addAttribute("candidate", userEntity);
        return "details";
    }

    @GetMapping("/deleteCandidate/{id}")
    public String deleteCandidate(@PathVariable(value = "id") long id) {
        try {
            userService.deleteUserById(id);
            return "redirect:/searchCandidatesPage";
        } catch (Exception e) {
            return "Error deleting candidate: " + e.getMessage();
        }
    }

    @PostMapping("/saveCandidate")
    public String saveCandidate(@ModelAttribute("candidate") UserEntity userEntity) {
        try {
            userService.saveUser(userEntity);
            return "redirect:/searchCandidatesPage";
        } catch (Exception e) {
            return "Error saving candidate: " + e.getMessage();
        }
    }

    @GetMapping("/updateCandidateForm/{id}")
    public String sendUpdateCandidateForm(@PathVariable(value = "id") long id, Model model) {

        UserEntity userEntity = userService.getUserById(id);
        model.addAttribute("heading", "Edit User Form");
        model.addAttribute("candidate", userEntity);
        return "details";
    }
    @GetMapping("/logout")
    public String logoutRoute() {
        return "redirect:/";
    }
    

}
