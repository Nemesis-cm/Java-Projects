package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VendingMachineAuditDaoFileImplTest {
    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);


    public VendingMachineAuditDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testWriteAuditEntry() throws VendingMachinePersistenceException {
        //ARRANGE
        String entry = "One Twix removed.";

        //ACT
        testAuditDao.writeAuditEntry(entry);

    }

}