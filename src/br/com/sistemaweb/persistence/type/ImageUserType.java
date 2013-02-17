package br.com.sistemaweb.persistence.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import br.com.sistemaweb.utils.Utils;

public class ImageUserType implements UserType {


	private static final int[] SQL_TYPES = {Types.BLOB};

    public int[] sqlTypes() { return SQL_TYPES; }
    public Class<WebImage> returnedClass() { return WebImage.class; }
    public boolean equals(Object x, Object y) { return x == y; }
    public Object deepCopy(Object value) { return value; }
    public boolean isMutable() { return false; }

    public Object nullSafeGet(ResultSet resultSet,
                              String[] names,
                              Object owner)
            throws HibernateException, SQLException {

      Blob image = resultSet.getBlob(names[0]);
      byte[] imageBytes = image.getBytes(0,0);
      return resultSet.wasNull() ? null : new WebImage();
    }

    public void nullSafeSet(PreparedStatement statement,
                            Object value,
                            int index)
            throws HibernateException, SQLException {

        if (value == null) {
            statement.setNull(index, Types.BLOB);
        } else {
            try {
				statement.setBlob(index, new FileInputStream(((WebImage)value).getImage()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		return null;
	}
	@Override
	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0.hashCode();
	}
	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}



}
