package com.wes.dojo.beltexam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="ideas")
public class Idea {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@NotEmpty(message="Content is required!")
@Size(min=3, max=300, message="Content must be between 3 and 300 characters")
private String content;


@Column(updatable=false)
@DateTimeFormat(pattern="yyyy-MM-dd")
 private Date createdAt;
@DateTimeFormat(pattern="yyyy-MM-dd")
 private Date updatedAt;

@PrePersist
protected void onCreate(){
	this.createdAt = new Date();
}


@PreUpdate
protected void onUpdate(){
	this.updatedAt = new Date();
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="user_id")
private User user;


public Idea() {
	
}


// ...
// getters and setters
// ...

public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}



public String getContent() {
	return content;
}


public void setContent(String content) {
	this.content = content;
}


public Date getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}


public Date getUpdatedAt() {
	return updatedAt;
}


public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}


public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


}