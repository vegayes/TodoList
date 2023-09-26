package edu.kh.todo.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.todo.common.JDBCTemplete.*;

import edu.kh.member.model.dao.MemberDAO;
import edu.kh.todo.model.dao.TodoDAO;
import edu.kh.todo.model.dto.Todo;

public class TodoService {
	
	private TodoDAO dao = new TodoDAO();

	/** 1) 조회하기(리스트)
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Todo> selectList(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.selectTodo(conn, memberNo);
		
		close(conn);
		return todoList;
	}


	/** 1-1 ) 조회하기 (num)
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public Todo todo(int num) throws Exception {
		Connection conn = getConnection();
		
		Todo todo =dao.select(conn,num);
		
		close(conn);
		return todo;
	}
	

	/** 2) 등록하기
	 * @param insertTitle
	 * @param insertMemo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int insert(String insertTitle, String insertMemo, int memberNo) throws Exception {
		Connection conn = getConnection();
		int todoResult = dao.insert(conn, insertTitle, insertMemo, memberNo);
		
		if(todoResult > 0) commit(conn);
		else 				rollback(conn);
		
		close(conn);
		return todoResult;
	}

	/** 3) 수정하기
	 * @param updateTitle
	 * @param updateMemo
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int update(String updateTitle, String updateMemo, int num) throws Exception{
		Connection conn = getConnection();
		int updateResult = dao.update(conn, updateTitle, updateMemo, num);
		
		if(updateResult > 0) commit(conn);
		else 				rollback(conn);

		close(conn);
		return updateResult;
	}


	/** 4) 삭제하기
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int delete(int num) throws Exception {
		Connection conn = getConnection();
		int delResult = dao.del(conn,num);
		
		if(delResult >0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		return delResult;
	}

	
}
