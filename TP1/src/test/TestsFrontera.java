package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import main.Frontera;
import main.Tree;
import main.Frontera.CompareTypes;

public class TestsFrontera {

    Frontera fBPA = new Frontera(CompareTypes.BPA);
    Frontera fBPP = new Frontera(CompareTypes.BPP);

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01isEmptyReturnsTrue() {
        assertTrue(fBPA.isEmpty());
    }

    @Test
    public void test02isEmptyReturnsFalse() {
        fBPA.add(new Tree.Node());
        assertFalse(fBPA.isEmpty());
    }

    @Test
    public void test03isEmptyReturnsTrue() {
        fBPA.add(new Tree.Node());
        fBPA.getFirst();
        assertTrue(fBPA.isEmpty());
    }

    @Test
    public void test04getFirstReturnsEmpty() {
        assertEquals(false, fBPA.getFirst() != null);
    }

    @Test
    public void test05getFirstReturnsPresent() {
        fBPA.add(new Tree.Node());
        assertEquals(true, fBPA.getFirst() != null);
    }

    @Test
    public void test06add() {
        fBPA.add(new Tree.Node());
        assertEquals(false, fBPA.isEmpty());
    }

    @Test
    public void test07sortBPA() {
        Tree.Node node1 = new Tree.Node();
        node1.setDepth(0);
        Tree.Node node2 = new Tree.Node();
        node2.setDepth(1);
        fBPA.add(node2);
        fBPA.add(node1);
        fBPA.sort();
        assertEquals(node1, fBPA.getFirst());
    }

    @Test
    public void test08sortBPP() {
        Tree.Node node1 = new Tree.Node();
        node1.setDepth(0);
        Tree.Node node2 = new Tree.Node();
        node2.setDepth(1);
        fBPP.add(node2);
        fBPP.add(node1);
        fBPP.sort();
        assertEquals(node2, fBPP.getFirst());
    }

}
