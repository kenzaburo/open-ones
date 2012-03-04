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
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This class is used to read idx, ifo,dict files get dictionary information, word and meaning.
 * @author Tuan, Kien, Thach
 */
public class StarDict {
    private static final String EXT_DICT = ".dict";

    private static final String EXT_INDEX = ".idx";

    private static final String EXT_INFO = ".ifo";

    /** Logger. */
    static final Logger LOG = Logger.getLogger("DictFile");

    /** number of the nearest word that is displayed. */
    private final int nearest = 10;

    /** Dict directory.(path to the .dict file). */
    private String strURL = null;

    /** decide if object has loaded the entries. */
    private boolean boolAvailable = false;

    /** ifo file. */
    private IfoFile ifoFile = null;

    /** idx file. */
    private IdxFile idxFile = null;

    /** dict file. */
    private DictFile dictFile = null;

    /**
     * Constructor to load dictionary with given path.
     * @param url Path of one of stardict file or Path of folder contains stardict files
     * @deprecated using StarDict.loadDict(String url)
     */
    public StarDict(String url) {
        File file = new File(url);

        if (!file.isDirectory()) {
            strURL = getFileNameWithoutExtension(url);
            LOG.debug("strURL=" + strURL);
            ifoFile = new IfoFile(strURL + EXT_INFO);
            idxFile = new IdxFile(strURL + EXT_INDEX, ifoFile.getLongWordCount(), ifoFile.getLongIdxFileSize());
            dictFile = new DictFile(strURL + EXT_DICT);
        } else {
            String[] list = file.list();
            // Map<file extension,file name>
            Map<String, String> fileMap = new HashMap<String, String>();

            // Build table to mapping the file extension and file name
            for (int i = list.length - 1; i >= 0; i--) {
                if (list[i].endsWith(EXT_INFO)) {
                    fileMap.put(EXT_INFO, list[i]);
                } else if (list[i].endsWith(EXT_INDEX)) {
                    fileMap.put(EXT_INDEX, list[i]);
                } else if (list[i].endsWith(EXT_DICT)) {
                    fileMap.put(EXT_DICT, list[i]);
                }
            }

            ifoFile = new IfoFile(url + File.separator + fileMap.get(EXT_INFO));
            idxFile = new IdxFile(url + File.separator + fileMap.get(EXT_INDEX), ifoFile.getLongWordCount(),
                    ifoFile.getLongIdxFileSize());
            dictFile = new DictFile(url + File.separator + fileMap.get(EXT_DICT));
        }

        if (ifoFile.isBoolIsLoaded() && idxFile.isLoaded()) {
            boolAvailable = true;
        }
    }

    /**
     * Create a new object of StarDict with files: ifo, idx, dict.
     * @param ifoFile info file
     * @param idxFile index file
     * @param dictFile data dictionary file
     */
    public StarDict(IfoFile ifoFile, IdxFile idxFile, DictFile dictFile) {
        this.ifoFile = ifoFile;
        this.idxFile = idxFile;
        this.dictFile = dictFile;

        if (ifoFile.isBoolIsLoaded() && idxFile.isLoaded()) {
            boolAvailable = true;
        }
    }

    /**
     * Load dictionary from the folder.
     * @param dictRepo path of folder
     * @return object of StarDict
     */
    public static StarDict loadDict(String dictRepo) {
        File file = new File(dictRepo);
        String ifoFilePath;

        IfoFile ifoFile = null;
        IdxFile idxFile = null;
        DictFile dictFile = null;

        if (!file.isDirectory()) {
            String filePathNoExt = getFileNameWithoutExtension(dictRepo);
            LOG.debug("filePathNoExt=" + filePathNoExt);

            ifoFilePath = filePathNoExt + EXT_INFO;
            if (new File(ifoFilePath).isFile()) {
                ifoFile = new IfoFile(ifoFilePath);
            } else {
                return null;
            }

            idxFile = new IdxFile(filePathNoExt + EXT_INDEX, ifoFile.getLongWordCount(), ifoFile.getLongIdxFileSize());
            dictFile = new DictFile(filePathNoExt + EXT_DICT);
        } else {
            String[] list = file.list();
            // Map<file extension,file name>
            Map<String, String> fileMap = new HashMap<String, String>();

            // Build table to mapping the file extension and file name
            for (int i = list.length - 1; i >= 0; i--) {
                if (list[i].endsWith(EXT_INFO)) {
                    fileMap.put(EXT_INFO, list[i]);
                } else if (list[i].endsWith(EXT_INDEX)) {
                    fileMap.put(EXT_INDEX, list[i]);
                } else if (list[i].endsWith(EXT_DICT)) {
                    fileMap.put(EXT_DICT, list[i]);
                }
            }

            if (fileMap.size() < 3) { // There is not enough files of StarDict
                LOG.warn("Only " + fileMap.size() + " file(s) in folder '" + dictRepo + "'");
                return null;
            }

            ifoFile = new IfoFile(dictRepo + File.separator + fileMap.get(EXT_INFO));
            idxFile = new IdxFile(dictRepo + File.separator + fileMap.get(EXT_INDEX), ifoFile.getLongWordCount(),
                    ifoFile.getLongIdxFileSize());
            dictFile = new DictFile(dictRepo + File.separator + fileMap.get(EXT_DICT));

        }

        if ((ifoFile == null) || (idxFile == null) || (dictFile == null)) {
            return null;
        }

        StarDict dict = new StarDict(ifoFile, idxFile, dictFile);
        return dict;
    }

