package com.imooc.oa.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/api/login")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String,Object> result = new LinkedHashMap<>();

        try {
            User user = userService.checkLogin(username, password);
            result.put("code", "0");
            result.put("message", "success");
            Map<String,User> data = new LinkedHashMap<>();
            data.put("user", user);
            result.put("data", data);
        } catch (Exception e) {
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }
        // 返回json結果
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(result);
        resp.getWriter().println(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
