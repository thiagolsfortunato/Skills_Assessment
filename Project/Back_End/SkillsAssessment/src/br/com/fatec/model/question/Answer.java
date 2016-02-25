package br.com.fatec.model.question;
import java.util.List;


public class Answer {
	
	private Long code;
	private String description;
	private List<Competence> competencies;
	
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
	
	
}
