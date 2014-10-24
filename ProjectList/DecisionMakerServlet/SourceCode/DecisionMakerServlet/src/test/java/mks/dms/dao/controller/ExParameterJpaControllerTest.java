package mks.dms.dao.controller;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.Parameter;
import mks.dms.service.BaseService;
import mks.dms.util.AppCons;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExParameterJpaControllerTest {

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
	public void testExParameterJpaController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParameterByCd() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
		ExParameterJpaController daoCtrl = new ExParameterJpaController(emf);
		List<Parameter> listParameter = daoCtrl.findParameterByCd("Rank");
		assertEquals(5, listParameter.size());
	}

	@Test
	public void testParameterJpaController() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEntityManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
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
	public void testFindParameterEntities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindParameterEntitiesIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindParameter() {
		ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf());
		
		String emailSubject = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_SUBJECT, true);
		assertEquals("[DMS] Khôi phục mật khẩu", emailSubject);
	}

	@Test
	public void testGetParameterCount() {
		fail("Not yet implemented");
	}

}
