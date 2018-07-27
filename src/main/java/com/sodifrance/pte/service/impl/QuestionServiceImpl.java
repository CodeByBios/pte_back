package com.sodifrance.pte.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.commun.utils.PteAbstractConstants;
import com.sodifrance.pte.dao.CandidatDao;
import com.sodifrance.pte.dao.QuestionDao;
import com.sodifrance.pte.dao.ReponseDao;
import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.dto.InfoQuestionDto;
import com.sodifrance.pte.model.dto.ResumeQuestionDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.LangageService;
import com.sodifrance.pte.service.QuestionService;
import com.sodifrance.pte.service.ReponseService;

import ch.qos.logback.core.joran.action.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("questionService")
public class QuestionServiceImpl extends AbstractServiceImpl<Question> implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private ReponseService reponseService;

	@Autowired
	private LangageService langageService;

	@Autowired
	private ReponseDao reponseDao;

	@Autowired
	private CandidatDao candidatDao;

	@Override
	protected JpaRepository<Question, Long> getEntityDao() {
		return questionDao;
	}

	@Override
	public Optional<Question> findQuestionById(Long id) {
		return questionDao.findById(id);
	}

	/**
	 * Convert update dto
	 * 
	 * @param pQuestionUpdate
	 *            : les nouvelles données de la question
	 * @return La question à enregistrer
	 */
	private Question initUpdateQuestion(Question pQuestionUpdate) {

		Question lQuestion = findQuestionById(pQuestionUpdate.getId()).get();

		if (lQuestion != null) {

			if (pQuestionUpdate.getEtat() != null) {
				if (lQuestion.getEtat().equals(Boolean.TRUE)) {
					lQuestion.setEtat(pQuestionUpdate.getEtat());
				} else {
					lQuestion.setEtat(pQuestionUpdate.getEtat());
					lQuestion.setLibelle(pQuestionUpdate.getLibelle());
					lQuestion.setLangages(pQuestionUpdate.getLangages());
					lQuestion.setNiveaux(pQuestionUpdate.getNiveaux());
					lQuestion.setTypeQuestion(pQuestionUpdate.getTypeQuestion());
					lQuestion.setReponses(pQuestionUpdate.getReponses());
				}
			}
		} else
			throw new PteParametersException("La question n'esxiste pas");

		return lQuestion;
	}

	/**
	 * Créationn de la Question avec les Réponses
	 * 
	 * @param pQuestion
	 * @return La question à enregistrer
	 */
	@Override
	public Question createQuestion(Question pQuestion) {

		pQuestion.getReponses().forEach(reponse -> {
			reponseService.createReponse(reponse);
		});

		return super.createEntity(pQuestion);
	}

	/**
	 * Modification de la Question avec les Réponses
	 * 
	 * @param pQuestion
	 * @return La question à enregistrer avec les réponses
	 */
	@Override
	public Question updateQuestion(Question pQuestion) {

		Question lQuestionUpdate = initUpdateQuestion(pQuestion);

		lQuestionUpdate.getReponses().forEach(updateReponse -> {
			
			if(updateReponse.getId() != null) {
				reponseService.updateReponse(updateReponse);
			}else {
				reponseService.createReponse(updateReponse);
			}
			
		});

		super.updateEntity(lQuestionUpdate);

		return lQuestionUpdate;

	}

	@Override
	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	@Override
	public List<Question> getAllQuestionByNiveauxAndLangagesAndTypeQuestion(Niveau pNiveau, Langage pLangage,
			TypeQuestion pTypeQuestion) {
		return questionDao.findByNiveauxAndLangagesAndTypeQuestion(pNiveau, pLangage, pTypeQuestion);
	}

	@Override
	public List<Question> getAllQuestionsActives(Boolean actif) {
		List<Question> lListQuestions = this.getAllQuestions();
		if (actif == null) {
			return lListQuestions;
		} else {
			return lListQuestions.stream().filter(quest -> actif ? quest.getEtat() : !quest.getEtat())
					.collect(Collectors.toList());
		}
	}

	/**
	 * Suppression d'une question non utilisée par un candidat
	 * 
	 * @param pQuestion
	 * @return
	 */
	@Override
	public void deleteQuestion(Long pIdQuestion) {
		Question lQuestion = findQuestionById(pIdQuestion).get();

		if (lQuestion.getId() != null) {
			List<Candidat> lListCandidat = candidatDao.findAll();
			Set<Question> lListQuestionCandidat = new HashSet<Question>();

			lListCandidat.forEach(candit ->{
				lListQuestionCandidat.addAll(candit.getQuestions());
			});
			
			if(lListQuestionCandidat.contains(lQuestion)){
				throw new PteParametersException("Cette Question ne peut être supprimer");
			}else {
				if(lQuestion.getReponses() != null) {
					lQuestion.getReponses().forEach(reponse ->{
						reponseDao.delete(reponse);
					});
				}
				questionDao.delete(lQuestion);
			}
		}
	}
	
	/*public int countNbQuestionsByNiveau(Question pQuestion, String pTypeQuestion) {
		
		int lNombrequestion = 0;
		pQuestion.getNiveaux().forEach(niveau -> {
			lNombrequestion = (int)lListQuestions.stream().filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getTypeQuestion().getLibelle().equals(PteAbstractConstants.TYPE_QUESTION_TECHNIQUE)).count();
		});
		
		 return 1;
	}*/
	
	@Override
	public List<InfoQuestionDto> getAllQuestionsByNiveau() {
		
		List<Question> lListQuestions = this.getAllQuestions();
		
		List<InfoQuestionDto> lLisInfoQuestionDto =  new ArrayList<InfoQuestionDto>();
		
		Set<Niveau> lLisNiveaux = new HashSet<>();
		
		lListQuestions.forEach(niveaux -> {
			lLisNiveaux.addAll(niveaux.getNiveaux());
		});
		
		lLisNiveaux.forEach(niveau -> {
			
			InfoQuestionDto lInfoQuestionDto =  new InfoQuestionDto();
			lInfoQuestionDto.setLibelleNiveau(niveau.getLibelle());
			
			ResumeQuestionDto lResumeQuestionDto = new ResumeQuestionDto();
			lResumeQuestionDto.setNbQuestionValidee((int)lListQuestions.stream().filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).count());
			lResumeQuestionDto.setNbQuestionNonValidee((int)lListQuestions.stream().filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getEtat().equals(Boolean.FALSE)).count());
			lResumeQuestionDto.setNbQuestionTechinque((int)lListQuestions.stream().filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getTypeQuestion().getLibelle().equals(PteAbstractConstants.TYPE_QUESTION_TECHNIQUE)).count());
			lResumeQuestionDto.setNbQuestionLogique((int)lListQuestions.stream().filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getTypeQuestion().getLibelle().equals(PteAbstractConstants.TYPE_QUESTION_LOGIQUE)).count());
			lResumeQuestionDto.setNbQuestionAnglais((int)lListQuestions.stream().filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getTypeQuestion().getLibelle().equals(PteAbstractConstants.TYPE_QUESTION_ANGLAIS)).count());
			lResumeQuestionDto.setNbQuestionFancais((int)lListQuestions.stream().filter(qVal -> qVal.getEtat().equals(Boolean.TRUE)).filter(niv -> niv.getNiveau().equals(niveau.getLibelle())).filter(qVal -> qVal.getTypeQuestion().getLibelle().equals(PteAbstractConstants.TYPE_QUESTION_FRANCAIS)).count());

			lInfoQuestionDto.setResumeQuestionDto(lResumeQuestionDto);
			lLisInfoQuestionDto.add(lInfoQuestionDto);
		});
		
		return lLisInfoQuestionDto;
		
	}
}
