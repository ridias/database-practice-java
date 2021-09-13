package application.interfaces;

import java.sql.ResultSet;

public interface Mapper<T> {
	
	public T map(ResultSet rset);
}
