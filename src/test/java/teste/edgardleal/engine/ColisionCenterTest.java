package teste.edgardleal.engine;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.edgardleal.engine.ColisionCenter;
import com.edgardleal.engine.Movable;

public class ColisionCenterTest extends TestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVerifica() {
		ColisionCenter lista = new ColisionCenter();
		for (int i = 0; i < 2; i++) 
			lista.add(getMovable());
		System.out.println("Resultdo : " +lista.verifica());
	}
	
	private Movable getMovable(){
		Movable m = new Movable();
		m.setWidth(10);
		m.setHeight(10);
		m.setPosition((int)(Math.random()*500), (int)(Math.random()*500));
		return m;
	}
}
