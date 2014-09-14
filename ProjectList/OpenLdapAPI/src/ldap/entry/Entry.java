package ldap.entry;

import java.io.Serializable;
import java.util.List;

import rocky.common.CommonUtil;

import com.novell.ldap.LDAPAttributeSet;

/**
 * Class Entry.
 * @author HoaNV, ThachLe
 */
public abstract class Entry implements Serializable {

    /** DN of an Entry in LDAP. */
    private String dn;

    /** Name of OU in LDAP. Ex: ou=K21, ou=CTU, ou=Users, dc=my-domain, dc=com */
    private String groupOU;

    /** LDAPAttributeSet of an Entry in LDAP. */
    private LDAPAttributeSet attributeSet;

    /** Constructor non-parameter of Entry Class. */
    public Entry() {
        attributeSet = new LDAPAttributeSet();
    }

    /**
     * Constructor non-parameter of Entry Class.
     * 
     * @param dn
     *            Domain-Name of Entry
     * @param attributeSet
     *            AttributeSet of Entry
     * */
    public Entry(String dn, LDAPAttributeSet attributeSet) {
        this.dn = dn;
        this.attributeSet = attributeSet;
    }

    /**
     * ConfigAttributeSet method used to match attribute in LDAPEntry with
     * attribute in your entity.
     */
    public abstract void configAttributeSet();

    /* =========================GETTER SETTER================= */

    /**
     * getDN it's getter method of DN attribute
     * 
     * @return Domain-Name of Entry
     */
    public final String getDn() {
        return dn;
    }

    /**
     * setDN it's setter method of DN attribute
     */
    public void setDn(String dn) {
        this.dn = dn;
    }

    /**
     * getAttributeSet it's getter of AttributeSet attribute
     * 
     * @return AttributeSet attribute of an Entry
     */
    public final LDAPAttributeSet getAttributeSet() {
        return attributeSet;
    }

    /**
     * setAttributeSet it's setter method of attributeSet attribute
     */
    public void setAttributeSet(LDAPAttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    /**
     * Get value of groupOU.
     * 
     * @return the groupOU
     */
    public String getGroupOU() {
        return groupOU;
    }

    /**
     * Set the value for groupOU.
     * 
     * @param groupOU
     *            the groupOU to set
     */
    public void setGroupOU(String groupOU) {
        this.groupOU = groupOU;
    }

    /**
     * Parse group name from GroupOU. <br/>
     * Ex: GroupOU: ou=K21, ou=CTU, ou=Users, dc=my-domain, dc=com Group = K21.
     * 
     * @return
     */
    public String getGroup() {
        final String GET_GROUP_PATTERN = ".*ou=[.*],.*";
        String group = CommonUtil.parsePattern(this.groupOU, GET_GROUP_PATTERN);

        return group;
    }

    public List<Entry> getChild() {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return null;
    }
}