    /**
     * Create an empty Stardict-based dictionary.
     * The dictionary includes 3 files dictName.dict, dictName.idx, dictName.ifo.
     * These 3 files will be generated at path: repoPath + "/" + dictName.
     * @param repoPath folder contains the dictionary files.
     * @param dictName name of the dictionary. It's used for naming of files
     * @return true if success; false if the dictionary is existed or error.
     */
    public static boolean createDict(String repoPath, String dictName, DictInfo dictInfo) {
        String dictPath = repoPath + File.separator + dictName;
        String dictFilePath = dictPath + File.separator + dictName + EXT_DICT;
        String idxFilePath = dictPath + File.separator + dictName + EXT_INDEX;
        String ifoFilePath = dictPath + File.separator + dictName + EXT_INFO;

        if (((new File(dictFilePath)).exists()) || ((new File(idxFilePath)).exists())
                || ((new File(ifoFilePath)).exists())) {
            LOG.debug("The dictionary is already.");
            return false;
        }

        // Create the path folder for dictionary files.
        File dictPathFile = new File(dictPath);
        if (!dictPathFile.exists() || !dictPathFile.isDirectory()) {
            if (!dictPathFile.mkdirs()) {
                LOG.error("Could not create folder '" + dictPath);
                return false;
            }
        }

        try {
            DataOutputStream dt = new DataOutputStream(new FileOutputStream(dictFilePath));
            // java.io.FileWriter fw = new java.io.FileWriter(dictFile);
            String strFirstWord = "@00-database-info\nThis is the " + dictName
                    + " dictionary database of the " + dictName;
            dt.write(strFirstWord.getBytes());
            dt.flush();
            dt.close();

            dt = new DataOutputStream(new FileOutputStream(idxFilePath));
            dt.write('\0');
            dt.write(convertAnInt32(0));
            dt.write(convertAnInt32(strFirstWord.length()));
            dt.flush();
            dt.close();
            FileWriter fw = new FileWriter(ifoFilePath);
            fw.write(dictInfo.toString());
            fw.write("sametypesequence=m\n");
            fw.flush();
            fw.close();

            return true;
        } catch (Exception ex) {
            LOG.error("Could not create new dictionary", ex);
            // Delete created files
            (new File(dictFilePath)).delete();
            (new File(idxFilePath)).delete();
            (new File(ifoFilePath)).delete();
        }

        return false;
    }

    /**
     * get book name of dictionary.
     * @return Book name
     */
    public String getDictName() {
        return ifoFile.getStrBookname().replace("\r", "").trim();
    }

    /**
     * get book version.
     * @return version of a dictionary
     */
    public String getDictVersion() {
        return ifoFile.getStrVersion();
    }

    /**
     * get amount of words in a StarDict dictionary (within 3 files).
     * @return a long totalWord.
     * @author LongNX
     */
    public int getTotalWords() {
        return getWordEntry().size();
    }

    /**
     * get word content from an idx. let say the stardict-dictd-easton-2.4.2, we give this method the idx 1000 and it
     * return us the "diana".
     * @param idx index
     * @return word
     * @author LongNX
     */
    public String getWordByIndex(int idx) {
        String word = getWordEntry().get(idx).getStrLwrWord();
        return word;
    }

    /**
     * lookup a word by its index.
     * @param idx index of a word
     * @return word data
     */
    public String lookupWord(int idx) {
        if (idx < 0 || idx >= idxFile.getLongWordCount()) {
            return "not found";
        }
        WordEntry tempEntry = idxFile.getEntryList().get((int) idx);

        return dictFile.getWordData(tempEntry.getLongOffset(), tempEntry.getLongSize());
    }

    /**
     * lookup a word.
     * @param word that is looked up in database.
     * @return word data
     */
    public String lookupWord(String word) {
        if (!boolAvailable) {
            return "the dictionary is not available";
        }
        int idx = (int) idxFile.findIndexForWord(word);

        return lookupWord(idx);
    }

