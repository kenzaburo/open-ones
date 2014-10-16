package mks.dms.dao.controller;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.Rate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RateJpaControllerTest {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");

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
	public void testRateJpaController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEntityManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		ExRateJpaController exRateJpaController = new ExRateJpaController(emf);
		Rate test = new Rate();
//		test.setRank('b');
		test.setReqId(10);
		exRateJpaController.create(test);
		Rate temp = exRateJpaController.findLatestRateByRequestId(10);
//		assertEquals(0, temp.getRank().compareTo('b'));
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testDestroy() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRateEntities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRateEntitiesIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRateCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
