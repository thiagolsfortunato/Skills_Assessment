package br.gov.sp.fatec.main;

import br.gov.sp.fatec.connection.CorsFilter;
import br.gov.sp.fatec.controller.CompetenciesRoutes;
import br.gov.sp.fatec.controller.CourseRoutes;
import br.gov.sp.fatec.controller.EnrollsRoutes;
import br.gov.sp.fatec.controller.InstitutionRoutes;
import br.gov.sp.fatec.controller.QuestionRoutes;
import br.gov.sp.fatec.controller.QuizRoutes;
import br.gov.sp.fatec.controller.UserRoutes;

import spark.servlet.SparkApplication;

public class App implements SparkApplication{

	@Override
	public void init() {
		
		CorsFilter.apply();
		 
		 QuizRoutes.getQuiz();
		 QuestionRoutes.getQuestions();
		 CourseRoutes.getCourse();
		 EnrollsRoutes.getEnrolls();
		 CompetenciesRoutes.getCompetencies();
		 UserRoutes.getUser();
		 InstitutionRoutes.getInstitution();
		
	}

}
