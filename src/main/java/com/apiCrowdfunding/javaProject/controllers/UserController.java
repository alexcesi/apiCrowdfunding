package com.apiCrowdfunding.javaProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiCrowdfunding.javaProject.models.Role;
import com.apiCrowdfunding.javaProject.models.User;
import com.apiCrowdfunding.javaProject.services.RoleService;
import com.apiCrowdfunding.javaProject.services.UserService;

@RestController
@RequestMapping("/api/users")	
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
    private RoleService roleService;
	
	@GetMapping("")
    public ResponseEntity<List<User> >getAllUsers() {
    	try {
    		return ResponseEntity.ok().body(userService.getAllUsers());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
    }
	
    @GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value = "id") int id){
		try {
			return ResponseEntity.ok().body(userService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
    
    @PostMapping("")
	public ResponseEntity<User> create(@RequestBody User user) {
    	Integer roleId = user.getRole().getId();
        Role role = roleService.findById(roleId);
        user.setRole(role);
		 try {
			 user = userService.add(user);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
    
    @PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Integer id, @RequestBody User user) {
    	try {
    	  User currentUser = userService.getById(id);
    	  Integer roleId = user.getRole().getId();
          Role role = roleService.findById(roleId);
          user.setRole(role);
    	  if (currentUser == null) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	 	currentUser.setUsername(user.getUsername());	
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());
        currentUser.setRole(user.getRole());
        currentUser.setProjectOwner(user.isProjectOwner());
        userService.update(currentUser);
		return ResponseEntity.ok().body(currentUser);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable(value = "id") int id) {
    	userService.deleteById(id);
		return ResponseEntity.ok().body(null);
	}
}
