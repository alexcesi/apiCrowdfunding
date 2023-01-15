package com.apiCrowdfunding.javaProject.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  
  @Column(name = "title", nullable = false)
  private String title;
  
  @Column(name = "description", nullable = false)
  private String description;
  
  @Column(name = "goal_amount", nullable = false)
  private Integer goalAmount;

  @Column(name = "deadline", nullable = false)
  private LocalDate deadline;
  
  @Column(name = "launch_date", nullable = false)
  private LocalDate launchDate;
  
  @Column(name = "image_presentation", nullable = false)
  private String image_presentation;

  @Column(name = "rate", nullable = false)
  private Integer rate;
  
  @Column(name = "link_project_owner", nullable = false)
  private String link_project_owner;

  @ManyToOne(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @JsonIgnore
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Image> images;
	  
  public Project() {
  }

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
		}
		
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getGoalAmount() {
		return goalAmount;
	}
	
	public void setGoalAmount(Integer goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public LocalDate getDeadline() {
		return deadline;
	}
	
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	
	public LocalDate getLaunchDate() {
		return launchDate;
	}
	
	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}
	
	public String getImage_presentation() {
		return image_presentation;
	}
	
	public void setImage_presentation(String image_presentation) {
		this.image_presentation = image_presentation;
	}
	
	public Integer getRate() {
		return rate;
	}
	
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	public String getLink_project_owner() {
		return link_project_owner;
	}
	
	public void setLink_project_owner(String link_project_owner) {
		this.link_project_owner = link_project_owner;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	 
	public Integer getUserId() {
	    return this.user.getId();
	}
	
	  
    public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	 
}