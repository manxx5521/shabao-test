package com.xiaoshabao.shabaotest.base;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;

public class VectorTest {

	private Vector<String> getVector() {
		Vector<String> vc = new Vector<String>();
		vc.add("aa");
		vc.add("bb");
		vc.add("cc");
		return vc;
	}

	/**
	 * Iterator遍历方式
	 */
	@Test
	public void traverseIterator() {
		Vector<String> vc = getVector();

		Iterator<String> iter = vc.iterator();

		while (iter.hasNext()) {

			String value = iter.next();

			System.out.println(value);

		}

	}

	/**
	 * Enumeration 遍历方式
	 */
	@Test
	public void traverseEnumeration() {
		Vector<String> vc = getVector();
		Enumeration<String> enu = vc.elements();

		while (enu.hasMoreElements()) {

			String value = enu.nextElement();

			System.out.println(value);

		}

	}

}
