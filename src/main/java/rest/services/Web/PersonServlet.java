package rest.services.Web;

import rest.services.Model.Person;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        getPerson(req, resp);
    }


    private void getPerson (HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("bitrthday");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
    }


    private Person retrievePersonFromRequest (HttpServletRequest request){

        Person result = new Person();
        result.setFirstName(request.getParameter("firstName"));
        result.setLastName(request.getParameter("lastName"));
        result.setGender(request.getParameter("gender"));
        result.setBirthday(request.getParameter("birthday"));
        result.setEmail(request.getParameter("email"));
        result.setAge(request.getParameter("age"));

        return result;


    }


}
