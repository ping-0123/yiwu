package com.test.main;

import org.junit.Test;
import org.springframework.util.SimpleIdGenerator;

import com.test.BaseBlockJUnitTest;

public class SimpleIdGeneratorTest extends BaseBlockJUnitTest {
	
	
	@Test
	public void testGenerate(){
		SimpleIdGenerator idGenerator = new SimpleIdGenerator();
		System.err.println(idGenerator.generateId());
		System.err.println(idGenerator.generateId());
	}
}
