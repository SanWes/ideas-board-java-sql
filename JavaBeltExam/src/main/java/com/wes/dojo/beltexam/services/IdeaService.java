package com.wes.dojo.beltexam.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wes.dojo.beltexam.models.Idea;
import com.wes.dojo.beltexam.repositories.IdeaRepository;





@Service
public class IdeaService {

	 @Autowired
	 private final IdeaRepository ideaRepository;
	 
	 public IdeaRepository getIdeaRepository() {
		 return ideaRepository;
	 }
	 public IdeaService(IdeaRepository ideaRepository) {
		 this.ideaRepository = ideaRepository;
	 }

	 
	 public Idea createIdea(@Valid Idea idea) {
		 return ideaRepository.save(idea);
	 }
	 
	 
	 public List<Idea> allIdeas() {
		 return ideaRepository.findAll();
	 }
	 
	 
	 public Idea getOne(Long id) {
		 Optional<Idea> idea = ideaRepository.findById(id);
		 return idea.isPresent() ? idea.get() : null;
	 }
	 	 
	public void deleteIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		 if(optionalIdea.isPresent()) {
			 this.ideaRepository.deleteById(id);
		 } else {
			  
		 }
		
	}
	 
}
