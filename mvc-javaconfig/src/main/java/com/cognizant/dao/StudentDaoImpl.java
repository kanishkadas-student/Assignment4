package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private JdbcTemplate  jdbcTemplate;

	private List<Student> list = new ArrayList<Student>();
	
	//private JdbcTemplate jdbc;
	@Override
	public String insert(Student student) {

		String sqlInsert = "insert into student values(?,?)";
		int result = jdbcTemplate.update(sqlInsert, student.getId(), student.getName());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
//		if (list.add(student))
//			return "SUCCESS";
//		else
//			return "FAIL";

	}

	@Override
	public String delete(Student student) {

		String sqlDelete = "delete from student where id = ?";
		int result = jdbcTemplate.update(sqlDelete, student.getId());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
		//return null;
	}

	@Override
	public String update(Student student) {
		
		String sqlUpdate = "update student set name = ? where id = ?";
		int result = jdbcTemplate.update(sqlUpdate, student.getName(), student.getId());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
		//return null;
	}

	@Override
	public List<Student> getAll() {
		//return list;
		String sql = "select * from student";
		List<Student> result = jdbcTemplate.query(sql, (rs, RowMapper) ->
									new Student(rs.getString(1), rs.getString(2)));
		return result;
	}

}
