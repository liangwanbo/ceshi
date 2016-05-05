package com.cblue.xutils.sample;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.lidroid.xutils.sample.R;


@ContentView(R.layout.mydbutils)
public class DBUtilsActivity extends Activity {
	
	@ViewInject(R.id.dbutils_et)
	private EditText dbUtils_EditText;
	@ViewInject(R.id.dbutils_save_btn)
	private Button dbUtils_Save_Button;
	@ViewInject(R.id.dbutils_select_btn)
	private Button dbUtils_Select_Button;
	@ViewInject(R.id.dbutils_tv)
	private TextView dbUtils_TextView;
	
	private DbUtils dbUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    ViewUtils.inject(this);	
	    //根据构造方法不同，可以设置不同的路径和库名，如果不设置，使用默认
	    dbUtils = DbUtils.create(this, "mydb");
	}
	
	@OnClick({R.id.dbutils_save_btn,R.id.dbutils_select_btn})
	public void dbUtilsSaveButtonOnClick(View view){
		if(view.getId()==R.id.dbutils_save_btn){
		 String name = dbUtils_EditText.getText().toString();
		 save(name);
		}else{
			 select();
		}
	}	
	/**
	 * 保存数据
	 */
	private void save(String name){
		Student student = new Student();
		student.setName(name);
		try {
			dbUtils.saveBindingId(student);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	}
	
	
	/**
	 * 查询数据
	 */
	private void select(){
		try {
			List<Student> students = dbUtils.findAll(Student.class);
			StringBuffer stringBuffer = new StringBuffer();
			for(Student student:students){
				stringBuffer.append(student.toString()+"|"); 
				
			}
			dbUtils_TextView.setText(stringBuffer.toString());
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
