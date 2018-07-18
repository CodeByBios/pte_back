package com.sodifrance.pte.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.ReponseDto;
import com.sodifrance.pte.model.entity.Reponse;

@Component
public class ReponseTransform {
	
	
	
	public ReponseTransform() {} ;
	/**
     * Converti une entité en dto
     * @param pReponse l'entité à convertir
     * @return le dto correspondant
     */
    public ReponseDto convertEntityToDto(Reponse pReponse) {
    	ReponseDto lReponseDto = new ReponseDto();
        BeanUtils.copyProperties(pReponse, lReponseDto);
        return lReponseDto;
    }
    
    /**
     * Converti une liste d'entités en liste de dto
     * @param lListReponse la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<ReponseDto> convertListEntityToListDto(List<Reponse> lListReponse) {
        List<ReponseDto> dtoList = lListReponse.stream().filter(elt -> elt != null).map(u -> this.convertEntityToDto(u)).collect(Collectors.toList());
        return dtoList;
    }
    
    /**
     * Converti un dto en entité
     * @param dto objet à convertir en entité
     * @return l'entité correspondante
     */
    public Reponse dtoToEntity(ReponseDto pReponseDto, Reponse pReponse) {
        if (pReponseDto != null) {
            BeanUtils.copyProperties(pReponseDto, pReponse);
        }
        return pReponse;
    }

}
