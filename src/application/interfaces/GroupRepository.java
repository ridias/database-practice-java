package application.interfaces;

import java.util.List;

import domain.entities.Group;

public interface GroupRepository extends BaseRepository<Group>{
	
	
	public List<Group> getAllByUserId(int idUser);
	public Group getByIdAndIdUser(int id, int idUser);
	public void delete(int id);
	
}
