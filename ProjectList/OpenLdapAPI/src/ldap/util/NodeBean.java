package ldap.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeBean {

	private String id;
	private String parent;
	private String text;
	private String type;
	/** Unique code . */
	private Map<String, String> a_attr = new HashMap<String, String>();
	private List<NodeBean> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<NodeBean> getChildren() {
		return children;
	}
	public void setChildren(List<NodeBean> children) {
		this.children = children;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    /**
     * Get value of a_attr.
     * @return the a_attr
     */
    public Map<String, String> getA_attr() {
        return a_attr;
    }
    /**
     * Set the value for a_attr.
     * @param a_attr the a_attr to set
     */
    public void setA_attr(Map<String, String> a_attr) {
        this.a_attr = a_attr;
    }
    
    public void addAttr(String key, String value) {
        this.a_attr.put(key, value);
    }

}
