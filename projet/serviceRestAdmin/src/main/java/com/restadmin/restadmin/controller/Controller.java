package com.restadmin.restadmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restadmin.restadmin.service.FuseeService;
import com.restadmin.restadmin.service.UserService;


import com.restadmin.restadmin.model.Fusee;
import com.restadmin.restadmin.dto.UserDTO;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/restAdmin")
public class Controller {

    private final FuseeService fuseeService;
    private final UserService userService;

    public Controller(FuseeService fuseeService, UserService userService) {
        this.fuseeService = fuseeService;
        this.userService = userService;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDTO> login(@RequestParam String username,
            @RequestParam String password) {
        UserDTO newUserDTO = userService.login(username, password);

        return ResponseEntity.ok().body(newUserDTO);
    }

    @PostMapping(path = "/addFusee")
    public @ResponseBody String addNewFusee(@RequestParam String name) {
        return fuseeService.addFusee(name);
    }

    @DeleteMapping(path = "/demonteFusee")
    public @ResponseBody String demonteFusee(@RequestParam Integer id) {
        return fuseeService.demonteFusee(id);
    }

    @PutMapping(path = "/modifieFusee")
    public @ResponseBody String modifieFusee(@RequestParam Integer id, @RequestParam String name) {
        return fuseeService.modifieFusee(id, name);
    }

    @GetMapping(path = "/getFusee")
    public @ResponseBody Iterable<Fusee> getAllFusees() {
        return fuseeService.getAllFusees();
    }

    @PostMapping(path = "/signin")
    public @ResponseBody String signin(@RequestParam String username, @RequestParam String password) {
        return userService.signin(username, password);
    }

}
