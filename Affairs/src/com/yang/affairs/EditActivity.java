package com.yang.affairs;

import com.yang.domain.Affairs;
import com.yang.exception.MyException;
import com.yang.service.AffairsService;
import com.yang.service.Constrants;
import com.yang.utils.DateUtil;
import com.yang.utils.DialogUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends Activity implements OnClickListener{
	private int[] viewId={
			R.id.id,R.id.what,R.id.why,R.id.how,R.id.comment,R.id.spinner,
			R.id.status,R.id.create_time,R.id.start_time,R.id.done_time,
			R.id.duration
	};
	private String[]types={
		Constrants.TYPE_COMPANY,Constrants.TYPE_LIFE,Constrants.TYPE_PROGRAM,
		Constrants.TYPE_READ,Constrants.TYPE_OTHER
	};
	private String mode;
	private int id;
	private AffairsService service;
	private Affairs affairs;
	private Button save;
	private Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		init();
	}
	private void init() {
		service=new AffairsService(this);
		save=(Button) findViewById(R.id.save);
		save.setOnClickListener(this);
		spinner=(Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
		spinner.setAdapter(adapter);
		Intent i=getIntent();
		mode=i.getExtras().getString("mode");
		id=i.getExtras().getInt("id");
		if(mode.equals(Constrants.MODE_START)){
			modeStart();
		}
		if(mode.equals(Constrants.MODE_CREATE)){
			modeCreate();
		}
		if(mode.equals(Constrants.MODE_TERMINATE)){
			modeTerminate();
		}
		if(mode.equals(Constrants.MODE_COMPLETE)){
			modeComplete();
		}
		if(mode.equals(Constrants.MODE_WATCH)){
			modeWatch();
		}
		
	}
	private String getSpinnerValue(){
		return spinner.getSelectedItem().toString();
	}
	private void modeWatch() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				affairs=service.getOne(id);
				affairs2view(affairs);
			}
		});
	}
	private void modeTerminate() {
		modeWatch();
	}
	private void modeCreate() {
		affairs=new Affairs();
		affairs2view(affairs);
	}
	private void modeStart() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				affairs=service.getOne(id);
				affairs2view(affairs);
			}
		});
	}
	private void modeComplete() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				affairs=service.getOne(id);
				affairs2view(affairs);
			}
		});
	}
	//数据到试图的映射
	private void affairs2view(Affairs aff){
		String[] values={
				String.valueOf(aff.getId()),
				aff.getWhat(),aff.getWhy(),aff.getHow(),
				aff.getComment(),aff.getType(),aff.getStatus(),
				DateUtil.getDefaultFormat(aff.getCreate_time()),
				DateUtil.getDefaultFormat(aff.getStart_time()),
				DateUtil.getDefaultFormat(aff.getDone_time()),
				aff.getDuration()
		}; 
		for (int i = 0; i < values.length; i++) {
			if(viewId[i]==R.id.spinner){
				String type=aff.getType();
				if(type==null){
					type="";
				}
				int position=0;
				for (int j = 0; j < types.length; j++) {
					if(type.equals(types[j])){
						position=j;
						break;
					}
				}
				spinner.setSelection(position);
				continue;
			}
			EditText t=(EditText)findViewById(viewId[i]);
			t.setText(values[i]);
		}
	}
	//试图到数据的映射
	private void view2affairs(){
		affairs.setWhat(viewid2String(R.id.what));
		affairs.setWhy(viewid2String(R.id.why));
		affairs.setHow(viewid2String(R.id.how));
		affairs.setComment(viewid2String(R.id.comment));
		String type=getSpinnerValue();
		affairs.setType(type);
		affairs.setStatus(viewid2String(R.id.status));
	}
	private String viewid2String(int id){
		EditText t=(EditText)findViewById(id);
		return t.getText().toString();
	}
	@Override
	public void onClick(View v) {
		view2affairs();
		if(mode.equals(Constrants.MODE_START)){
			saveStart();
		}
		if(mode.equals(Constrants.MODE_CREATE)){
			saveCreate();
		}
		if(mode.equals(Constrants.MODE_TERMINATE)){
			saveTerminate();
		}
		if(mode.equals(Constrants.MODE_COMPLETE)){
			saveComplete();
		}
		if(mode.equals(Constrants.MODE_WATCH)){
			saveWatch();
		}
		DialogUtils.showDialog(EditActivity.this, "保存成功");
	}
	private void saveWatch() {
		// TODO Auto-generated method stub
		
	}
	private void saveComplete() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				service.completeOne(affairs);
			}
		});
	}
	private void saveTerminate() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				service.terminateOne(affairs);
			}
		});
	}
	private void saveCreate() {
		ExceptionHandler.handle(this, new MyRun(){
			@Override
			public void run() throws MyException {
				service.createOne(affairs);
			}
		});
	}
	private void saveStart() {
		ExceptionHandler.handle(this, new MyRun(){

			@Override
			public void run() throws MyException {
				service.startOne(affairs);
			}
		});
	}
}
