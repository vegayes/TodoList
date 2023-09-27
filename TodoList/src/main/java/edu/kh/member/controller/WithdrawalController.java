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

@WebServlet("/withdrawal")
public class WithdrawalController extends HttpServlet {

	private MemberService service = new MemberService();
	
//	 1) 탈퇴하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		try {
			int memNo= (int) session.getAttribute("loginMemberNo");
			
			int withdrawal = service.withdrawal(memNo);
			
			if(withdrawal > 0) {
				

				session.setAttribute("message", "탈퇴되었습니다.");
				
				session.invalidate();
				resp.sendRedirect("/");				
				
				System.out.println("회원탈퇴");
				
			}else {
				session.setAttribute("message", "탈퇴를 실패하였습니다.");
				resp.sendRedirect("/");
				System.out.println("회원탈퇴불가");
			}
			
//			int withdrawal = service.withdrawal();
			

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
