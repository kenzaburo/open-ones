package rocky.sizecounter;

/**
 * Size Counter API.
 * @author Thach Le
 */
public interface ISizeCounter {
    /**
     * Count number of step in source file with given encoding.
     * @param filePath
     * @param csName encoding of file. Ex: utf-8
     * @return
     * @throws UnsupportedFileType
     */
    public SourceMetaData countLOC(String filePath, String csName) throws UnsupportedFileType;

    /**
     * Count number of step in source file with unknown encoding.
     * @param filePath
     * @return
     * @throws UnsupportedFileType
     */
    public SourceMetaData countLOC(String filePath) throws UnsupportedFileType;

    /**
     * Count size of none-source file.
     * @param filePath
     * @return
     * @throws UnsupportedFileType
     */
	public SizeMetaData countSize(String filePath) throws UnsupportedFileType;

	/**
	 * Check the supporting of the Counter.
	 * @param extFile extension of file
	 * @return true if the file extension is supported to count size.
	 */
    public boolean isCountable(String extFile);
}
