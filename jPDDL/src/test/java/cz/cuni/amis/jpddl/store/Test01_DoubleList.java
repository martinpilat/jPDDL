package cz.cuni.amis.jpddl.store;

import org.junit.Test;

import cz.cuni.mff.jpddl.store.DoubleList;
import cz.cuni.mff.jpddl.store.DoubleList.ForEach;

public class Test01_DoubleList {

	@Test
	public void test01() {
		DoubleList<MyInteger> list = new DoubleList<MyInteger>(20);
		
		list.add(new MyInteger(1));
		list.add(new MyInteger(2));
		list.add(new MyInteger(3));
		
		final StringBuffer sb = new StringBuffer();
		
		list.forEach(new ForEach<MyInteger>() {

			@Override
			public boolean element(int index, MyInteger data) {
				sb.append(index + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		
		if (!sb.toString().equals("0->1;1->2;2->3;")) {
			throw new RuntimeException("TEST FAILED");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test02() {
		DoubleList<MyInteger> list = new DoubleList<MyInteger>(20);
		
		list.add(new MyInteger(1));
		list.add(new MyInteger(2));
		list.add(new MyInteger(3));
		
		list.remove(1);
		list.add(new MyInteger(4));
		
		final StringBuffer sb = new StringBuffer();
		
		list.forEach(new ForEach<MyInteger>() {

			@Override
			public boolean element(int index, MyInteger data) {
				sb.append(index + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		
		if (!sb.toString().equals("0->1;2->3;3->4;")) {
			throw new RuntimeException("TEST FAILED");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test03() {
		DoubleList<MyInteger> list = new DoubleList<MyInteger>(20);
		
		list.add(new MyInteger(1));
		list.add(new MyInteger(2));
		list.add(new MyInteger(3));
		
		list.remove(2);
		list.remove(0);
		list.remove(1);
		
		list.add(new MyInteger(1));
		list.add(new MyInteger(2));
		list.add(new MyInteger(3));
		
		final StringBuffer sb = new StringBuffer();
		
		list.forEach(new ForEach<MyInteger>() {

			@Override
			public boolean element(int index, MyInteger data) {
				sb.append(index + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		
		if (!sb.toString().equals("3->1;4->2;5->3;")) {
			throw new RuntimeException("TEST FAILED");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test04() {
		DoubleList<MyInteger> list = new DoubleList<MyInteger>(20);
		
		list.add(new MyInteger(1));
		
		final StringBuffer sb = new StringBuffer();
		
		list.forEach(new ForEach<MyInteger>() {

			@Override
			public boolean element(int index, MyInteger data) {
				sb.append(index + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		
		if (!sb.toString().equals("0->1;")) {
			throw new RuntimeException("TEST FAILED");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test05() {
		DoubleList<MyInteger> list = new DoubleList<MyInteger>(20);
		
		list.add(new MyInteger(1));
		list.remove(1);
		list.add(new MyInteger(1));
		
		final StringBuffer sb = new StringBuffer();
		
		list.forEach(new ForEach<MyInteger>() {

			@Override
			public boolean element(int index, MyInteger data) {
				sb.append(index + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		
		if (!sb.toString().equals("1->1;")) {
			throw new RuntimeException("TEST FAILED");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	public static void main(String[] args) {
		Test01_DoubleList test = new Test01_DoubleList();
		
		test.test01();
		test.test02();
		test.test03();
		test.test04();
		test.test05();
	}
	
}
