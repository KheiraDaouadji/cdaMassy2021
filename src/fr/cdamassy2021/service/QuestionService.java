package fr.cdamassy2021.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	@Autowired
	QuestionRepository repo;
	
	public void save(Question question) {
		repo.save(question);
	}
	
	public List<Question> listAll() {
		return (List<Question>) repo.findAll();
	}
	
	
}