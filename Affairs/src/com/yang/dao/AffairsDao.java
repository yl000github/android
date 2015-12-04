package com.yang.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.yang.domain.Affairs;
import com.yang.exception.MyException;

import android.content.Context;

public class AffairsDao {  
	  
    @SuppressWarnings("unused")
	private Context context;  
    private Dao<Affairs, Integer> affairsDaoOpe;  
    private DatabaseHelper helper;  
  
    @SuppressWarnings("unchecked")
	public AffairsDao(Context context)  
    {  
        this.context = context;  
        try  
        {  
            helper = DatabaseHelper.getHelper(context);  
            affairsDaoOpe = helper.getDao(Affairs.class);  
        } catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 增加一个事务
     * @param affairs 
     * @throws MyException 
     */  
    public void add(Affairs affairs) throws MyException  
    {  
        try  
        {  
        	affairsDaoOpe.create(affairs);  
        } catch (SQLException e)  
        {  
            e.printStackTrace();
            throw new MyException("exception occured in add");
        }  
    }//...other operations  
    public void delete(int id) throws MyException{
		try {
			affairsDaoOpe.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new MyException("exception occured in delete");
		}
	}
	public void update(Affairs data) throws MyException{
		try {
			affairsDaoOpe.update(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new MyException("exception occured in update");
		}
	}
	public Affairs select(int id) throws MyException{
		try {
			return affairsDaoOpe.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("exception occured in select");
		}
	}
	public List<Affairs> selectByStatus(String status) throws MyException{
		try {
			return affairsDaoOpe.queryBuilder().where().eq("status", status)
					.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("exception occured in select");
		}
	}
	public List<Affairs> selectAll() throws MyException{
		List<Affairs> list=null;
		try {
			list= affairsDaoOpe.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("exception occured in add");
		}
		return list;
	}
}
