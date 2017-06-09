package nl.atlasdev.les2.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/calculator.do")
public class Calculator extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double one = Double.parseDouble(req.getParameter("one"));
		double two = Double.parseDouble(req.getParameter("two"));
		String type = req.getParameter("type");
		double answer = 0;
		switch(type) {
		case "+":
			answer = one + two;
			break;
		case "-":
			answer = one - two;
			break;
		case "*":
			answer = one * two;
			break;
		case "/":
			answer = one / two;
			break;
		}
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Calculated</title>");
		out.println(" <body>");
		out.println(" <p>The answer is <b>" + answer + "</b></p>");
		out.println(" </body>");
		out.println("</html>");
		out.close();
	}
}