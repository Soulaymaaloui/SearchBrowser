package com.example.searchengine;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ServletSearch", urlPatterns = {"/Find"})
public class SearchEngine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the keyword and engine parameters
        String keyword = request.getParameter("keyword");
        String engine = request.getParameter("engine");

        // Check if parameters are valid
        if (keyword == null || keyword.isEmpty() || engine == null || engine.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Keyword and search engine are required.");
            return;
        }

        // Prepare the search URL
        String redirectUrl;
        switch (engine.toLowerCase()) {
            case "google":
                redirectUrl = "https://www.google.com/search?q=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
                break;
            case "bing":
                redirectUrl = "https://www.bing.com/search?q=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
                break;
            default:
                response.setContentType("text/plain");
                response.getWriter().write("Invalid search engine. Use 'google' or 'bing'.");
                return;
        }

        // Redirect to the search engine
        response.sendRedirect(redirectUrl);
    }
}
