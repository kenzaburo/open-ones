package ldap.test;
import ldap.dao.GroupDAO;
import ldap.entry.GroupEntry;
import ldap.util.ServerConfig;

import org.junit.Assert;
import org.junit.Test;

public class TestGroup {
    private ServerConfig ldapCfg = Config.getServerConfig();
    private String userOU = "ou=Users,dc=fsoft,dc=com,dc=vn";
    
    
    /**
    * Testing add a group into an Organization Unit.
    */
    @Test
    public void testAdd_N_001(){
        GroupEntry entry = new GroupEntry("Thach_CT", "ou=Users," + ldapCfg.getRootDN());
        GroupDAO dao = new GroupDAO(ldapCfg);
        boolean result = dao.add(entry);
        Assert.assertTrue(result);
    }
    

    /**
    * Testing add a sub group into a existing group.
    */
    @Test
    public void testAdd_N_002(){
        GroupDAO dao = new GroupDAO(ldapCfg);
        GroupEntry entry = new GroupEntry("Thach_K01", dao.findGroupByDN("ou=Thach_CT,ou=Users," + ldapCfg.getRootDN()).getDn());
        dao.add(entry);
    }
    
    @Test
    public void testFindGroup_N_001(){
        GroupDAO dao = new GroupDAO(ldapCfg);
        GroupEntry entry = (GroupEntry) dao.findGroupByDN(userOU );
        System.out.println(entry);
    }
}
