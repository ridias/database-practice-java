package infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import application.interfaces.GroupRepository;
import domain.entities.Group;
import infrastructure.mapper.MapperGroup;

public class GroupMysqlRepository implements GroupRepository {

	private Connection conn;
	private MapperGroup mapper;
	
	public GroupMysqlRepository() {
		this.mapper = new MapperGroup();
	}
	
	@Override
	public Group insert(Group obj) {
		this.conn = MysqlConnection.getConnection();
		obj.setId(-1);
		try {
			var query = "INSERT INTO viculturadb.groups Values(?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, obj.getName());
			preparedStmt.setString(3, obj.getDescription());
			preparedStmt.setTimestamp(4, Timestamp.valueOf(obj.getDateCreated()));
			
			var dateEnd = obj.getDateEnd();
			if(dateEnd == null) {
				preparedStmt.setTimestamp(5, null);
			}else {
				preparedStmt.setTimestamp(5, Timestamp.valueOf(obj.getDateEnd()));
			}
			
			preparedStmt.setInt(6, obj.getIdUser());
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
	public void update(Group obj) {
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "UPDATE viculturadb.groups SET name = ?, description = ?, date_end = ? WHERE id = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, obj.getName());
			preparedStmt.setString(2, obj.getDescription());
			var dateEnd = obj.getDateEnd();
			if(dateEnd == null) {
				preparedStmt.setTimestamp(4, null);
			}else {
				preparedStmt.setTimestamp(4, Timestamp.valueOf(obj.getDateEnd()));
			}
			
			preparedStmt.setInt(6, obj.getId());
			preparedStmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		
	}

	@Override
	public List<Group> getAllByUserId(int idUser) {
		this.conn = MysqlConnection.getConnection();
		var groups = new ArrayList<Group>();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT * FROM viculturadb.groups WHERE idUser = " + idUser;
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) groups.add(this.mapper.map(rset));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return groups;
	}

	@Override
	public Group getByIdAndIdUser(int id, int idUser) {
		this.conn = MysqlConnection.getConnection();
		var group = new Group();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT * FROM viculturadb.groups WHERE id_user = ? and id = ?";
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) group = this.mapper.map(rset);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return group;
	}

	@Override
	public void delete(int id) {
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "DELETE FROM viculturadb.groups WHERE id = " + id;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
	}

}
