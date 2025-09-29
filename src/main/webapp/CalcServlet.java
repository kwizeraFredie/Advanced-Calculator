package ac.rw;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String expr = request.getParameter("expression");
        double result = 0;

        try {
            if (expr.contains("sin")) {
                String numStr = expr.replace("sin", "").trim();
                result = Math.sin(Math.toRadians(Double.parseDouble(numStr)));
            } else if (expr.contains("cos")) {
                String numStr = expr.replace("cos", "").trim();
                result = Math.cos(Math.toRadians(Double.parseDouble(numStr)));
            } else if (expr.contains("sqrt")) {
                String numStr = expr.replace("sqrt", "").trim();
                result = Math.sqrt(Double.parseDouble(numStr));
            } else if (expr.contains("^")) {
                String[] parts = expr.split("\\^");
                double base = Double.parseDouble(parts[0]);
                double power = Double.parseDouble(parts[1]);
                result = Math.pow(base, power);
            } else {
                result = evalBasic(expr);
            }
        } catch (Exception e) {
            response.getWriter().println("<h2 style='color:red;'>Error: Invalid Expression</h2>");
            response.getWriter().println("<a href='index.html'>Try Again</a>");
            return;
        }

        response.setContentType("text/html");
        response.getWriter().println("<link rel='stylesheet' href='style.css'>");
        response.getWriter().println("<div class='calculator'>");
        response.getWriter().println("<h2>Result: " + result + "</h2>");
        response.getWriter().println("<a href='index.html'>Back</a>");
        response.getWriter().println("</div>");
    }

    private double evalBasic(String expr) {
        expr = expr.replaceAll("\\s+", "");
        if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expr.contains("-")) {
            String[] parts = expr.split("\\-");
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else if (expr.contains("*")) {
            String[] parts = expr.split("\\*");
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } else if (expr.contains("/")) {
            String[] parts = expr.split("\\/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
        return 0;
    }
}
