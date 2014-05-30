package openones.mail;

import java.io.File;
import java.util.ArrayList;


public class MailInfo {
	private String mailFrom;
	private String[] mailTo;
	private File[] attachedFile;
	private String mailSubject;
	private String mailBody;
	private ImageFormat[] image;
	
	
	/**
     * @return the image
     */
    public ImageFormat[] getImage() {
        return image;
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(ArrayList<ImageFormat> list) {
        this.image = new ImageFormat[list.size()];
        for(int i = 0;i < list.size(); i++){
            image[i] = list.get(i);
        }
    }
    
    public void setMailTo(ArrayList<String> list){
		this.mailTo = new String[list.size()];
		for (int i = 0; i < list.size(); i++){
			mailTo[i] = list.get(i);
		}
	}
	/**
	 * @param mailBody the mailBody to set
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	/**
	 * @return the mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailTo the mailTo to set
	 */
	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}
	/**
	 * @return the mailTo
	 */
	public String[] getMailTo() {
		return mailTo;
	}
	/**
	 * @param attachedFile the attachedFile to set
	 */
	/*public void setAttachedFile(ArrayList<File> listAttachedFile) {
		this.attachedFile = new File[listAttachedFile.size()];
        for(int i = 0;i < listAttachedFile.size(); i++){
            attachedFile[i] = listAttachedFile.get(i);
        }
	}*/
	
	/**
	 * @param attachedFile the attachedFile to set
	 */
	public void setAttachedFile(ArrayList<String> listAttachedFileName) {
		this.attachedFile = new File[listAttachedFileName.size()];
		for(int i = 0;i < listAttachedFileName.size(); i++){
            attachedFile[i] = new File(listAttachedFileName.get(i));
        }
	}
	
	/**
	 * @return the attachedFile
	 */
	public File[] getAttachedFile() {
		return attachedFile;
	}
	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}
	
}
