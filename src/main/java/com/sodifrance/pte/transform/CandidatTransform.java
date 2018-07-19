package com.sodifrance.pte.transform;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.CandidatDto;
import com.sodifrance.pte.model.entity.Candidat;

/**
 * Classe de conversion des beans Candidat et CandidatDto.
 */
@Component
public class CandidatTransform {
	
	@Autowired
	private UtilisateurTransform utilisateurTransform;
	
	@Autowired
	private QuestionTransform questionTransform;

    @PostConstruct
    public void init() {
    	
    	ModelMapper modelMapper = new ModelMapper();
    	
        // Pour la transformation Candidat -> CandidatDto
        modelMapper.addMappings(new PropertyMap<Candidat, CandidatDto>() {
            @Override
            protected void configure() {
            }
        });
    }
    
    
	/**
	 * Converti un dto en entité
	 *
	 * @param pCandidatDto
	 *            objet à convertir en entité
	 * @return un Candidat
	 */
	public Candidat convertToEntity(CandidatDto pCandidatDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		Candidat lCandidat = modelMapper.map(pCandidatDto, Candidat.class);
		lCandidat.setUtlisateur(utilisateurTransform.convertToEntity(pCandidatDto.getUtilisateurDto()));
		lCandidat.setQuestions(questionTransform.convertToListEntity(pCandidatDto.getQuestionDto()));

		return lCandidat;
	}
	
	/**
	 * Converti un dto en entité
	 *
	 * @param pCandidatDto
	 *            objet à convertir en entité
	 * @return un Candidat
	 */
	public Candidat convertWithoutQuestionsToEntity(CandidatDto pCandidatDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		//Candidat lCandidat = modelMapper.map(pCandidatDto, Candidat.class);
		Candidat lCandidat = new Candidat();
		lCandidat.setDate(pCandidatDto.getDate());
		lCandidat.setNom(pCandidatDto.getNom());
		lCandidat.setPrenom(pCandidatDto.getPrenom());
		lCandidat.setTemps(pCandidatDto.getTemps());
		lCandidat.setUtlisateur(utilisateurTransform.convertToEntity(pCandidatDto.getUtilisateurDto()));
		//lCandidat.setQuestions(questionTransform.convertToListEntity(pCandidatDto.getQuestionDto()));

		return lCandidat;
	}
	
	/**
	 * Converti une entité en dto
	 *
	 * @param pCandidat
	 *            l'entité à convertir
	 * @return le dto correspondant
	 */
	public CandidatDto convertToDto(Candidat pCandidat) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		CandidatDto lCandidatDto = modelMapper.map(pCandidat, CandidatDto.class);
		lCandidatDto.setUtilisateurDto(utilisateurTransform.convertToDto(pCandidat.getUtlisateur()));
		lCandidatDto.setQuestionDto(questionTransform.listEntityToListDto(pCandidat.getQuestions())); 
		
		return lCandidatDto;
	}
	
	/**
	 * Converti une entité en dto
	 *
	 * @param pCandidat
	 *            l'entité à convertir sans les questions
	 * @return le dto correspondant
	 */
	public CandidatDto convertWithoutQuestionsToDto(Candidat pCandidat) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		//CandidatDto lCandidatDto = modelMapper.map(pCandidat, CandidatDto.class);
		CandidatDto lCandidatDto = new CandidatDto();
		lCandidatDto.setId(pCandidat.getId());
		lCandidatDto.setDate(pCandidat.getDate());
		lCandidatDto.setNom(pCandidat.getNom());
		lCandidatDto.setPrenom(pCandidat.getPrenom());
		lCandidatDto.setTemps(pCandidat.getTemps());
		lCandidatDto.setUtilisateurDto(utilisateurTransform.convertToDto(pCandidat.getUtlisateur()));
		//lCandidatDto.setQuestionDto(questionTransform.listEntityToListDto(pCandidat.getQuestions())); 
		
		return lCandidatDto;
	}
    
}
