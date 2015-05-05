package com.market.marketbarn.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmlUtils 
{
	
	private XmlUtils() {
		// TODO Auto-generated constructor stub
	}

	public static int createXmlFile(String fileName){
		// 0-fail  1-success 
		int status=0;
		//create document
		Document doc=DocumentHelper.createDocument();
		//root node store
		Element storeElement=doc.addElement("store");
		//commment
		storeElement.addComment("store xml by dom4j");
		
		//child node machine 1
		Element machineElement=storeElement.addElement("machine");
		machineElement.addAttribute("id", "1");
		//machine->item
		Element itemElement=machineElement.addElement("item");
		itemElement.setText("电饭煲");
		//machine->price
		Element priceElement=machineElement.addElement("price");
		priceElement.setText("128");
		//machine->quantity
		Element quantityElement=machineElement.addElement("quantity");
		quantityElement.setText("20");
		
		//child node machine 2
		machineElement=storeElement.addElement("machine");
		machineElement.addAttribute("id", "2");
		itemElement=machineElement.addElement("item");
		itemElement.setText("电风扇");
		priceElement=machineElement.addElement("price");
		priceElement.setText("89");
		quantityElement=machineElement.addElement("quantity");
		quantityElement.setText("12");
		
		//write to file
		try{
			XMLWriter writer=new XMLWriter();
			OutputFormat.createPrettyPrint();
			writer.write(doc);
			writer.close();
			status=1;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}
	
	//xml format
	/**
	 * @param fileName
	 * @return
	 */
	public static int formatXmlFile(String fileName){
		int returnValue=0;
		try{
			SAXReader saxReader=new SAXReader();
			Document doc=saxReader.read(fileName);
			XMLWriter writer=null;
			//format
			OutputFormat format=OutputFormat.createPrettyPrint();
			//xml encode
			format.setEncoding("UTF-8");
//			writer=new XMLWriter(new OutputStreamWriter(
//					new FileOutPutStream("fileName"),
//					format.getEmcoding()),
//					format);
			writer=new XMLWriter(format);
			writer.write(doc);
			writer.close();
			returnValue=1;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return returnValue;
	}
	
	/*public int modiXmlFile(String fileName,String newFileName){
		int status=0;
		try
		{
			SAXReader saxReader=new SAXReader();
			Document doc=saxReader.read(new File(fileName));
			//xpath 查找
			List list=doc.selectNodes("/store/item/@id");
			Iterator iter=list.iterator();
			while(iter.hasNext())
			{
				Attribute attribute = (Attribute) iter.next();
				if( "1".equals(attribute.getName()) )
				{
					attribute.setValue("2");
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}*/

	public int delXmlNode(String fileName){
		int status=0;
		return status;
	}
	/*testing*/
	public static void main(String[] args) {
		createXmlFile("store1.xml");
	}

}


