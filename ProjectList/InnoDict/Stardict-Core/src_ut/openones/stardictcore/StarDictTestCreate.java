package openones.stardictcore;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
/**
 * Test manipulation of StarDict.
 * @author Thach Le
 */
public class StarDictTestCreate {

    String dictRepo = "/media/Data1/Projects/Open-Ones/GoogleCode/trunk/ProjectList/InnoDict/Stardict-Core/src_ut/testdata";

    @Test
    public void testCreateNewDict() {
        String repoPath = dictRepo;
        String dictName = "ecom_en_en";
        DictInfo dictInfo = new DictInfo();
        dictInfo.setIntro("Dictionary of E-Commerce: English-English");
        dictInfo.setVersion("0.0.1");
        dictInfo.setAuthor("Thach.Le");
        dictInfo.setBookname("ECommerce-En-En");
        dictInfo.setIdxfilesize("1");
        dictInfo.setWordcount("1");
        dictInfo.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        dictInfo.setDescription("Open-Ones' Ecommerce dictionary");

        boolean ok = StarDict.createDict(repoPath, dictName, dictInfo);
        Assert.assertEquals(true, ok);

        StarDict ecomDict = StarDict.loadDict(repoPath + File.separator + "ecom_en_en");
        ecomDict.addOneWord("e-marketplaces", "An online market");

        Assert.assertEquals("0.0.1", ecomDict.getDictVersion());
        Assert.assertEquals("ECommerce-En-En", ecomDict.getDictName());
        Assert.assertEquals(1, ecomDict.getTotalWords());
        Assert.assertEquals(true, ecomDict.existWord("e-marketplaces"));
    }

    @Test
    public void testLookupWordString() {
        String repoPath = dictRepo;
        StarDict ecomDict = StarDict.loadDict(repoPath + File.separator + "ecom_en_en");

        Assert.assertEquals("0.0.1", ecomDict.getDictVersion());
        Assert.assertEquals("ECommerce-En-En", ecomDict.getDictName());
        Assert.assertEquals(1, ecomDict.getTotalWords());

        String actual = ecomDict.lookupWord("e-marketplaces");
        String expected = "An online market";
        assertEquals(expected, actual);
    }
}
