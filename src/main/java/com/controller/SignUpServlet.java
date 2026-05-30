package com.controller;

import java.io.IOException;

import com.DAO.ResidentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		int flatNo = Integer.parseInt(req.getParameter("flatnumber"));
		String mobileNo = req.getParameter("mobile");
		String password = req.getParameter("password");

		ResidentDAO resident = new ResidentDAO();
		resident.saveResident(firstName, lastName, flatNo, mobileNo, password);

		req.setAttribute("firstName", firstName);
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}

}
