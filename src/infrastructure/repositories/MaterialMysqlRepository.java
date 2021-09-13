package infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import application.interfaces.MaterialRepository;
import domain.entities.Material;
import infrastructure.mapper.MapperMaterial;

public class MaterialMysqlRepository implements MaterialRepository {

	private Connection conn;
	private MapperMaterial mapper;
	
	public MaterialMysqlRepository() {
		this.mapper = new MapperMaterial();
	}
	
	@Override
	public Material insert(Material obj) {
		this.conn = MysqlConnection.getConnection();
		obj.setId(-1);
		try {
			var query = "INSERT INTO materials Values(?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, obj.getName());
			preparedStmt.setInt(3, obj.getYear());
			preparedStmt.setTimestamp(4, Timestamp.valueOf(obj.getDateCreated()));
			preparedStmt.setString(5, obj.getUrlImage());
			preparedStmt.setString(6, obj.getUrlDetails());
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
	public void update(Material obj) {
		// TODO Auto-generated method stub
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "UPDATE materials SET name = ?, year = ?, url_image = ?, url_details = ? WHERE id = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, obj.getName());
			preparedStmt.setInt(2, obj.getYear());
			preparedStmt.setTimestamp(3, Timestamp.valueOf(obj.getDateCreated()));
			preparedStmt.setString(4, obj.getUrlImage());
			preparedStmt.setString(5, obj.getUrlDetails());
			preparedStmt.setInt(6, obj.getId());
			preparedStmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
	}

	@Override
	public int getTotal() {
		int total = 0;
		this.conn = MysqlConnection.getConnection();
		try {
			var query = "SELECT count(*) as total from materials";
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
	public List<Material> getAll(int start, int limit) {
		this.conn = MysqlConnection.getConnection();
		var materials = new ArrayList<Material>();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT * FROM viculturadb.materials LIMIT " + start + ", " + limit;
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) materials.add(this.mapper.map(rset));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return materials;
	}

	@Override
	public Material getById(int id) {
		this.conn = MysqlConnection.getConnection();
		var material = new Material();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT * FROM viculturadb.materials WHERE id = " + id;
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) material = this.mapper.map(rset);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return material;
	}

	@Override
	public Material getByName(String name) {
		this.conn = MysqlConnection.getConnection();
		var material = new Material();
		try {
			Statement stmt = conn.createStatement();
			var query = "SELECT * FROM viculturadb.materials WHERE name = " + name;
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) material = this.mapper.map(rset);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		MysqlConnection.closeConnection();
		return material;
	}

}
