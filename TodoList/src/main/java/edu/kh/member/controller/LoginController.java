package edu.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.member.model.dto.Member;
import edu.kh.member.model.service.MemberService;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/login")
public class LoginController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setCharacterEncoding("UTF-8");
			
			String loginId = req.getParameter("loginId");
			String loginPw = req.getParameter("loginPw");
			
			MemberService service = new MemberService();
			
			Member loginMember = service.login(loginId,loginPw);
			
			System.out.println(loginMember);
			

			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				//로그인이 성공
				
				TodoService todoService = new TodoService();
				
				List<Todo> todoList = todoService.selectList(loginMember.getMemberNo());
				System.out.println("todoList :" + todoList);
				
				
				session.setAttribute("loginMember", loginMember);
				session.setMaxInactiveInterval(60*60);
				System.out.println("로그인 성공");
				
				// todolist 전달
//				req.setAttribute("todoList", todoList);
				session.setAttribute("todoList", todoList);
				resp.sendRedirect("/");
				
			}else {
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println("로그인 실패");
				resp.sendRedirect("/");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}