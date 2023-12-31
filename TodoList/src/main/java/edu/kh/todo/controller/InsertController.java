package edu.kh.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RepaintManager;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@WebServlet("/insert")
public class InsertController extends HttpServlet {
	
	private TodoService service = new TodoService();
	
//	1) 등록하기 화면 띄우기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	2) 등록하기 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		try {
			
			req.setCharacterEncoding("UTF-8");
			
			String insertTitle = req.getParameter("insertTitle");
			String insertMemo = req.getParameter("insertMemo");
			
			int memberNo= (int) session.getAttribute("loginMemberNo");
			
			// memberNo 객체에서 빼서 저장해야만 가져올 수 있남?
		
			int todoResult = service.insert(insertTitle, insertMemo, memberNo);
			
			
			if(todoResult > 0) {
				session.setAttribute("message", "TodoList가 등록되었습니다.");
				
				TodoService todoService = new TodoService();
				System.out.println("등록 후 조회 시작 ");
				List<Todo> todoList = todoService.selectList(memberNo);
				
				session.setAttribute("todoList", todoList);
				resp.sendRedirect("/");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	
}
