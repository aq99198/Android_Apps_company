package com.oy.hy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ItemShowActivity extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent =  this.getIntent();
		Bundle bundle = intent.getExtras();
		int choose_num = bundle.getInt("item_num");
		
		SimpleAdapter adapter = new SimpleAdapter(this,displaything(choose_num),R.layout.activity_concept,
		        new String[]{"title","info","img"},
		        new int[]{R.id.concept_t1,R.id.concept_t2,R.id.concept_img});
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> displaything(int num) {
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		switch(num) {
			case 0:  //高精度投料机
			 map.put("title", getString(R.string.ouritem1));
			 map.put("info", getString(R.string.introduce_thing));
			 map.put("img", R.drawable.pic_company);
			 list.add(map);
			break;
			case 1:  //数字水位计
				 map.put("title", getString(R.string.ouritem2));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.ouritem2_pic);
				 list.add(map);
				break;
			case 2:  //仓库温控仪
				map.put("title", getString(R.string.ouritem3));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.pic_company);
				 list.add(map);
				break;
			case 3:
				 map.put("title", getString(R.string.introduce));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.pic_company);
				 list.add(map);	
				break;
			case 4:
				 map.put("title", getString(R.string.introduce));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.pic_company);
				 list.add(map);
				break;
			default:
				break;
		}
		return list;
	 }
	
	
}
