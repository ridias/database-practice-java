package application.interfaces;

import java.util.List;

import domain.entities.Material;

public interface MaterialRepository extends BaseRepository<Material> {

	public int getTotal();
	public List<Material> getAll(int start, int limit);
	public Material getById(int id);
	public Material getByName(String name);
}
