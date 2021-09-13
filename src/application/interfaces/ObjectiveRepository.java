package application.interfaces;

import java.util.List;

import domain.entities.Objective;

public interface ObjectiveRepository extends BaseRepository<Objective> {

	public int getTotalByIdGroup(int idGroup);
	public List<Objective> getAllByIdGroup(int idGroup, int start, int limit);
	public boolean doesObjectBelongToIdUser(int id, int idUser);
	public boolean doesObjectBelongToIdGroupAndIdUser(int id, int idUser, int idGroup);
	public void delete(int id);
}
