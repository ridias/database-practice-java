package infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import application.interfaces.ObjectiveRepository;
import domain.entities.Objective;
import infrastructure.mapper.MapperObjective;

public class ObjectiveMysqlRepository implements ObjectiveRepository {

	private Connection conn;
	private MapperObjective mapper;
	
	@Override
	public Objective insert(Objective obj) {
		this.conn = MysqlConnection.getConnection();
		obj.setId(-1);
		
		try {
			var query = "INSERT INTO viculturadb.objectives Values(0, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, obj.getMinProgress());
			preparedStmt.setInt(2, obj.getMaxProgress());
			preparedStmt.setTimestamp(3, Timestamp.valueOf(obj.getDateCreated()));
			preparedStmt.setInt(4, obj.getIdGroup());
			preparedStmt.setInt(5, obj.getMaterial().getId());
			preparedStmt.setInt(6, obj.getCurrentProgress());
			preparedStmt.executeUpdate();
			var rs = preparedStmt.getGeneratedKeys();
			if(rs.next()) {
				obj.setId(rs.getInt(1));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return obj;
	}

	@Override
	public void update(Objective obj) {
		this.conn = MysqlConnection.getConnection();
		
		try {
			var query = "UPDATE viculturadb.objectives SET min_progress = ?, max_progress = ?, current_progress = ? where id = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, obj.getMinProgress());
			preparedStmt.setInt(2, obj.getMaxProgress());
			preparedStmt.setInt(3, obj.getCurrentProgress());
			preparedStmt.setInt(4, obj.getId());
			preparedStmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		
	}

	@Override
	public int getTotalByIdGroup(int idGroup) {
		int total = 0;
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "SELECT count(*) as total from objectives WHERE id_group = " + idGroup;
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) total = rset.getInt(1);			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return total;
	}

	@Override
	public List<Objective> getAllByIdGroup(int idGroup, int start, int limit) {
		this.conn = MysqlConnection.getConnection();
		var objectives = new ArrayList<Objective>();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT o.id as id, m.name, m.year, m.url_image, m.url_details, o.min_progress, o.max_progress, o.current_progress, o.date_created, o.id_group FROM objectives as o, materials as m WHERE o.id_group = " + idGroup + " and o.id_material = m.id LIMIT  " + start + ", " + limit;
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) objectives.add(this.mapper.map(rset));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return objectives;
	}

	@Override
	public boolean doesObjectBelongToIdUser(int id, int idUser) {
		this.conn = MysqlConnection.getConnection();
		boolean itBelongs = true;
		
		try {
			var query = "SELECT o.id as id FROM objectives AS o, viculturadb.groups AS g WHERE g.id_user = ? AND g.id = o.id_group AND o.id = ? ";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, idUser);
			preparedStmt.setInt(2, id);
			preparedStmt.executeUpdate();
			var rset = preparedStmt.getResultSet();
			if(!rset.next()) itBelongs = false;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return itBelongs;
	}

	@Override
	public boolean doesObjectBelongToIdGroupAndIdUser(int id, int idUser, int idGroup) {
		this.conn = MysqlConnection.getConnection();
		boolean itBelongs = true;
		
		try {
			var query = "SELECT o.id as id FROM objectives AS o, viculturadb.groups AS g WHERE g.id_user = ? AND g.id = o.id_group AND o.id = ? AND o.id_group = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, idUser);
			preparedStmt.setInt(2, id);
			preparedStmt.setInt(3, idGroup);
			preparedStmt.executeUpdate();
			var rset = preparedStmt.getResultSet();
			if(!rset.next()) itBelongs = false;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return itBelongs;
	}

	@Override
	public void delete(int id) {
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "SELECT * FROM viculturadb.objectives where id = " + id;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		
	}

}
