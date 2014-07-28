/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mks.dms.model;

import java.util.List;

/**
 * This class presents a tree of nodes. Node can be a department.
 * 
 * @author ThachLe
 */
public class MasterNode {
    private String id;
    private String parent;
    private String text;
    private String type;
    private List<MasterNode> children;
    /**
     * Get value of id.
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Get value of parent.
     * @return the parent
     */
    public String getParent() {
        return parent;
    }
    /**
     * Set the value for parent.
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
    /**
     * Get value of text.
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * Set the value for text.
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * Get value of type.
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * Set the value for type.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Get value of children.
     * @return the children
     */
    public List<MasterNode> getChildren() {
        return children;
    }
    /**
     * Set the value for children.
     * @param children the children to set
     */
    public void setChildren(List<MasterNode> children) {
        this.children = children;
    }
}
