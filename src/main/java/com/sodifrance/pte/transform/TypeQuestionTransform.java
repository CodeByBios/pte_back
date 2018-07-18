package com.sodifrance.pte.transform;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.TypeQuestionDto;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.model.entity.Utilisateur;

/**
 * Classe de conversion des beans Niveau et NiveauDto.
 */
@Component
public class TypeQuestionTransform {

	public TypeQuestionTransform() {};
	
	
	 @PostConstruct
	    public void init() {
	    	
	    	ModelMapper modelMapper = new ModelMapper();
	    	
	        // Pour la transformation TypeQuestion -> TypeQuestionDto
	        modelMapper.addMappings(new PropertyMap<TypeQuestion, TypeQuestionDto>() {
	            @Override
	            protected void configure() {
	            }
	        });
	    }
	 
	 
	 
	 /**
		 * Converti un dto en entité
		 *
		 * @param pUtilisateurtDto
		 *            objet à convertir en entité
		 * @return un Utilisateur
		 */
		public TypeQuestion convertDtoToEntity(TypeQuestionDto pTypeQuestionDto) {
			ModelMapper modelMapper = new ModelMapper();
			TypeQuestion lTypeQuestion = modelMapper.map(pTypeQuestionDto, TypeQuestion.class);
			
			return lTypeQuestion;
		}
		
		/**
		 * Converti une entité en dto
		 *
		 * @param user
		 *            l'entité à convertir
		 * @return le dto correspondant
		 */
		public TypeQuestionDto convertToDto(TypeQuestion pTypeQuestion) {
			ModelMapper modelMapper = new ModelMapper();
			TypeQuestionDto lTypeQuestionDto = modelMapper.map(pTypeQuestion, TypeQuestionDto.class);
			
			return lTypeQuestionDto;
		}
	 
	 
	 

    /**
     * Converti une entité en dto
     * @param entity l'entité à convertir
     * @return le dto correspondant
     */
    /*public TypeQuestionDto convertEntityToDto(TypeQuestion pTypeQuestion) {
    	TypeQuestionDto lTypeQuestionDto = new TypeQuestionDto();
        BeanUtils.copyProperties(pTypeQuestion, lTypeQuestionDto);
        return lTypeQuestionDto;
    }*/
    
    /**
     * Converti une liste d'entités en liste de dto
     * @param entityList la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    /*public List<TypeQuestionDto> convertListEntityToListDto(List<TypeQuestion> pListTypeQuestion) {
        List<TypeQuestionDto> dtoList = pListTypeQuestion.stream().filter(elt -> elt != null).map(u -> this.convertEntityToDto(u)).collect(Collectors.toList());
        return dtoList;
    }*/
    
    /**
     * Converti un dto en entité
     * @param TypeQuestionDto objet à convertir en entité pTypeQuestion
     * @return l'entité correspondante
     */
    /*public TypeQuestion dtoToEntity(TypeQuestionDto TypeQuestionDto, TypeQuestion pTypeQuestion) {
        if (TypeQuestionDto != null) {
            BeanUtils.copyProperties(TypeQuestionDto, pTypeQuestion);
        }
        return pTypeQuestion;
    }*/
    
    
}
