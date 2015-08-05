package com.springmvc.marketbarn.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class LeftItemServiceTest {
	
	@Autowired
	LeftItemService service = new LeftItemService(); 

	@Test
	public void testUploadXmlFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateRemainsXmlFile() {
		service.createRemainsXmlFile();
	}

	@Test
	public void testAddLeftItems() {
		fail("Not yet implemented");
	}

}
