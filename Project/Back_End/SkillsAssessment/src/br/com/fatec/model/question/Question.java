package br.com.fatec.model.question;
import java.util.List;

public class Question {
	
	private Long number;
	private String introduction;
	private String question;
	private List<Answer> answers;
	private Integer situation;
	
	public Question(Long number, String introduction, String question,
			List<Answer> answers) {
		this.number = number;
		this.introduction = introduction;
		this.question = question;
		this.answers = answers;
	}
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public int getSituation() {
		return situation;
	}
	public void setSituation(int situation) {
		this.situation = situation;
	}
}
