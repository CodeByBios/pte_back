package com.sodifrance.pte.transform;

import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.ReponseCandidatDto;
import com.sodifrance.pte.model.entity.ReponseCandidat;

@Component
public class ReponseCandidatTransform {

	public ReponseCandidat convertToEntity(ReponseCandidatDto pReponseCandidatDto) {
		ReponseCandidat rep = null;

		if (pReponseCandidatDto != null) {
			ReponseCandidat lreponseCandidat = new ReponseCandidat();
			lreponseCandidat.setLibelle(pReponseCandidatDto.getLibelle());
			lreponseCandidat.setReponseJuste(pReponseCandidatDto.getReponseJuste());
			rep = lreponseCandidat;
		}

		return rep;
	}
}
