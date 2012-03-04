/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package openones.stardictcore;

/**
 * @author Thach.Le
 */
public class DictInfo {
    /** . */

    private String intro;

    /** . */
    private String version;

    /** . */
    private String wordcount;

    /** . */
    private String idxfilesize;

    /** . */
    private String bookname;

    /** . */
    private String author;

    /** . */
    private String website;

    /** . */
    private String description;

    /** . */
    private String date;

    /**
     * Get value of intro.
     * @return the intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * Set the value for intro.
     * @param intro the intro to set
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * Get value of version.
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set the value for version.
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Get value of wordcount.
     * @return the wordcount
     */
    public String getWordcount() {
        return wordcount;
    }

    /**
     * Set the value for wordcount.
     * @param wordcount the wordcount to set
     */
    public void setWordcount(String wordcount) {
        this.wordcount = wordcount;
    }

    /**
     * Get value of idxfilesize.
     * @return the idxfilesize
     */
    public String getIdxfilesize() {
        return idxfilesize;
    }

    /**
     * Set the value for idxfilesize.
     * @param idxfilesize the idxfilesize to set
     */
    public void setIdxfilesize(String idxfilesize) {
        this.idxfilesize = idxfilesize;
    }

    /**
     * Get value of bookname.
     * @return the bookname
     */
    public String getBookname() {
        return bookname;
    }

    /**
     * Set the value for bookname.
     * @param bookname the bookname to set
     */
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    /**
     * Get value of author.
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the value for author.
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get value of website.
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the value for website.
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get value of description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get value of date.
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value for date.
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Create dictionary header for Stardict.
     * @return String of dictionary header
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final String nl = "\n";

        StringBuffer sb = new StringBuffer();
        sb.append(intro + nl)
          .append("version=").append(version).append(nl)
          .append("wordcount=").append(wordcount).append(nl)
          .append("idxfilesize=").append(idxfilesize).append(nl)
          .append("bookname=").append(bookname).append(nl)
          .append("author=").append(author).append(nl)
          .append("website=").append(website).append(nl)
          .append("description=").append(description).append(nl)
          .append("date").append(date).append(nl);

        return sb.toString();
    }

}
