package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.Distributer;

@RunWith(BlockJUnit4ClassRunner.class)
public class HashCodeTest {

	@Test
	public void testHashCode() {
		Distributer d1 = new Distributer();
		Distributer d2 = new Distributer();
		System.out.println(d1.equals(d2));
		System.out.println(d2.hashCode());
	}
}
