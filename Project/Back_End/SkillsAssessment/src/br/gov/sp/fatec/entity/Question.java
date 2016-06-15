package br.gov.sp.fatec.entity;
import java.util.List;

public class Question {
	
	private Long code;
	private String question;
	private String introduction;
	private String type;
	private Integer situation;
	private List<Answer> answers;
	private Integer unansweredQuestions;
	private Integer questionAmount;
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getSituation() {
		return situation;
	}

	public void setSituation(Integer situation) {
		this.situation = situation;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getUnansweredQuestions() {
		return unansweredQuestions;
	}

	public void setUnansweredQuestions(Integer unansweredQuestions) {
		this.unansweredQuestions = unansweredQuestions;
	}

	public Integer getQuestionAmount() {
		return questionAmount;
	}

	public void setQuestionAmount(Integer questionAmount) {
		this.questionAmount = questionAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
