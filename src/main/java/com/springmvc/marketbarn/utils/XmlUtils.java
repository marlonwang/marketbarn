package com.springmvc.marketbarn.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils 
{
	
/*	public static void main(String[] args)
	{
	 final String xmlPath = "D:"+File.separator+"storeLeft.xml";
     Map<String, String> values = new HashMap<String, String>();
     List<String> attributeList = new ArrayList<String>();
     attributeList.add("lft_item_barcode");
     attributeList.add("9787115191120");
     values.put("lft_item_num", "11");
     values.put("lft_count_time", "2015-05-23 01:54:06");
     
     
     
     attributeList.add("name");
     attributeList.add("小明");
     values.put("age", "20");
     values.put("sex", "男");
     
     //设置根节点
   	 Document doc = DocumentHelper.createDocument();
   	 Element roots = DocumentHelper.createElement("leftItems");
   	 doc.setRootElement(roots);
   	 int count = attributeList.size()/2;
   	 for(int i = 0;i<count;i++)
   	 {
   		buildXML(values,attributeList,doc,xmlPath); 
   	 }
   	 
   	// readXML(new File(xmlPath));
	}*/
	
	/**
	 * 生成xml文件
	 * @param values
	 * @param attribute
	 * @param doc
	 * @param xmlPath
	 */
	public static void buildXML(Map<String, String> values,String attribute,Document doc,String xmlPath)
	{
		Set<String> keySet = new HashSet<String>();
		List<String> keyList = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		keySet = values.keySet();
		for(String s : keySet)
		{
			if(null == s)
				s="";
			value.add(values.get(s));
		}
		Iterator<String> ss = keySet.iterator();
		while(ss.hasNext())
		{
			keyList.add(ss.next());
		}

		//System.out.println("key"+keyList);
		//System.out.println("value"+value);
		Element roots = doc.getRootElement();
		Element root = roots.addElement("item");
		
		//设置根节点的属性值
		String[] attr = attribute.split(":");
		root.setAttributeValue(attr[0],attr[1]);
		
		//添加子节点
		for(String s : keyList)
		{
			if(null == s)
			{
				s="";
			}
			Element e = root.addElement(s);
			e.setText(values.get(s));
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		try
		{
			XMLWriter writer =  new XMLWriter(new FileOutputStream(new File(xmlPath)), format);
		    writer.write(doc);
		    writer.close();
			
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("UnsupportedEncodingException");
		} catch (FileNotFoundException e)
		{
			System.out.println("FileNotFoundException");
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}
	}
	
	/**
	 * 读取XML文件
	 * @param XMLPath
	 */
	@SuppressWarnings("unchecked")
	public static void readXML(File xmlFile)
	{
		SAXReader reader = new SAXReader();
		Document doc = null;
	    List<String> attribute = new ArrayList<String>();
	    List<String> keyList = new ArrayList<String>();
	    List<String> valueList = new ArrayList<String>();
 		try
		{
			doc = reader.read(xmlFile);
		} catch (DocumentException e)
		{
			System.out.println("DocumentException");
		}
		Element roots = doc.getRootElement();
		//获取根节点下面所有的子节点
		List<Element> elements = roots.elements();
		//得到第二节点的属性和值
		for(Element e : elements)
		{
			attribute.add(e.attribute(0).getName()) ;
			attribute.add(e.attributeValue(e.attribute(0).getName())) ;
			Iterator<Element> secondElement = e.elementIterator();
			while(secondElement.hasNext())
			{
				Element seconde = secondElement.next();
				keyList.add(seconde.getName());
				valueList.add(seconde.getText());
			}
			
		}
		System.out.println("sudent 节点上的属性"+attribute);
		//获取属性节点
		System.out.println(keyList);
		System.out.println(valueList);	
	}

}
