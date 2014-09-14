package ldap.entry;

import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPEntry;

/*
 * Class GroupEntry.
 * @author HoaNV, ThachLe
 */
public class GroupEntry extends Entry {

    /** Logical name of group. Example: Name of a department. */
    private String ouName;
    private List<Entry> entryList;

    public GroupEntry() {

    }

    /**
     * Prepare an GroupEntry with name and parent group .
     * 
     * @param ouName
     *            Name of sub group
     * @param superDN
     *            Parent group as format of DN. Ex: ou=Users,dc=my-domain,dc=com
     */
    public GroupEntry(String ouName, String superDN) {
        this.ouName = ouName;
        this.setDn("ou=" + ouName + "," + superDN);
        entryList = new ArrayList<Entry>();
        configAttributeSet();
    }

    public GroupEntry(LDAPEntry entry) {
        this.setDn(entry.getDN());
        entryList = new ArrayList<Entry>();
        String[] split = entry.getDN().split(",");
        String ou = split[0].substring(3);
        this.ouName = ou;
        this.setGroupOU(entry.getDN());
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
    }

    public void removeEntry(Entry entry) {
        entryList.remove(entry);
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    @Override
    public void configAttributeSet() {
        this.getAttributeSet().add(new LDAPAttribute("objectClass", new String("organizationalUnit")));
        this.getAttributeSet().add(new LDAPAttribute("ou", ouName));
    }

    @Override
    public List<Entry> getChild() {
        return entryList;
    }

    @Override
    public String getName() {
        return ouName;
    }
}
