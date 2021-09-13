package application.interfaces;

public interface BaseRepository<T> {

	public T insert(T obj);
	public void update(T obj);
}
