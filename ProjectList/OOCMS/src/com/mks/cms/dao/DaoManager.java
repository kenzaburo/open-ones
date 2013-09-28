/**
 * MKS Copyright 2012.
 */
package com.mks.cms.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import rocky.common.PropertiesManager;


/**
 * @author thachln
 */
public class DaoManager {
    /** Logger. */
    private static Logger log = Logger.getLogger("DaoManager");

    /** Singleton Dao Manager. */
    private static IDao daoInstance = null;

    /**
     * Get the instance of DaoManager from the configuration.
     * @return instance of DaoManager
     */
    public static IDao getDaoInstance() {
        if (daoInstance == null) {
            try {
                Properties props = PropertiesManager.newInstanceFromProps("/app.properties");
                // get class of DaoManager implementation.
                String daoManagerImplClassName = props.getProperty("DaoManagerImpl",
                        "ebiz.dao.gae.GAEDAOManager");
                Class daoManagerClass = Class.forName(daoManagerImplClassName);
                daoInstance = (IDao) daoManagerClass.newInstance();
            } catch (Exception ex) {
                log.log(Level.CONFIG, "Load configuration resource file /app.properties", ex);
            }
        }

        return daoInstance;
    }

}
