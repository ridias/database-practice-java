package infrastructure.mapper;

import java.sql.ResultSet;

import application.interfaces.Mapper;
import domain.entities.Material;
import domain.entities.Objective;

public class MapperObjective implements Mapper<Objective> {

	@Override
	public Objective map(ResultSet rset) {
		var material = new Material();
		var objective = new Objective();
		
		try {
			material.setName(rset.getString(1));
			material.setYear(rset.getInt(2));
			material.setUrlImage(rset.getString(3));
			material.setUrlDetails(rset.getString(4));
			
			objective.setId(rset.getInt(0));
			objective.setMinProgress(rset.getInt(5));
			objective.setMaxProgress(rset.getInt(6));
			objective.setCurrentProgress(rset.getInt(7));
			objective.setDateCreated(rset.getTimestamp(8).toLocalDateTime());
			objective.setIdGroup(rset.getInt(9));
			
			objective.setMaterial(material);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return objective;
	}

}
