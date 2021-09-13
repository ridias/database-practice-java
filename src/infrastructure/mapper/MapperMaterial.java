package infrastructure.mapper;

import java.sql.ResultSet;

import application.interfaces.Mapper;
import domain.entities.Material;

public class MapperMaterial implements Mapper<Material>{

	@Override
	public Material map(ResultSet rset) {
		var material = new Material();
		
		try {
			material.setId(rset.getInt("id"));
			material.setName(rset.getString("name"));
			material.setYear(rset.getInt("year"));
			material.setDateCreated(rset.getTimestamp("date_created").toLocalDateTime());
			material.setUrlImage(rset.getString("url_image"));
			material.setUrlDetails(rset.getString("url_details"));
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return material;
	}

}
