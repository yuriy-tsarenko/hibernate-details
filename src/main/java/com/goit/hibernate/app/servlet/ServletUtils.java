package com.goit.hibernate.app.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

record ServletUtils() {

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    public static Optional<Long> resolveNumericPathVariable(String requestURI) {
        Pattern numericPattern = Pattern.compile("[0-9]+");
        String[] uriParts = requestURI.split("/");
        for (String uriPart : uriParts) {
            if (numericPattern.matcher(uriPart).matches()) {
                return Optional.of(Long.parseLong(uriPart));
            }
        }
        return Optional.empty();
    }

    public static void sendJsonResponse(HttpServletResponse response, Object object) throws IOException {
        String json = GSON.toJson(object);
        response.setStatus(HttpCode.OK);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
