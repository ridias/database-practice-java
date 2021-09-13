package domain.validators;

import domain.entities.Group;

public class GroupValidator extends BaseValidator {

	public GroupValidator(Group group) {
		super(group);
	}
	
	public boolean isNameValid() {
		var group = (Group) this.entity;
		var name = group.getName();
		if(name.isEmpty()) return false;
		if(name.length() < 2 || name.length() > 100) return false;
		return true;
	}
	
	public boolean isDescriptionValid() {
		var group = (Group) this.entity;
		var description = group.getDescription();
		if(description.isEmpty()) return false;
		if(description.length() < 2 || description.length() > 150) return false;
		return true;
	}
	
	public boolean isIdUserValid() {
		var group = (Group) this.entity;
		var idGroup = group.getIdUser();
		return idGroup > 0;
	}
}
