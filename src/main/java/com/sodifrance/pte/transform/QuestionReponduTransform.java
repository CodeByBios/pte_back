package com.sodifrance.pte.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.QuestionReponduDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.QuestionRepondu;
import com.sodifrance.pte.model.entity.ReponseCandidat;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.service.QuestionReponduService;
import com.sodifrance.pte.service.QuestionService;

@Component
public class QuestionReponduTransform {
	
	@Autowired
	private QuestionReponduService questionReponduService;
	
	@Autowired
	private CandidatService candidatService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ReponseCandidatTransform reponseCandidatTransform;
	

	public QuestionRepondu convertToEntity(QuestionReponduDto pQuestionReponduDto) {
		QuestionRepondu rep = null;
		List<ReponseCandidat> reponsesCandidat = new ArrayList<ReponseCandidat>();
		
		if(pQuestionReponduDto != null) {
			if(pQuestionReponduDto.getId() != null) {
				
				QuestionRepondu questionRepondu = questionReponduService.findQuestionById(pQuestionReponduDto.getId()).get();
				rep = questionRepondu;
				
			}else {
				QuestionRepondu questionRepondu = new QuestionRepondu();
				Candidat candidat = candidatService.findCandidatById(pQuestionReponduDto.getIdCandidat()).get();
				Question question = questionService.findQuestionById(pQuestionReponduDto.getIdQuestion()).get();
		
				questionRepondu.setCandidat(candidat);
				questionRepondu.setQuestion(question);
				
				pQuestionReponduDto.getReponseCandidatDto().forEach(reponse -> {
					ReponseCandidat reponseCandidat = reponseCandidatTransform.convertToEntity(reponse);
				    reponsesCandidat.add(reponseCandidat);
				});
				
				questionRepondu.setReponsesCandidat(reponsesCandidat);
				rep = questionRepondu;
			}
			
		}
		
		return rep;
	}
}