    /**
     * get a list of word entry.
     * @return list of word entry
     */
    public List<WordEntry> getWordEntry() {
        return idxFile.getEntryList();
    }

    /**
     * load index file and info file.
     */
    public void reLoad() {
        boolAvailable = false;
        ifoFile.reload();
        idxFile.reload();

        if (ifoFile.isBoolIsLoaded() && idxFile.isLoaded()) {
            boolAvailable = true;
        }
    }

    /**
     * get the nearest of the chosen word.
     * @param word that is looked up in database
     * @return a list of nearest word.
     */
    public List<Word> getNearestWords(String word) {
        if (boolAvailable) {
            int idx = (int) idxFile.findIndexForWord(word);
            int nMax = nearest + idx;
            if (nMax > idxFile.getLongWordCount()) {
                nMax = (int) idxFile.getLongWordCount();
            }
            List<Word> wordList = new ArrayList<Word>();
            for (int i = idx; i < nMax; i++) {
                if (i != 0) {
                    Word tempWord = new Word();
                    tempWord.setStrWord(idxFile.getEntryList().get(i).getStrWord());
                    tempWord.setIndex(i);
                    wordList.add(tempWord);
                }
            }
            return wordList;
        }
        return null;
    }

    /**
     * check if a word is in dictionary.
     * @param word that is looked up in database
     * @return true if exists, false otherwise
     */
    public boolean existWord(String word) {
        int wordIndex = (int) idxFile.findIndexForWord(word);

        if (wordIndex >= idxFile.getLongWordCount()) {
            return false;
        }

        String lwrWord = word.toLowerCase();
        if (lwrWord.equals(idxFile.getEntryList().get(wordIndex).getStrLwrWord())) {
            return true;
        }

        return false;
    }

    /**
     * Add list of word to idx, dict file, modify size .ifo file.
     * @param pWord word that is added
     * @param pMean word mean
     * @return true if success
     */
    public boolean addListOfWords(String[] pWord, String[] pMean) {
        if (pWord.length != pMean.length || pWord.length == 0) {
            return false;
        }
        try {
            for (int i = 0; i < pWord.length; i++) {
                String strLwrWord = pWord[i].toLowerCase();
                int pos = (int) idxFile.findIndexForWord(strLwrWord);
                boolean bExist = false;
                if (pos < (int) idxFile.getLongWordCount()) {
                    if (strLwrWord.compareTo(((WordEntry) idxFile.getEntryList().get(pos)).getStrLwrWord()) == 0) {
                        bExist = true;
                    }
                }
                long nextOffset = dictFile.addData(pMean[i]);
                if (nextOffset >= 0) {
                    if (!bExist) {
                        idxFile.addEntry(pWord[i], nextOffset, pMean[i].length(), pos);
                    } else {
                        WordEntry tempEntry = idxFile.getEntryList().get(pos);
                        tempEntry.setLongOffset(nextOffset);
                        tempEntry.setLongSize(pMean[i].length());
                    }
                }
            }
            idxFile.write();
            ifoFile.setLongIdxFileSize(idxFile.getLongIdxFileSize());
            ifoFile.setLongWordCount(idxFile.getLongWordCount());
            ifoFile.write();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Add a word to .dict file and .idx file, modify the size of ifo file.
     * @param word word that is needed to add.
     * @param mean word meaning.
     * @return true if add complete.
     */
    public boolean addOneWord(String word, String mean) {
        String[] pWord = new String[1];
        String[] pMean = new String[1];
        pWord[0] = word;
        pMean[0] = mean;

        return addListOfWords(pWord, pMean);
    }

    /**
     * Get file name without extension. For example: input: a:\b.a - output: a:\b
     * @param url path of a file
     * @return original file name
     */
    public static String getFileNameWithoutExtension(String url) {
        int dot = url.lastIndexOf(".");

        return (dot > -1) ? url.substring(0, dot) : null;
    }

    /**
     * get extension of file.
     * @param url path to file
     * @return extension of file
     */
    public static String getExtension(String url) {
        int dot = url.lastIndexOf(".");
        return url.substring(dot + 1);
    }

    /**
     * Convert int into 32-bit integer.
     * @param val integer
     * @return 4 bytes of 32-bit integer
     */
    public static byte[] convertAnInt32(int val) {
        byte[] str = new byte[4];

        str[0] = (byte) ((val & 0xFF000000) >> 24);
        str[1] = (byte) ((val & 0x00FF0000) >> 16);
        str[2] = (byte) ((val & 0x0000FF00) >> 8);
        str[3] = (byte) ((val & 0x000000FF));
        return str;
    }

}
