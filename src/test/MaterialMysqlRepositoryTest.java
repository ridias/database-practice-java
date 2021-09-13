package test;

import infrastructure.repositories.MaterialMysqlRepository;

public class MaterialMysqlRepositoryTest {

	public static void main(String[] args) {
		
		var materialRepository = new MaterialMysqlRepository();
		
		var result = materialRepository.getAll(0, 5);
		
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}

	}
}
