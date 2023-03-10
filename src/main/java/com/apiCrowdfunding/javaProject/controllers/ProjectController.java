package com.apiCrowdfunding.javaProject.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apiCrowdfunding.javaProject.models.Comment;
import com.apiCrowdfunding.javaProject.models.Compensation;
import com.apiCrowdfunding.javaProject.models.Contribution;
import com.apiCrowdfunding.javaProject.models.Image;
import com.apiCrowdfunding.javaProject.models.Project;
import com.apiCrowdfunding.javaProject.models.User;
import com.apiCrowdfunding.javaProject.services.CommentService;
import com.apiCrowdfunding.javaProject.services.CompensationService;
import com.apiCrowdfunding.javaProject.services.ContributionService;

import com.apiCrowdfunding.javaProject.services.ProjectService;
import com.apiCrowdfunding.javaProject.services.UserService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
    @Autowired
    private ContributionService contributionService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CompensationService compensationService;
	 
	@Autowired
	private UserService userService;
	


	@GetMapping("")
    public ResponseEntity<List<Project> >getAllProjects() {
	
    		return ResponseEntity.ok().body(projectService.getAllProjects());
		
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProject(@PathVariable(value = "id") int id){
		try {
			return ResponseEntity.ok().body(projectService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
 	}
	
	@PostMapping("")
    public ResponseEntity<Project> create(@RequestBody Map<String, Object> projectMap) {
        try {
            Map<String, Object> projectDetails = (Map<String, Object>) projectMap.get("project");
            Integer userId = (Integer) projectDetails.get("userId");
            User user = userService.getById(userId);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Project project = new Project();
            project.setTitle((String) projectDetails.get("title"));
            project.setDescription((String) projectDetails.get("description"));
            project.setGoalAmount((Integer) projectDetails.get("goalAmount"));
            project.setDeadline(LocalDate.parse((String) projectDetails.get("deadline")));
            project.setLaunchDate(LocalDate.parse((String) projectDetails.get("launchDate")));
            project.setImage_presentation((String) projectDetails.get("image_presentation"));
            project.setRate((Integer) projectDetails.get("rate"));
            project.setLink_project_owner((String) projectDetails.get("link_project_owner"));
            project.setUser(user);

            project = projectService.add(project);

            return ResponseEntity.ok(project);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

	@PutMapping("/{id}")
	public ResponseEntity<Project> update(@PathVariable("id") Integer id, @RequestBody Project project) {
 
		Project existingProject = projectService.getById(id);
		User user = userService.getById(existingProject.getUserId());
       
        existingProject.setUser(user);
        existingProject.setTitle(project.getTitle());	
        existingProject.setDescription(project.getDescription());
        existingProject.setGoalAmount(project.getGoalAmount());
        existingProject.setDeadline(project.getDeadline());
        existingProject.setLaunchDate(project.getLaunchDate());
        existingProject.setImage_presentation(project.getImage_presentation());
        existingProject.setRate(project.getRate());
        existingProject.setLink_project_owner(project.getLink_project_owner());
        projectService.update(existingProject);
		return ResponseEntity.ok().body(existingProject);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Project> delete(@PathVariable(value = "id") int id) {
		projectService.deleteById(id);
		return ResponseEntity.ok().body(null);
	}
	
	@PostMapping("/{id}/images")
    public ResponseEntity<Image> addImage(@PathVariable("id") Integer projectId, @RequestParam("file") MultipartFile file) {
		Project project = projectService.getById(projectId);
		Image image = new Image();
		image.setImageUrl(file.getOriginalFilename());
		image.setProject(project);
		project.getImages().add(image);
		project = projectService.update(project);
		return ResponseEntity.ok(image);
    }
	
	@PostMapping("/{id}/contributions")
	public ResponseEntity<Contribution> addContribution(@PathVariable("id") Integer projectId, @RequestBody Contribution contribution) {
		// R??cup??ration du projet correspondant ?? l'id sp??cifi??
		Project project = projectService.getById(projectId);
		Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) currentUser;
		System.out.print(user);
		// Mise ?? jour de la contribution avec le projet correspondant
		contribution.setProject(project);
		// R??cup??ration de l'utilisateur actuellement connect?? pour l'ajouter comme contributeur
		contribution.setUser(user);
		// Enregistrement de la contribution dans la base de donn??es
		contribution = contributionService.add(contribution);
		return ResponseEntity.ok(contribution);
	}

	@PostMapping("/{id}/comments")
	public ResponseEntity<Comment> addComment(@PathVariable("id") Integer projectId, @RequestBody Comment comment) {
		// R??cup??ration du projet correspondant ?? l'id sp??cifi??
		Project project = projectService.getById(projectId);
		Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) currentUser;
		System.out.print(user);
		// Mise ?? jour de la contribution avec le projet correspondant
		comment.setProject(project);
		// R??cup??ration de l'utilisateur actuellement connect?? pour l'ajouter comme
		// contributeur
		comment.setUser(user);
		// Enregistrement de la contribution dans la base de donn??es
		comment = commentService.add(comment);
		return ResponseEntity.ok(comment);
	}

	@PostMapping("/{id}/compensations")
	public ResponseEntity<Compensation> addCompensation(@PathVariable("id") Integer projectId, @RequestBody Compensation compensation) {
		// R??cup??ration du projet correspondant ?? l'id sp??cifi??
		Project project = projectService.getById(projectId);
		// Mise ?? jour de la contribution avec le projet correspondant
		compensation.setProject(project);
		// Enregistrement de la contribution dans la base de donn??es
		compensation = compensationService.add(compensation);
		return ResponseEntity.ok(compensation);
	}

}