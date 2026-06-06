package com.controller;

import java.io.IOException;

import com.DAO.LoginDAO;
import com.DAO.ResidentDAO;
import com.entity.Resident;

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
		String flatNum = req.getParameter("flatnumber");
		int flatNo;
		if (flatNum == null || flatNum.trim().isEmpty()) {
			req.setAttribute("firstNameerrorMessage", "Flat Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		} else {
			try {
				flatNo = Integer.parseInt(flatNum);
			} catch (NumberFormatException e) {
				req.setAttribute("flatParseErrorMessage", "Flat Number must contain only numbers.");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				return;
			}
		}

		String mobileNo = req.getParameter("mobile");
		String password = req.getParameter("password");

		if (firstName == null || firstName.trim().isEmpty()) {
			req.setAttribute("firstNameErrorMessage", "First Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			req.setAttribute("lastNameErrorMessage", "Last Name is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (mobileNo == null || mobileNo.trim().isEmpty()) {
			req.setAttribute("mobileNoErrorMessage", "Mobile Number is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		if (password == null || password.trim().isEmpty()) {
			req.setAttribute("passworErrorMessage", "Password is Mandatory, Please Enter");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		ResidentDAO resident = new ResidentDAO();

		LoginDAO loginDAO = new LoginDAO();
		Resident residentDetails = loginDAO.getResidentDetails(mobileNo);

		boolean validFlat = (flatNo >= 101 && flatNo <= 120) ||

				(flatNo >= 201 && flatNo <= 220) ||

				(flatNo >= 301 && flatNo <= 320) ||

				(flatNo >= 401 && flatNo <= 420) ||

				(flatNo >= 501 && flatNo <= 520);

		if (!validFlat) {
			req.setAttribute("FlatNoerrorMessage", "Invalid Flat Number. Please enter a valid flat number.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		if (mobileNo.equalsIgnoreCase(residentDetails.getMobileNumber())) {
			req.setAttribute("errorMessage", "Resident is already registered. Please log in to continue.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		resident.saveResident(firstName, lastName, flatNo, mobileNo, password);

		req.setAttribute("firstName", firstName);
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}

}
