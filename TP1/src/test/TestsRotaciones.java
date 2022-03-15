package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import main.Tablero;

public class TestsRotaciones {

	Tablero tablero11 = new Tablero("012345678");
	Tablero tablero12 = new Tablero("102345678");
	Tablero tablero13 = new Tablero("120453786");
	Tablero tablero21 = new Tablero("123045678");
	Tablero tablero22 = new Tablero("123405678");
	Tablero tablero23 = new Tablero("123450678");
	Tablero tablero31 = new Tablero("123456078");
	Tablero tablero32 = new Tablero("123456708");
	Tablero tablero33 = new Tablero("123456780");

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01Creacion() {
		assertEquals(tablero13.getEstado(), "120453786");
	}

	@Test
	public void test02Rotacion_1_1() {
		List<String> rotaciones = tablero11.getRotaciones();

		assertEquals("102345678", rotaciones.get(0));
		assertEquals("312045678", rotaciones.get(1));
	}

	@Test
	public void test03Rotacion_1_2() {
		List<String> rotaciones = tablero12.getRotaciones();

		assertEquals("012345678", rotaciones.get(0));
		assertEquals("120345678", rotaciones.get(1));
		assertEquals("142305678", rotaciones.get(2));
	}

	@Test
	public void test04Rotacion_1_3() {
		List<String> rotaciones = tablero13.getRotaciones();

		assertEquals("102453786", rotaciones.get(0));
		assertEquals("123450786", rotaciones.get(1));
	}

	@Test
	public void test05Rotacion_2_1() {
		List<String> rotaciones = tablero21.getRotaciones();

		assertEquals("023145678", rotaciones.get(0));
		assertEquals("123405678", rotaciones.get(1));
		assertEquals("123645078", rotaciones.get(2));
	}

	@Test
	public void test06Rotacion_2_2() {
		List<String> rotaciones = tablero22.getRotaciones();

		assertEquals("103425678", rotaciones.get(0));
		assertEquals("123045678", rotaciones.get(1));
		assertEquals("123450678", rotaciones.get(2));
		assertEquals("123475608", rotaciones.get(3));
	}

	@Test
	public void test07Rotacion_2_3() {
		List<String> rotaciones = tablero23.getRotaciones();

		assertEquals("120453678", rotaciones.get(0));
		assertEquals("123405678", rotaciones.get(1));
		assertEquals("123458670", rotaciones.get(2));
	}

	@Test
	public void test08Rotacion_3_1() {
		List<String> rotaciones = tablero31.getRotaciones();

		assertEquals("123056478", rotaciones.get(0));
		assertEquals("123456708", rotaciones.get(1));
	}

	@Test
	public void test09Rotacion_3_2() {
		List<String> rotaciones = tablero32.getRotaciones();

		assertEquals("123406758", rotaciones.get(0));
		assertEquals("123456078", rotaciones.get(1));
		assertEquals("123456780", rotaciones.get(2));
	}

	@Test
	public void test10Rotacion_3_3() {
		List<String> rotaciones = tablero33.getRotaciones();

		assertEquals("123450786", rotaciones.get(0));
		assertEquals("123456708", rotaciones.get(1));
	}

}