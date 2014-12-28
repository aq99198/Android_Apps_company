package com.oy.hy;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.oy.File.DBManager;
import com.oy.File.HttpDownloader;
import com.oy.File.MyContentHandler;

import android.R.integer;
import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class LifeplanActivity extends ListActivity {
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		display_lifeplan();
	}
 	@Override
 	protected void onDestroy() {
 		super.onDestroy();
 	}
 	
 	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
 		MenuInflater inflater = getMenuInflater();
 		inflater.inflate(R.menu.option_menu, menu);
 		return true;
 	}
 	
 	@Override
 	public boolean onOptionsItemSelected(MenuItem item) {
 		switch (item.getItemId()){
 		case R.id.updata:
 			//http://192.168.31.108:8080/examples/lifelist.xml
 			System.out.print("updata?");
 			System.out.println();
 			UpdataLifeplanDB("http://www.gzhytech.com/hy_work/lifelist.xml",false);
 			System.out.print("updata ok.");
 			System.out.println();
 			display_lifeplan();
 			return true;
 		}
 		return false;
 	}
 	/*
 	 * 设置好布局，方便显示
 	 */
 	private void display_lifeplan(){

 		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.activity_lifeplan,
		        new String[]{"title","info"},
		        new int[]{R.id.title,R.id.info});
 		setListAdapter(adapter);
		
 	}
 	
	 private List<Map<String, Object>> getData() {
		 
		 DBManager bmr = new DBManager(this);

		 if(!bmr.exist()){
			 UpdataLifeplanDB("http://www.gzhytech.com/hy_work/lifelist.xml",true);
		 }
		 Lifejob lifejobs = new Lifejob();
		 lifejobs = bmr.query(getToWeek());
		 
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("title", "买菜");
		 map.put("info", lifejobs.buy);
		 list.add(map);
		 map = new HashMap<String, Object>();
		 map.put("title", "做饭");
		 map.put("info", lifejobs.food);
		 list.add(map);
		 map = new HashMap<String, Object>();
		 map.put("title", "洗碗");
		 map.put("info", lifejobs.wash);
		 list.add(map);
		 map = new HashMap<String, Object>();
		 map.put("title", "清洁");
		 map.put("info", lifejobs.clean);
		 list.add(map);
		  
		 return list;
	 }
	 /*
	  * 获取当天星期
	  */
	 public int getToWeek() {  
		 
		    Calendar calendar = Calendar.getInstance();   
		    int dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		    if(dayIndex==0) dayIndex=7;
		    return dayIndex;  
		} 
	 
	 //第一参数：下载网址
	 //第二参数：是否需要重新获取db,1为需要，2为不需要
	 private void UpdataLifeplanDB(String urlStr,boolean tf_num) {
		
		HttpDownloader httpDownloader = new HttpDownloader();
		String lrc = httpDownloader.download(urlStr);
		System.out.println(lrc);
		try{
			//创建一个SAXParserFactory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			//为XMLReader设置内容处理器
			reader.setContentHandler(new MyContentHandler(this,tf_num));
			//开始解析文件
			reader.parse(new InputSource(new StringReader(lrc)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}


