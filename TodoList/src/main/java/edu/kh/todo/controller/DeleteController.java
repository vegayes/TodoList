package edu.kh.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{
	
	private TodoService service = new TodoService();
	
//	 1) 삭제하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		try {
			String todoNo = req.getParameter("todoNo");		
			int num = Integer.parseInt(todoNo);
			
			int delResult = service.delete(num);
			
			if(delResult > 0) {
				System.out.println("삭제되었습니다.");
				session.setAttribute("message", "TodoList가 삭제되었습니다.");
				
				int memberNo= (int) session.getAttribute("loginMemberNo");
				
				System.out.println("삭제 후 조회 시작 ");
				List<Todo> todoList = service.selectList(memberNo);
				
				session.setAttribute("todoList", todoList);
	
				resp.sendRedirect("/");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
