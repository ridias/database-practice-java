package domain.validators;

import domain.entities.Objective;

public class ObjectiveValidator extends BaseValidator {

	public ObjectiveValidator(Objective objective) {
		super(objective);
	}
	
	public boolean isMinProgressValid() {
		var objective = (Objective) this.entity;
		return objective.getMinProgress() >= 0;
	}
	
	public boolean isMaxProgressValid() {
		var objective = (Objective) this.entity;
		return objective.getMaxProgress() > 0;
	}
	
	public boolean isCurrentProgressValid() {
		var objective = (Objective) this.entity;
		int current = objective.getCurrentProgress();
		return objective.getMinProgress() <= current && current <= objective.getMaxProgress();
	}
	
	public boolean isIdGroupValid() {
		var objective = (Objective) this.entity;
		return objective.getIdGroup() > 0;
	}
	
	public boolean isIdMaterialValid() {
		var objective = (Objective) this.entity;
		return objective.getMaterial().getId() > 0;
	}
}
