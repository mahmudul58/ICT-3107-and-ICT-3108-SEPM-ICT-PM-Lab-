import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the HTML form (make sure your form inputs match these names)
        String deptName = request.getParameter("deptName");
        String numStudentsStr = request.getParameter("numStudents");
        String action = request.getParameter("action");

        // Parse number of students (default to 0 if empty/invalid to avoid crashes)
        int numStudents = 0;
        if (numStudentsStr != null && !numStudentsStr.isEmpty()) {
            try {
                numStudents = Integer.parseInt(numStudentsStr);
            } catch (NumberFormatException e) {
                numStudents = 0;
            }
        }

        ServiceClass service = new ServiceClass();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // CSS Styling
        out.println("<html><head><style>");
        out.println("table { border-collapse: collapse; width: 50%; margin-top: 20px; }");
        out.println("th, td { border: 1px solid #333; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { font-family: Arial, sans-serif; padding: 20px; }");
        out.println("</style></head><body>");

        out.println("<h2>Action: " + (action != null ? action : "None") + "</h2>");

        if (action == null) {
            out.println("<p>No action specified.</p>");
            out.println("</body></html>");
            return;
        }

        switch (action) {
            case "Insert":
                if (service.insertDB(deptName, numStudents)) {
                    out.println("<p style='color:green;'>Department inserted successfully.</p>");
                } else {
                    out.println("<p style='color:red;'>Insertion failed. (Check if Department Name already exists)</p>");
                }
                break;

            case "View":
                List<String> depts = service.viewDB();
                if (depts.isEmpty()) {
                    out.println("<p>No departments found.</p>");
                } else {
                    out.println("<table>");
                    out.println("<tr><th>Department Name</th><th>Number of Students</th></tr>");
                    for (String dept : depts) {
                        // Manual parsing of the string returned by service.viewDB()
                        // Expected format: "Department: [Name], Students: [Num]"
                        String[] parts = dept.split(", Students: ");
                        String dName = parts[0].replace("Department: ", "");
                        String dNum = parts.length > 1 ? parts[1] : "0";
                        out.println("<tr><td>" + dName + "</td><td>" + dNum + "</td></tr>");
                    }
                    out.println("</table>");
                }
                break;

            case "Update":
                if (service.updateDB(deptName, numStudents)) {
                    out.println("<p style='color:green;'>Updated successfully.</p>");
                } else {
                    out.println("<p style='color:red;'>Update failed. Department Name not found?</p>");
                }
                break;

            case "Delete":
                if (service.deleteDB(deptName)) {
                    out.println("<p style='color:green;'>Deleted successfully.</p>");
                } else {
                    out.println("<p style='color:red;'>Delete failed. Department Name not found?</p>");
                }
                break;

            default:
                out.println("<p>Unknown action.</p>");
        }

        out.println("<br><a href='index.jsp'>Go Back</a>"); // Assuming you have an index.jsp
        out.println("</body></html>");
    }
}