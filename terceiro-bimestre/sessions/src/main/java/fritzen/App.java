package fritzen;

import static spark.Spark.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import fritzen.auth.AuthController;
import fritzen.config.Database;
import fritzen.images.ImageDao;


public class App {
    public static void main(String[] args) {

        File uploadDir = new File("uploads");
        uploadDir.mkdir();

        staticFiles.location("/public");
        staticFiles.externalLocation("uploads");

        post("/entrar", AuthController.validar);

        get("/", (req, res) -> "Hello");


        post("/upload", (req, res) -> {
            
            Boolean login = req.session().attribute("login");

            if (login == null || login == false) {
                res.redirect("/login.html");
                return "";
            }

            int userId = req.session().attribute("user_id");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream is = req.raw().getPart("uploaded_file").getInputStream()) {
                
                String filename = req.raw().getPart("uploaded_file").getSubmittedFileName();
                Path path = Files.createTempFile(uploadDir.toPath(), "", filename);

                System.out.println("FINAL PATH: " + path.getFileName().toString());

                Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);

                ImageDao imgDao = new ImageDao();
                imgDao.add(path.getFileName().toString(), userId);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "ok";
        });


        get("/photos", (req, res) -> {

            Connection con = Database.getConnection();
            List<String> images = new ArrayList<String>();
            
            try {

            String sql = "SELECT * FROM images;";
            ResultSet rs = con.prepareStatement(sql).executeQuery();

            while (rs.next()) {
               images.add(rs.getString("filename"));
            }

            } catch (SQLException e) {
            System.out.println("Erro ao buscar os usuários");
            e.printStackTrace();
            }

            String html = "<!DOCTYPE html><html><body>"; 

            for (int i = 0; i < images.size(); i++) {
                html += "<img src=\"" + images.get(i) + "\" width='200px' height='200px' /> ";
            };

            html+= "</body></html>";
            return html;
        });


        get("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/login.html");
            return null;
        });

        get("/protected", (req, res) -> {
            Boolean login = req.session().attribute("login");

            if (login != null && login == true)
                return "Acessível apenas logando, logado:  " + req.session().attribute("login");

            res.header("Location", "/login.html");
            res.redirect("/login.html");
            return null;
        });

        post("/adicionar", (req, res) -> {

            Boolean login = req.session().attribute("login");

            if (login == null || login == false) {
                res.redirect("/login.html");
                return "";
            }

            int userId = req.session().attribute("user_id");
            String msg = req.queryParams("msg");

            Connection con = Database.getConnection();

            try {

                String sql = "INSERT INTO posts (user_id, msg) VALUES (?, ?);";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, msg);
                ps.execute();

                return "Tudo certo...";

            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar usuário");
                e.printStackTrace();

                return "ooooppss";
            }

        });

    }
}
