package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.Tablero;

public class TestsHeuristicas {

	Tablero tableroOrdenado = new Tablero("123456780");
	Tablero tablero32 = new Tablero("123456708");
	Tablero tableroRandom = new Tablero("013425786");
	Tablero tableroRandom2 = new Tablero("023451768");

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01ManhattanDevuelve1() {
		assertEquals(1, tablero32.getManhattan());
	}

	@Test
	public void test02ManhattanDevuelve4() {
		assertEquals(4, tableroRandom.getManhattan());
	}
	
	@Test
	public void test03ManhattanDevuelve5() {
		assertEquals(5, tableroRandom2.getManhattan());
	}
	
	@Test
	public void test04ManhattanDevuelve0() {
		assertEquals(0, tableroOrdenado.getManhattan());
	}

	@Test
	public void test05EuclideanDevuelve1() {
		assertEquals(1.0, tablero32.getEuclidean(), 0.1);
	}

	@Test
	public void test06EuclideanDevuelve4() {
		assertEquals(3.41, tableroRandom.getEuclidean(), 0.01);
	}
	
	@Test
	public void test07InvalidaDevuelve0() {
		assertEquals(0, tableroOrdenado.getInvalid(), 0.01);
	}
	
	@Test
	public void test08InvalidaDevuelve0() {
		assertEquals(0, tableroOrdenado.getInvalid());
	}
	
	@Test
	public void test09InvalidaDevuelve9() {
		Tablero tableroDesordenado = new Tablero("012345678");
		assertEquals(9, tableroDesordenado.getInvalid());
	}


}
