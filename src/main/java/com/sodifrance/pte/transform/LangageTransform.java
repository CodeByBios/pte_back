package com.sodifrance.pte.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.model.dto.LangageDto;
import com.sodifrance.pte.model.dto.NiveauDto;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;

/**
 * Classe de conversion des beans Niveau et NiveauDto.
 */
@Component
public class LangageTransform {

	public LangageTransform() {};
	

    /**
     * Converti une entité en dto
     * @param pLangage l'entité à convertir
     * @return le dto correspondant
     */
    public LangageDto convertEntityToDto(Langage pLangage) {
    	LangageDto lLangageDto = new LangageDto();
        BeanUtils.copyProperties(pLangage, lLangageDto);
        return lLangageDto;
    }
    
    /**
     * Converti une liste d'entités en liste de dto
     * @param pListLangage la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<LangageDto> convertListEntityToListDto(List<Langage> pListLangage) {
        List<LangageDto> lListLangage = pListLangage.stream().filter(elt -> elt != null).map(u -> this.convertEntityToDto(u)).collect(Collectors.toList());
        return lListLangage;
    }
    
    /**
     * Converti un dto en entité
     * @param pLangageDto objet à convertir en entité
     * @return l'entité correspondante
     */
    public Langage dtoToEntity(NiveauDto pLangageDto, Langage pLangage) {
        if (pLangageDto != null) {
            BeanUtils.copyProperties(pLangageDto, pLangage);
        }
        return pLangage;
    }
    
    
}
