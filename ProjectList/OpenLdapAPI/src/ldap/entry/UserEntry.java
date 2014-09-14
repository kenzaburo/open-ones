package ldap.entry;

import rocky.common.CHARA;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.util.Base64;

/*
 * UserEntry
 * @author HoaNV, ThachLe
 */
public class UserEntry extends Entry {

    private String uid; // User ID of entry
    private String firstName;
    private String lastName; // Similar with Surname
    private String email;
    private String commonName; // fullname of user
    private String telNumber;
    private String password; // Password of entry

    /**
     * Constructor StudentEntry
     */
    public UserEntry() {

    }

    public UserEntry(String dn, LDAPAttributeSet attributeSet) {
        super(dn, attributeSet);
    }

    /**
     * Constructor StudentEntry Its going to Convert an LDAPEntry become
     * StudentEntry Class.
     * 
     * @param entry
     *            it's object of LDAPEntry Class, it store all attribute in
     *            LDAPEntry
     */
    public UserEntry(LDAPEntry entry) {
        this.setDn(entry.getDN());
        LDAPAttributeSet attrSet = entry.getAttributeSet();
        this.uid = attrSet.getAttribute("uid").getStringValue();

        LDAPAttribute attrMail = attrSet.getAttribute("mail");
        this.email = (attrMail != null) ? attrMail.getStringValue() : CHARA.BLANK;

        this.firstName = attrSet.getAttribute("givenName").getStringValue();
        this.lastName = attrSet.getAttribute("sn").getStringValue();
        this.commonName = attrSet.getAttribute("cn").getStringValue();
        // this.password =
        // Base64.encode(entry.getAttributeSet().getAttribute("userPassword").getStringValue());
        this.password = attrSet.getAttribute("userPassword").getStringValue();

        LDAPAttribute attrPhone = attrSet.getAttribute("telephoneNumber");
        this.telNumber = (attrPhone != null) ? Base64.encode(attrPhone.getStringValue()) : CHARA.BLANK;
    }

    /**
     * Constructor StudentEntry
     * 
     * @param uid
     *            : UserID of an Entry
     * @param groupOU
     *            : Group contains user. Ex: ou=K01, ou=CTU, ou=Users,
     *            dc=my-domain, dc=com
     * @param firstName
     *            : FirstName of a Student
     * @param lastName
     *            : LastName of a Student
     * @param email
     *            : Email of a Student
     * @param commonName
     *            : FullName of a Student
     * @param telNumber
     *            : TelephoneNumber of a Student
     * @param password
     *            : Password of an Entry
     * @param parentOu
     *            :
     */
    public UserEntry(String uid, String groupOU, String firstName, String lastName, String email, String commonName,
            String telNumber, String password) {
        this.uid = uid;
        this.setDn("uid=" + uid + "," + groupOU);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.commonName = commonName;
        this.telNumber = telNumber;
        this.password = password;
        configAttributeSet();
    }

    /**
     * configAttributeSet used to match attribute in LDAPEntry with attribute in
     * your entity.
     */
    @Override
    public void configAttributeSet() {
        this.getAttributeSet().add(new LDAPAttribute("objectClass", new String("inetOrgPerson")));
        this.getAttributeSet().add(new LDAPAttribute("cn", commonName));
        this.getAttributeSet().add(new LDAPAttribute("givenName", firstName));
        this.getAttributeSet().add(new LDAPAttribute("sn", lastName));
        // this.getAttributeSet().add(new LDAPAttribute("uid", uid));
        this.getAttributeSet().add(new LDAPAttribute("userPassword", password));

        if (email != null) {
            this.getAttributeSet().add(new LDAPAttribute("mail", email));
        } else {
            // Do nothing
        }

        if (telNumber != null) {
            this.getAttributeSet().add(new LDAPAttribute("telephoneNumber", telNumber));
        } else {
            // Do nothing
        }
    }

    /* ====================== GETTER AND SETTER ======================== */
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String toString() {
        return "StudentEntry [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", commonName=" + commonName + ", telNumber=" + telNumber + ", password=" + password + "]";
    }
}
