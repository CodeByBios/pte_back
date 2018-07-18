package com.sodifrance.pte.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.CreateTestDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;

@Component
public class TestTransform {
	
	@Autowired
	private QuestionTransform questionTransform;
	
	@Autowired
	private CandidatTransform candidatTransform;
	
	

	
    /**
     * Converti une liste d'entités en liste de dto
     * @param pListQuestion, pCandidat la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<CreateTestDto> convertListEntityToListDto(List<Question> pListQuestion, Candidat pCandidat) {
    	
    	List<CreateTestDto> lCreateListTestDto = new ArrayList<CreateTestDto>();
    	//pQuestionDto.setLangageDto(langageTransform.convertListEntityToListDto(lQuestion.getLangages()));
    	
    	lCreateListTestDto.forEach((lQuestionsCandidatDto) ->{
    		
    		lQuestionsCandidatDto.setCandidatDto(candidatTransform.convertToDto(pCandidat));
    		lQuestionsCandidatDto.setQuestionsDto(questionTransform.listEntityToListDto(pListQuestion));
    		
    		lCreateListTestDto.add(lQuestionsCandidatDto);
    	});
    	
        return lCreateListTestDto;
    }

}
