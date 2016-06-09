package br.gov.sp.fatec.entity;
import java.util.List;


public class Answer {
	
	private Long code;
	private String description;
	private List<Competence> competencies;
	private Long codeQuestion;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Competence> getCompetencies() {
		return competencies;
	}
	public void setCompetencies(List<Competence> competencies) {
		this.competencies = competencies;
	}
	public Long getCodeQuestion() {
		return codeQuestion;
	}
	public void setCodeQuestion(Long codeQuestion) {
		this.codeQuestion = codeQuestion;
	}
	
	
}
