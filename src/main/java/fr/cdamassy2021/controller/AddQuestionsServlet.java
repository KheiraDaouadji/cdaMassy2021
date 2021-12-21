
package fr.cdamassy2021.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addQuestionsServlet
 */
@WebServlet("/add")
public class AddQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddQuestionsDAO addQuestionsDAO;

    public void init() {
    	addQuestionsDAO = new AddQuestionsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	int registeraddQuestionsId = 0;
    	
        String intituleQuestion = request.getParameter("intituleQuestion");
        String libelleQuestion = request.getParameter("libelleQuestion");
        String libelleProposition = request.getParameter("libelleProposition");
        String undefined = request.getParameter("undefined");
        String correct = request.getParameter("correct");
        String incorrect = request.getParameter("incorrect");

        AddQuestions addQuestions = new AddQuestions();
        addQuestions.setintituleQuestion(intituleQuestion);
        addQuestions.setlibelleQuestion(libelleQuestion);
        addQuestions.setlibelleProposition(libelleProposition);
        addQuestions.setundefined(undefined);
        addQuestions.setcorrect(correct);
       
        addQuestions.setincorrect(incorrect);

        try {
        	registeraddQuestionsId = addQuestionsDAO.registeraddQuestions(addQuestions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(registeraddQuestionsId > 0) {
        	request.setAttribute("intituleQuestion", addQuestions.getintituleQuestion());
        	request.setAttribute("libelleQuestion",addQuestions.getlibelleQuestion());
        	request.setAttribute("libelleProposition",addQuestions.getlibelleProposition());
        	request.setAttribute("undefined",addQuestions.getundefined());
        	request.setAttribute("incorrect", addQuestions.getincorrect());

          RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewQuestions.jsp");
          requestDispatcher.forward(request, response);
        }
    }
}