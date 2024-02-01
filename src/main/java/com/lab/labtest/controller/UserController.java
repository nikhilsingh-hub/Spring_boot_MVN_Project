package com.lab.labtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.labtest.entity.UserEntity;
import com.lab.labtest.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String sendHomePage(Model model) {
        return "index";
    }

    @GetMapping("/searchCandidatesPage")
    public String sendSearchPage(Model model) {

        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/addNewCandidateForm")
    public String sendCandidateForm(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("heading", "Add User Form");
        model.addAttribute("candidate", userEntity);
        return "details";
    }

    @GetMapping("/deleteCandidate/{id}")
    public String deleteCandidate(@PathVariable(value = "id") long id){
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

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        
        int pageSize = 5;
        Page<UserEntity> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<UserEntity> listCandidates = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("candidates", listCandidates);
        return "search";
    }

}
