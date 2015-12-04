package com.yang.affairs;

import java.util.ArrayList;
import java.util.List;

import com.yang.domain.Affairs;
import com.yang.exception.MyException;
import com.yang.service.AffairsService;
import com.yang.service.Constrants;
import com.yang.utils.DialogUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/**
 * ui思路
 * 底部工具栏，依次为：doing,todo,done,terminate
 * 浮动新增
 * 点击进入查看详情--两个模式 信息部分显示和全部显示
 * @author Yang
 *
 */
public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	private ListView listView;
	private Button button;
	private String status=Constrants.STATUS_DOING;
	private AffairsService service;
	List<Affairs> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	private void init() {
		listView=(ListView) findViewById(R.id.listView);
		findViewById(R.id.todo).setOnClickListener(this);
		findViewById(R.id.doing).setOnClickListener(this);
		findViewById(R.id.done).setOnClickListener(this);
		findViewById(R.id.terminate).setOnClickListener(this);
		button = (Button) findViewById(R.id.create);
		button.setOnClickListener(this);
		service=new AffairsService(this);
		refreshView();
	}
	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.create){
			String mode=Constrants.MODE_CREATE;
			startEdit(mode, -1);
			return;
		}
		switch (view.getId()) {
		case R.id.todo:
			status=Constrants.STATUS_TODO;
			break;
		case R.id.doing:
			status=Constrants.STATUS_DOING;
			break;
		case R.id.done:
			status=Constrants.STATUS_DONE;
			break;
		case R.id.terminate:
			status=Constrants.STATUS_TERMINATE;
			break;
		default:
			status=Constrants.STATUS_DOING;
			break;
		} 
		refreshView();
	}
	/**
	 * update data to view
	 */
	private void refreshView() {
		try {
			list=service.getAllStatus(status);
			List<String> strs=new ArrayList<String>();
			for (Affairs affairs : list) {
				strs.add(affairs.getWhat());
			}
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
			listView.setOnCreateContextMenuListener(this);
		} catch (MyException e) {
			e.printStackTrace();
			DialogUtils.showDialog(this, e.toString());
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		T.showShort(this, "click");
		int id=list.get(arg2).getId();
		startEdit(Constrants.MODE_WATCH,id);
	}
	private void startEdit(String mode,int id){
		Intent i=new Intent(this,EditActivity.class);
		i.putExtra("mode", mode);
		i.putExtra("id", id);
		startActivity(i);
	}  
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
//		int location=menuInfo.;
		menu.add(0,0,0,"start");
		menu.add(0,1,0,"complete");
		menu.add(0,2,0,"terminate");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item 
                .getMenuInfo();
		int location=(int) info.id;
		int id=list.get(location).getId();
		String mode;
		switch (item.getItemId()) {
		case 0:
			mode="start";
			break;
		case 1:
			mode="complete";
			break;
		case 2:
			mode="terminate";
			break;
		default:
			mode="start";
			break;
		}
		startEdit(mode, id);
		return super.onContextItemSelected(item);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		refreshView();
	}
}
