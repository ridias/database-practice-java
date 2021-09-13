package domain.entities;

import java.time.LocalDateTime;

public class Objective extends BaseEntity {

	private int minProgress;
	private int maxProgress;
	private int currentProgress;
	private LocalDateTime dateCreated;
	private int idGroup;
	private Material material;
	private int completed;
	
	public Objective() {
		super();
	}

	public int getMinProgress() {
		return minProgress;
	}

	public void setMinProgress(int minProgress) {
		this.minProgress = minProgress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public int getCurrentProgress() {
		return this.currentProgress;
	}
	
	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}
	
	public int getCompleted() {
		return this.completed;
	}
	
	public void setCompleted(int completed) {
		this.completed = completed;
	}
}
