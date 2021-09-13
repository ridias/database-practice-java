package infrastructure.mapper;

import java.sql.ResultSet;

import application.interfaces.Mapper;
import domain.entities.Group;

public class MapperGroup implements Mapper<Group> {

	@Override
	public Group map(ResultSet rset) {
		var group = new Group();
		try{
			group.setId(rset.getInt("id"));
		
			group.setName(rset.getString("name"));
			group.setDescription(rset.getString("description"));
			group.setDateCreated(rset.getTimestamp("date_created").toLocalDateTime());
			group.setIdUser(rset.getInt("id_user"));
			
			var dateEnd = rset.getTimestamp("date_end");
			if(dateEnd == null) {
				group.setDateEnd(null);
			}else {
				group.setDateEnd(dateEnd.toLocalDateTime());
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return group;
	}

	
}
