package domain.validators;

import domain.entities.Material;

public class MaterialValidator extends BaseValidator {

	public MaterialValidator(Material material) {
		super(material);
	}
	
	public boolean isNameValid() {
		var material = (Material) this.entity;
		var name = material.getName();
		if(name.isEmpty()) return false;
		if(name.length() < 2 || name.length() > 1024) return false;
		return true;
	}
	
	public boolean isYearValid() {
		var material = (Material) this.entity;
		var year = material.getYear();
		if(year <= 0) return false;
		return true;
	}
	
	public boolean isImageValid() {
		var material = (Material) this.entity;
		var urlImage = material.getUrlImage();
		if(urlImage.length() > 1024) return false;
		return true;
	}
	
	public boolean isUrlImdbValid() {
		var material = (Material) this.entity;
		var urlDetails = material.getUrlDetails();
		if(urlDetails.length() > 1024) return false;
		return true;
	}
}
