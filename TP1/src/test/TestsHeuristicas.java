package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.Tablero;

public class TestsHeuristicas {

	Tablero tablero32 = new Tablero("123456708");
	Tablero tableroRandom = new Tablero("013425786");

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
	public void test01EuclideanDevuelve1() {
		assertEquals(1.0, tablero32.getEuclidean(), 0.1);
	}

	@Test
	public void test02EuclideanDevuelve4() {
		assertEquals(3.41, tableroRandom.getEuclidean(), 0.01);
	}

}
