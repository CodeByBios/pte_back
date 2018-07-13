package com.sodifrance.pte.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.TypeQuestionDto;
import com.sodifrance.pte.model.entity.TypeQuestion;

/**
 * Classe de conversion des beans Niveau et NiveauDto.
 */
@Component
public class TypeQuestionTransform {

	public TypeQuestionTransform() {};
	

    /**
     * Converti une entité en dto
     * @param entity l'entité à convertir
     * @return le dto correspondant
     */
    public TypeQuestionDto convertEntityToDto(TypeQuestion pTypeQuestion) {
    	TypeQuestionDto lTypeQuestionDto = new TypeQuestionDto();
        BeanUtils.copyProperties(pTypeQuestion, lTypeQuestionDto);
        return lTypeQuestionDto;
    }
    
    /**
     * Converti une liste d'entités en liste de dto
     * @param entityList la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<TypeQuestionDto> convertListEntityToListDto(List<TypeQuestion> pListTypeQuestion) {
        List<TypeQuestionDto> dtoList = pListTypeQuestion.stream().filter(elt -> elt != null).map(u -> this.convertEntityToDto(u)).collect(Collectors.toList());
        return dtoList;
    }
    
    /**
     * Converti un dto en entité
     * @param TypeQuestionDto objet à convertir en entité pTypeQuestion
     * @return l'entité correspondante
     */
    public TypeQuestion dtoToEntity(TypeQuestionDto TypeQuestionDto, TypeQuestion pTypeQuestion) {
        if (TypeQuestionDto != null) {
            BeanUtils.copyProperties(TypeQuestionDto, pTypeQuestion);
        }
        return pTypeQuestion;
    }
    
    
}
