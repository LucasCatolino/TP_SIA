package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.Tablero;

public class TestsTableroResoluble {

	@Test
	public void test01Resoluble() {
		Tablero tablero = new Tablero("123456780");
		assertTrue(tablero.isSolvable());
	}
	
	@Test
	public void test02Resoluble() {
		Tablero tablero = new Tablero("182043765");
		assertTrue(tablero.isSolvable());
	}
	
	@Test
	public void test03Resoluble() {
		Tablero tablero = new Tablero("123405867");
		assertTrue(tablero.isSolvable());
	}
	
	@Test
	public void test04Resoluble() {
		Tablero tablero = new Tablero("528417036");
		assertTrue(tablero.isSolvable());
	}
	
	@Test
	public void test05NoResuloble() {
		Tablero tablero = new Tablero("812043765");
		assertFalse(tablero.isSolvable());
	}
	
	@Test
	public void test06NoResuloble() {
		Tablero tablero = new Tablero("123456087");
		assertFalse(tablero.isSolvable());
	}
	
	@Test
	public void test07NoResuloble() {
		Tablero tablero = new Tablero("134506728");
		assertFalse(tablero.isSolvable());
	}
	
	@Test
	public void test08NoResuloble() {
		Tablero tablero = new Tablero("123405768");
		assertFalse(tablero.isSolvable());
	}
	
	@Test
	public void test09NoResuloble() {
		Tablero tablero = new Tablero("123415768");
		assertFalse(tablero.isSolvable());
	}
	
	@Test
	public void test10NoResuloble() {
		Tablero tablero = new Tablero("123415068");
		assertFalse(tablero.isSolvable());
	}

}
