package com.springmvc.marketbarn.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void testEncryptString() 
	{
		String result = null;
		try {
			result = AESUtils.encrypt("root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

	@Test
	public void testDecryptString() 
	{
		String result = null;
		try {
			result = AESUtils.decrypt("5463C31B80FEB1348B3FF9C8677A5FBC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

	@Test
	public void testEncryptStringString() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDecryptStringString() {
		fail("Not yet implemented");
	}

}
