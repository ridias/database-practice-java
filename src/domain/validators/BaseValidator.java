package domain.validators;

import domain.entities.BaseEntity;

public abstract class BaseValidator {

protected BaseEntity entity;
	
	public BaseValidator(BaseEntity entity) {
		this.entity = entity;
	}
	
	public boolean isIdValid() {
		return this.entity.getId() > 0;
	}
}
