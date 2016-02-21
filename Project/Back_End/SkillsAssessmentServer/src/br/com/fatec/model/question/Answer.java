package br.com.fatec.model.question;
import java.util.List;


public class Answer {
	
	private Long code;
	private String answer;
	private List<Competencies> competencies;
	
	public Answer(Long code, String answer, List<Competencies> competencies) {

		this.code = code;
		this.answer = answer;
		this.competencies = competencies;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<Competencies> getCompetencies() {
		return competencies;
	}
	public void setCompetencies(List<Competencies> competencies) {
		this.competencies = competencies;
	}	
}
