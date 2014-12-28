package com.oy.File;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.oy.hy.Lifejob;

import android.content.Context;

public class MyContentHandler extends DefaultHandler{
	String buy, food, wash, clean;
	String tagName;
	private DBManager mgr;
	private Lifejob lifejob;
	private boolean tf_num;
	public MyContentHandler(){
	}
	public MyContentHandler(Context context,boolean tf_num){
		mgr = new DBManager(context);
		lifejob = new Lifejob();
		this.tf_num = tf_num;
	}
	public void startDocument() throws SAXException {
		System.out.println("````````begin````````");
	}

	public void endDocument() throws SAXException {
		System.out.println("````````end````````");
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		tagName = localName;
		if (localName.equals("weekdays")) {
//			//获取标签的全部属性
			for (int i = 0; i < attr.getLength(); i++) {
				//System.out.println(attr.getLocalName(i) + "=" + attr.getValue(i));
				lifejob._id = Integer.parseInt(attr.getValue(i));
			}
		}
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		//在workr标签解析完之后，会打印出所有得到的数据
		tagName = "";
		if (localName.equals("weekdays")) {
			this.outToBD();
		}
	}
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tagName.equals("buy"))
			lifejob.buy = new String(ch, start, length);
		else if (tagName.equals("food"))
			lifejob.food = new String(ch, start, length);
		else if (tagName.equals("wash"))
			lifejob.wash = new String(ch, start, length);
		else if (tagName.equals("clean"))
			lifejob.clean = new String(ch, start, length);
	}

	private void outToBD() {
		if(this.tf_num==true)
		{
			mgr.add(lifejob);
		}
		else {
			mgr.updateData(lifejob);
		}
		System.out.print("updateData");
		System.out.println();

	}
}
