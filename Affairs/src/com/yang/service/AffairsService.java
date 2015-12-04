package com.yang.service;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.yang.dao.AffairsDao;
import com.yang.domain.Affairs;
import com.yang.exception.MyException;
 
/**
 *  
 * @author Yang
 * 几大流程
 *新增、开始、完结、查看所有、终止
 */
public class AffairsService {
	private AffairsDao affairsDao; 
	public AffairsService(Context context){
		affairsDao=new AffairsDao(context);
	}
	public void createOne(Affairs affairs) throws MyException{
		if(affairs==null){
			throw new MyException("affairs is null");
		}
		affairs.setCreate_time(new Date());
		affairs.setStatus(Constrants.STATUS_TODO);
		affairsDao.add(affairs); 
	}
	public void startOne(Affairs affairs) throws MyException{
		if(affairs==null){
			throw new MyException("affairs is null");
		}
		affairs.setStart_time(new Date());
		affairs.setStatus(Constrants.STATUS_DOING);
		affairsDao.update(affairs);
	}
	public void completeOne(Affairs affairs) throws MyException{
		if(affairs==null){
			throw new MyException("affairs is null");
		}
		affairs.setDone_time(new Date());
		affairs.setStatus(Constrants.STATUS_DONE);
		Duration duration=new Duration(affairs.getStart_time(),affairs.getDone_time());
		affairs.setDuration(duration.toString());
		affairsDao.update(affairs);
	}
	public List<Affairs> getAll() throws MyException{
		return affairsDao.selectAll();
	}
	public void terminateOne(Affairs affairs) throws MyException{
		if(affairs==null){
			throw new MyException("affairs is null");
		}
		affairs.setStatus(Constrants.STATUS_TERMINATE);
		affairsDao.update(affairs);
	}
	public Affairs getOne(int id) throws MyException{
		return affairsDao.select(id);
	}
	public List<Affairs> getAllStatus(String status) throws MyException {
		// TODO Auto-generated method stub
		return affairsDao.selectByStatus(status);
	}
}
