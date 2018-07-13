package com.sodifrance.pte.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.NiveauDto;
import com.sodifrance.pte.model.entity.Niveau;

/**
 * Classe de conversion des beans Niveau et NiveauDto.
 */
@Component
public class NiveauTransform {

	public NiveauTransform() {};
	

    /**
     * Converti une entité en dto
     * @param entity l'entité à convertir
     * @return le dto correspondant
     */
    public NiveauDto convertEntityToDto(Niveau entity) {
    	NiveauDto lNiveauDto = new NiveauDto();
        BeanUtils.copyProperties(entity, lNiveauDto);
        return lNiveauDto;
    }
    
    /**
     * Converti une liste d'entités en liste de dto
     * @param lListNiveau la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<NiveauDto> convertListEntityToListDto(List<Niveau> lListNiveau) {
        List<NiveauDto> dtoList = lListNiveau.stream().filter(elt -> elt != null).map(u -> this.convertEntityToDto(u)).collect(Collectors.toList());
        return dtoList;
    }
    
    /**
     * Converti un dto en entité
     * @param dto objet à convertir en entité
     * @return l'entité correspondante
     */
    public Niveau dtoToEntity(NiveauDto pNiveauDto, Niveau pNiveau) {
        if (pNiveauDto != null) {
            BeanUtils.copyProperties(pNiveauDto, pNiveau);
        }
        return pNiveau;
    }
    
    
}
