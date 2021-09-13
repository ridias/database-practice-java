package domain.entities;

public abstract class BaseEntity {

	private int id;
	
	public BaseEntity() {
		this.id = -1;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
