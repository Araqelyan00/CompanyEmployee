package companyEmployee.servlet;

import companyEmployee.constants.SharedConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picName = req.getParameter("picName");
        File imageFile = new File(SharedConstant.UPLOAD_FOLDER + picName);
        if (imageFile.exists()) {
            try (FileInputStream fis = new FileInputStream(imageFile)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());
                OutputStream os = resp.getOutputStream();
                byte[] buffer = new byte[1024*4];
                int bytesRead = -1;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
