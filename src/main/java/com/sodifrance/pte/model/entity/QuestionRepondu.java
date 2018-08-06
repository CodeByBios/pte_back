package com.sodifrance.pte.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class QuestionRepondu implements Serializable{

	private static final long serialVersionUID = 5539470872792087648L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "candidat_id")
	Candidat candidat;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<ReponseCandidat> reponsesCandidat = new ArrayList<ReponseCandidat>();
	
	public QuestionRepondu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionRepondu(Long id, Question question, Candidat candidat, List<ReponseCandidat> reponsesCandidat) {
		super();
		this.id = id;
		this.question = question;
		this.candidat = candidat;
		this.reponsesCandidat = reponsesCandidat;
	}
}
