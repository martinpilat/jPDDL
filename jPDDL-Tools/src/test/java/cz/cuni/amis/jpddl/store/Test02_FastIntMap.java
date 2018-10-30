package cz.cuni.amis.jpddl.store;

import org.junit.Test;

import cz.cuni.mff.jpddl.store.FastIntMap;
import cz.cuni.mff.jpddl.store.FastIntMap.ForEachEntry;

public class Test02_FastIntMap {
	
	@Test
	public void test01() {
		FastIntMap<MyInteger> map = new FastIntMap<MyInteger>(100);
		
		final StringBuffer sb = new StringBuffer();
		
		map.forEachEntry(new ForEachEntry<MyInteger>() {

			@Override
			public boolean entry(int key, MyInteger data) {
				sb.append(key + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		if (!sb.toString().equals((""))) {
			throw new RuntimeException("TEST FAILED!");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test02() {
		FastIntMap<MyInteger> map = new FastIntMap<MyInteger>(100);
		
		map.put(2, new MyInteger(2));
		map.put(4, new MyInteger(6));
		map.put(8, new MyInteger(12));
		
		final StringBuffer sb = new StringBuffer();
		
		map.forEachEntry(new ForEachEntry<MyInteger>() {

			@Override
			public boolean entry(int key, MyInteger data) {
				sb.append(key + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		if (!sb.toString().equals(("2->2;4->6;8->12;"))) {
			throw new RuntimeException("TEST FAILED!");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	@Test
	public void test03() {
		FastIntMap<MyInteger> map = new FastIntMap<MyInteger>(100);
		
		map.put(2, new MyInteger(2));
		map.put(4, new MyInteger(6));
		map.put(8, new MyInteger(12));
		
		map.remove(4);
		map.put(5, new MyInteger(7));
		
		final StringBuffer sb = new StringBuffer();
		
		map.forEachEntry(new ForEachEntry<MyInteger>() {

			@Override
			public boolean entry(int key, MyInteger data) {
				sb.append(key + "->" + data + ";");
				return true;
			}
			
		});
		
		System.out.println("TEST: " + sb.toString());
		if (!sb.toString().equals(("2->2;8->12;5->7;"))) {
			throw new RuntimeException("TEST FAILED!");
		}
		
		System.out.println("--// TEST OK //--");
	}
	
	public static void main(String[] args) {
		Test02_FastIntMap test = new Test02_FastIntMap();
		test.test01();
		test.test02();
		test.test03();
	}

}
