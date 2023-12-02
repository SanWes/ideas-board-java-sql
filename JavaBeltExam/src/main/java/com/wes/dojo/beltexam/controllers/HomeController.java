package com.wes.dojo.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wes.dojo.beltexam.models.Idea;
import com.wes.dojo.beltexam.models.LoginUser;
import com.wes.dojo.beltexam.models.User;
import com.wes.dojo.beltexam.services.IdeaService;
import com.wes.dojo.beltexam.services.UserService;



@Controller
public class HomeController {
	
	    private final UserService userService;
	    private final IdeaService ideaService;
	    
	    public UserService getUserService() {
			return userService;
		}

		public HomeController(UserService userService, IdeaService ideaService) {
	        this.userService = userService;
	        this.ideaService = ideaService;
	    }
	    
		
		// LOGIN AND REGISTRATION 
		
		@GetMapping("/")
		public String index(Model model) {
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}

	    
	    @RequestMapping(value="/registration", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model model) {
	    	// if result has errors, return the registration page (don't worry about validations just now)
	    	// else, save the user in the database, save the user id in session, and redirect them to the /home route
	    	
	    	userService.register(newUser, result);
	        if(result.hasErrors()) {
	            model.addAttribute("newLogin", new LoginUser());
	            return "index.jsp";
	        }
	        session.setAttribute("user_id", newUser.getId());
	        return "redirect:/home";
	    }
	    
	    @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
	    	// if the user is authenticated, save their user id in session
	    	// else, add error messages and return the login page
	    	
	    	User user = userService.login(newLogin, result);
	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "index.jsp";
	        }
	        session.setAttribute("user_id", user.getId());
	        return "redirect:/home";
	    }
	    
	    @RequestMapping("/logout")
	    public String logout(HttpSession session) {
	    	session.invalidate();
	    	return "redirect:/";
	    	// invalidate session
	    	// redirect to login page
	    }
	    
	    
	    // END OF LOGIN AND REGISTRATION
	    
	    
	    
	    // WELCOME / DASHBOARD
	    
	    @RequestMapping("/home")
	    public String home(HttpSession session, Model model) {
	    	if (session.getAttribute("user_id") == null) {
	    		return "redirect:/";
	    	}
	    	Long userId = (Long) session.getAttribute("user_id");
	    	User u = userService.findUserById(userId);
	    	model.addAttribute("user", u);
	    	
	    	model.addAttribute("ideas", ideaService.allIdeas());
	    	
	    	
			return "home.jsp";
	        // get user from session, save them in the model and return the home page
	    }
	    
	    
	    // NEW PAGE 
	    
	    @GetMapping("/ideas/new")
	    public String newIdea(@ModelAttribute("idea")Idea idea, HttpSession session, Model model) {
	    	if (session.getAttribute("user_id") == null) {
	    		return "redirect:/";
	    	}
	    	Long userId = (Long) session.getAttribute("user_id");
	    	User u = userService.findUserById(userId);
	    	model.addAttribute("user", u);
	    	return "newIdea.jsp";
	    }
	
	     // POST FORM Idea
		 @PostMapping(value="/postidea")
		 public String createIdea(@Valid @ModelAttribute("idea")Idea idea, BindingResult result) {
			 if (result.hasErrors()) {
				 //errors on form will return to same page with form
				 return "newIdea.jsp";
			 } else {
				 // successful form will create and save
				 ideaService.createIdea(idea);
				 // redirect page with all
				 return "redirect:/home";
				 }
			 }
	    
	    
		 // UPDATE IDEA ROUTES 
		 
		 @GetMapping("/ideas/{id}/edit")
		    public String editIdea(@PathVariable("id")Long idea_id, @ModelAttribute("idea")Idea idea, HttpSession session, Model model) {
			 if (session.getAttribute("user_id") == null) {
		    		return "redirect:/";
		    	}
//			 if (session.getAttribute("user_id") != ideaService.getOne(idea_id)) {
//		    		return "redirect:/home";
//		    	}
		    	Long userId = (Long) session.getAttribute("user_id");
		    	User u = userService.findUserById(userId);
		    	model.addAttribute("user", u);
		    	
		    	Idea thisIdea = ideaService.getOne(idea_id);
		    	model.addAttribute("idea", thisIdea);
		    	
		    	return "updateIdea.jsp";
		    }
		 
		
		    @RequestMapping("/update/{id}")
		    public String update(@Valid @ModelAttribute("idea")Idea idea, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		    	if (session.getAttribute("user_id") == null) {
		    		return "redirect:/";
		    	}
		    	if (result.hasErrors()) {
		        	model.addAttribute("idea", idea);
		    		return "updateIdea.jsp";
		    	} else {
		    		ideaService.createIdea(idea);
		    		return "redirect:/home";
		    	}
		    }
		 
		  // DELETE IDEA ROUTE 
		   @RequestMapping("/delete/idea/{id}")
		   public String deleteIdea(@PathVariable("id")Long id, HttpSession session) {
			   if (session.getAttribute("user_id") == null) {
		    		return "redirect:/";
		    	}
			   Idea thisIdea = ideaService.getOne(id);
			   //Black Belt Feature : Protected Deletion 
			   User thisUser = userService.findUserById((Long) session.getAttribute("user_id"));
			   if(thisUser.getId() != thisIdea.getUser().getId()) {
				   return "redirect:/home";
			   }
			    this.ideaService.deleteIdea(id);
			   
			   return "redirect:/home";
		   }
		   
		   
		   // DISPLAY ONE IDEA 
		   
		   @GetMapping("/ideas/{id}")
		   public String show(@PathVariable("id")Long idea_id, HttpSession session, Model model) {
			   if (session.getAttribute("user_id") == null) {
		    		return "redirect:/";
		    	}
			   Idea thisIdea = ideaService.getOne(idea_id);
		    	model.addAttribute("idea", thisIdea);
		    	
			   
			   model.addAttribute("idea", thisIdea);
			   return "show.jsp";
			   
		   }
		   
	    
}  // end controller
