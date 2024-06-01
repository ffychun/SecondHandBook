package com.ser;

import com.org.dao.BookDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddBookServlet", urlPatterns = "/AddBookServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 内存缓冲区的大小阈值
        maxFileSize = 1024 * 1024 * 5,    // 上传文件的最大大小
        maxRequestSize = 1024 * 1024 * 5 * 5 // 请求的最大大小
)
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Part filePart = request.getPart("picture");
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // 获取上传文件的文件名
        String mimeType = getServletContext().getMimeType(originalFileName); // 获取文件的 MIME 类型

        if (mimeType != null && mimeType.startsWith("image")) {
            // 从会话中获取了用户ID
            int userId = (int)request.getSession().getAttribute("userId");

            // 获取当前时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTime = dateFormat.format(new Date());

            // 创建新的文件名
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = "user_" + userId + "_time_" + currentTime + fileExtension;

            Book newBook = new Book();
            newBook.setBookName(request.getParameter("bookName"));
            newBook.setInventory(Integer.parseInt(request.getParameter("inventory")));
            newBook.setNote(request.getParameter("note"));
            newBook.setPrice(Double.parseDouble(request.getParameter("price")));
            String imagePath = "bookImages/" + newFileName; // 存储的是相对于项目根目录的文件路径
            newBook.setPath(imagePath);
            newBook.setuId(userId);

            try {
                BookDAO bookDAO = DAOFactory.getBookDAOInstance();
                bookDAO.addBook(newBook);

                // 保存文件到当前web项目中
                String uploadPath = getServletContext().getRealPath("") + File.separator + "bookImages";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                Path filePath = Paths.get(uploadDir.getAbsolutePath(), newFileName);
                InputStream fileContent = filePart.getInputStream(); // 获取文件内容
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

                response.sendRedirect("operateSucceed.jsp");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("imageError.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
