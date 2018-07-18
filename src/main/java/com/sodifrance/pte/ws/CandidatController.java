package com.sodifrance.pte.ws;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.commun.utils.PteCollectionUtils;
import com.sodifrance.pte.model.dto.CandidatDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.transform.CandidatTransform;
import com.sodifrance.pte.validator.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
public class CandidatController {
	
	private static final String PATH_CANDIDAT = "/v1/candidats";
	
	@Autowired
	private CandidatService candidatService;
	
	@Autowired
	private CandidatTransform candidatTransform;
	
	
	/**
     * Execute les validateurs sur le dto.<br>
     * Converti le dto en Entity.
     *
     * @param pCandidatDto la question dto
     * @return la Candidat entity
     */
    Candidat validateAndTransform(CandidatDto pCandidatDto) {
        // Validation du DTO
        DtoValidator.validateDto(pCandidatDto);

        // Conversion du DTO en Entity
        return candidatTransform.convertWithoutQuestionsToEntity(pCandidatDto);
    }
	
    /**
     * Création du candidat
     * @param pCandidatDto
     * @return
     * @throws Exception
     */
	@PostMapping(value = PATH_CANDIDAT + "/{idUtilisateur}")
	public CandidatDto createCandidat(@PathVariable Long idUtilisateur, @RequestBody CandidatDto pCandidatDto) throws Exception {
		log.debug("Creation du candidat : {}.", pCandidatDto);

		//Candidat lCandidat = validateAndTransform(pCandidatDto);
		Candidat lCandidat = candidatTransform.convertWithoutQuestionsToEntity(pCandidatDto);
		//Creation du candidat
		lCandidat = candidatService.createCandidtat(idUtilisateur, lCandidat);
		
		return candidatTransform.convertWithoutQuestionsToDto(lCandidat);
	}
	
	/**
	 * Récupération de tous les candidats
	 * @return List candidats
	 * @throws Exception
	 */
	@GetMapping(value = PATH_CANDIDAT)
	public List<CandidatDto> getCandidats() throws Exception {
		log.debug("Récupération des candidats");
		return PteCollectionUtils.transformCollectionToList(candidatService.getAllCandidats(), candidat -> candidatTransform.convertToDto(candidat));
	}
	
	/**
	 * Récupération d'un candidat
	 * @return Candidats
	 * @throws Exception
	 */
	@GetMapping(value = PATH_CANDIDAT + "/{idCandidat}")
	public CandidatDto getCandidat(@PathVariable Long idCandidat) throws Exception {
		log.debug("Récupération du candidat {}", idCandidat);
		Optional<Candidat> lCandidat = candidatService.findCandidatById(idCandidat);
		if(lCandidat.isPresent())
			return candidatTransform.convertToDto(lCandidat.get());
		else throw new Exception("Le candidat n'existe pas");
	}
	
	@PutMapping(value = PATH_CANDIDAT)
	public CandidatDto updateCandidat(@RequestBody CandidatDto pCandidatDto) throws Exception {
		log.debug("Mise à jour de la note du : candidat {}.", pCandidatDto);

		Candidat lCandidat = validateAndTransform(pCandidatDto);
		//Mise à jour de la note dans candidat
		lCandidat = candidatService.saveNoteCandidat(lCandidat);
		
		return candidatTransform.convertToDto(lCandidat);
	}
	
	
}
