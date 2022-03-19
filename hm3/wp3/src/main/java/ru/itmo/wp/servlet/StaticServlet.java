package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] allUris = uri.split("[+]");
        String contentType = getContentTypeFromName(allUris[0]);
        response.setContentType(contentType);
        List<File> filesToSend = new ArrayList<>();
        for (String nextUri : allUris) {
            File nextFile = getFile(nextUri);
            if (nextFile.isFile()) {
                filesToSend.add(nextFile);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }
        OutputStream outputStream = response.getOutputStream();
        for (File nextFile : filesToSend) {
            Files.copy(nextFile.toPath(), outputStream);
        }
        outputStream.flush();
    }

    private File getFile(String uri) {
        File file = new File("./src/main/webapp/static/" + uri);
        if (!file.isFile()) {
            file = new File(getServletContext().getRealPath("/static/" + uri));
        }
        return file;
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
