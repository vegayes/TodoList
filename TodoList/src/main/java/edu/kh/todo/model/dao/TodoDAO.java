package edu.kh.todo.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import edu.kh.member.model.dao.MemberDAO;
import edu.kh.todo.model.dto.Todo;
import static edu.kh.todo.common.JDBCTemplete.*;

public class TodoDAO {
	
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public TodoDAO() {
		
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/todo/sql/todo-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


//	1) 조회하기
	public List<Todo> selectTodo(Connection conn, int memberNo) throws Exception {
		
		List<Todo>todoList = new ArrayList<Todo>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
//			String sql = prop.getProperty("one");
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			System.out.println("memberNo : " + memberNo);
			rs = pstmt.executeQuery();
	
			
			while(rs.next()) {
				Todo todo = new Todo();

				todo.setTodoTitle(rs.getString(1));
				todo.setTodoMemo(rs.getString(2));
				todo.setTodoDate(rs.getString(3));
				todo.setMemberNo(rs.getInt(4));
				todoList.add(todo);
				
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return todoList;
	}


//	 2) 등록하기
	public int insert(Connection conn, String insertTitle, String insertMemo) throws Exception{
		
		int insertResult = 0;
		
		try {
			
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, insertTitle);
			pstmt.setString(2, insertMemo);
			

			insertResult = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
	
		return insertResult;
	}
}