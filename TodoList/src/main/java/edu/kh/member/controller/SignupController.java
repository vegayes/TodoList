package edu.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.member.model.service.MemberService;

@WebServlet("/signup")
public class SignupController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");

			String signupId = req.getParameter("signupId");
			String signupPw = req.getParameter("signupPw");
			String signupCheckPw = req.getParameter("signupCheckPw");
			String signupNick = req.getParameter("signupNick");

			MemberService service = new MemberService();
//			유효성 검사가 모두 통과되었을 때, 회원가입을 실행할 수 있게 해야하는데,, ?

			
			int newMember = service.signupMember(signupId,signupPw,signupNick);
			
			HttpSession session = req.getSession();

			if(newMember > 0){
				System.out.println("성공");
				
				session.setAttribute("message", "회원가입에 성공하셨습니다.");
				resp.sendRedirect("/");
				
			}else {
				System.out.println("회원가입 오류");
				
				session.setAttribute("message", "회원가입에 오류가 발생하였습니다.");
				resp.sendRedirect("/");
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


