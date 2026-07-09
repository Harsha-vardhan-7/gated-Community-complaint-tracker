package com.controller;

import java.io.IOException;

import com.enums.RoleEnum;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendRedirect("login");
			return;
		}

		RoleEnum role = (RoleEnum) session.getAttribute("role");

		if (role == RoleEnum.ADMIN) {
			req.getRequestDispatcher("/WEB-INF/views/admindashboard.jsp").forward(req, resp);
			return;
		} else if (role == RoleEnum.RESIDENT) {
			req.getRequestDispatcher("/WEB-INF/views/residentdashboard.jsp").forward(req, resp);
			return;
		} else {
			session.invalidate();
			resp.sendRedirect("login");
			return;
		}
	}
}
