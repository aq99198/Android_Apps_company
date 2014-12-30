package com.oy.hy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ItemShowActivity extends ListActivity{

	private int choose_num;
	private String[] from =null;
	private int[] to =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent =  this.getIntent();
		Bundle bundle = intent.getExtras();
		choose_num = bundle.getInt("item_num");
		displaything(getthing(choose_num),from,to);

	}
	
	private List<Map<String, Object>> getthing(int num) {
    	
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		switch(num) {
			case 0:  //高精度投料机
			 map.put("title", getString(R.string.ouritem1));
			 map.put("t1_1", getString(R.string.ouritem1_t1_1));
			 map.put("t1_2", getString(R.string.ouritem1_t1_2));
			 map.put("img", R.drawable.company_ouritem1_pic1);
			 map.put("img2", R.drawable.company_ouritem1_pic2);
			 map.put("t2", getString(R.string.ouritem1_t2));
			 map.put("t3", getString(R.string.ouritem1_t3));
			 map.put("img3", R.drawable.company_ouritem1_pic3);
			 from = new String[]{"title","img","t1_1","t1_2","img2","t2","img3","t3"};
			 to = new int[]{R.id.concept_t1,
						R.id.concept_img1,
						R.id.concept_t3,
						R.id.concept_t4,
						R.id.concept_img2,
						R.id.concept_t2,
						R.id.concept_img3,
						R.id.concept_t5
						};
			 list.add(map);
			break;
			case 1:  //数字水位计
				 map.put("title", getString(R.string.ouritem2));
				 map.put("t1_1", getString(R.string.ouritem2_t1_1));
				 map.put("t1_2", getString(R.string.ouritem2_t1_2));
				 map.put("img", R.drawable.company_ouritem2_pic1);
				 map.put("img2", R.drawable.company_ouritem2_pic2);
				 map.put("t2", getString(R.string.ouritem2_t2));
				 list.add(map);
				 from = new String[]{"title","img","t1_1","t1_2","img2","t2"};
				 to = new int[]{R.id.concept_t1,
							R.id.concept_img1,
							R.id.concept_t3,
							R.id.concept_t4,
							R.id.concept_img2,
							R.id.concept_t2
							};
				break;
			case 2:  //仓库温控仪
				map.put("title", getString(R.string.ouritem3));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.pic_company);
				 list.add(map);
				 from = new String[]{"title","img","info"};
				 to = new int[]{R.id.concept_t1,
							R.id.concept_img1,
							R.id.concept_t2,
							//R.id.concept_img2,
							//R.id.concept_t3
							};
				break;
			case 3://网络信道选择器
				 map.put("title", getString(R.string.ouritem4));
				 map.put("t1_1", getString(R.string.ouritem4_t1_1));
				 map.put("t1_2", getString(R.string.ouritem4_t1_2));
				 map.put("img", R.drawable.company_ouritem4_pic1);
				 map.put("img2", R.drawable.company_ouritem4_pic2);
				 map.put("t2", getString(R.string.ouritem4_t2));
				 
				 list.add(map);
				 from = new String[]{"title","img","t1_1","t1_2","img2","t2"};
				 to = new int[]{R.id.concept_t1,
							R.id.concept_img1,
							R.id.concept_t3,
							R.id.concept_t4,
							R.id.concept_img2,
							R.id.concept_t2
							};
				break;
			case 4:
				 map.put("title", getString(R.string.ouritem4));
				 map.put("t1_1", getString(R.string.ouritem4_t1_1));
				 map.put("t1_2", getString(R.string.ouritem4_t1_2));
				 map.put("img", R.drawable.company_ouritem4_pic1);
				 map.put("img2", R.drawable.company_ouritem4_pic2);
				 map.put("t2", getString(R.string.ouritem4_t2));
				 list.add(map);
				 from = new String[]{"title","img","t1_1","t1_2","img2"};
				 to = new int[]{R.id.concept_t1,
							R.id.concept_img1,
							R.id.concept_t3,
							R.id.concept_t4,
							R.id.concept_img2,
							R.id.concept_t2
							};
				break;
			default:
				break;
		}

		return list;
	 }
	private void displaything(List<? extends Map<String, ?>> data ,String[] from, int[] to){
		
		SimpleAdapter adapter = new SimpleAdapter(this,
				data,R.layout.activity_concept,
				from,to);
		setListAdapter(adapter);
	}
	
	
}
