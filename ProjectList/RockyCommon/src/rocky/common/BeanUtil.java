package rocky.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BeanUtil {
    final static Logger LOG = Logger.getLogger(BeanUtil.class);

    /**
     * @param entity
     * @return Map<property name, set method instance>
     * @throws IntrospectionException
     */
    public static Map<String, Method> getWriteMethodMap(Object entity) throws IntrospectionException {
        Map<String, Method> writeMethodMap = null;

        if (writeMethodMap != null) {
            return writeMethodMap;
        }
        // <property, WriteMethod object>
        writeMethodMap = new HashMap<String, Method>();

        // Analyze the bean to build the methodMap
        BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
        for (PropertyDescriptor propDes : beanInfo.getPropertyDescriptors()) {
            writeMethodMap.put(propDes.getName(), propDes.getWriteMethod());
        }

        return writeMethodMap;
    }

    /**
     * @param entity
     * @return Map<property name, set method instance>
     * @throws IntrospectionException
     */
    public static Map<String, Method> getReadMethodMap(Object entity) throws IntrospectionException {
        Map<String, Method> readMethodMap = null;
        if (readMethodMap != null) {
            return readMethodMap;
        }
        // <property, ReadMethod object>
        readMethodMap = new HashMap<String, Method>();

        // Analyze the bean to build the methodMap
        BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
        for (PropertyDescriptor propDes : beanInfo.getPropertyDescriptors()) {
            readMethodMap.put(propDes.getName(), propDes.getReadMethod());
        }
        return readMethodMap;
    }

    public static boolean updateProperty(Object obj, String property, String value) {
        try {
            String setMethodName = "set" + (property.substring(0, 1).toUpperCase()) + property.substring(1);
            Method setMethod = obj.getClass().getMethod(setMethodName, String.class);
            setMethod.invoke(obj, value);

        } catch (Exception ex) {
            LOG.warn("Dynamic invoke setter for '" + property, ex);
            return false;
        }

        return true;
    }

    public static boolean updateProperty(Object obj, String property, Object objValue) {
        return updateProperty(obj, property, objValue, String.class.getName());
    }

    /**
     * Update the value of Object's properties dynamically.
     * @param instanceObj
     * @param property
     * @param objValue
     * @param dataType
     * @return
     */
    public static boolean updateProperty(Object instanceObj, String property, Object objValue, String dataType) {
        Object actObj = null;
        Method setMethod;
        try {
            String setMethodName = "set" + (property.substring(0, 1).toUpperCase()) + property.substring(1);

            if (!CommonUtil.isNNandNB(dataType)) {
                dataType = String.class.getName();
            }

            if (String.class.getName().equals(dataType)) {
                actObj = (objValue != null ? objValue.toString() : null);
                setMethod = instanceObj.getClass().getMethod(setMethodName, String.class);
                setMethod.invoke(instanceObj, actObj);
            } else if (Date.class.getName().equals(dataType)) {
                if (objValue instanceof Double) {
                    actObj = new Date(new Double(objValue.toString()).longValue());
                } else {
                    actObj = (Date) objValue;
                }
                setMethod = instanceObj.getClass().getMethod(setMethodName, Date.class);
                setMethod.invoke(instanceObj, actObj);
            } else if (Integer.class.getName().equals(dataType)) {
                actObj = (Integer) objValue;
                setMethod = instanceObj.getClass().getMethod(setMethodName, Integer.class);
                setMethod.invoke(instanceObj, actObj);
            } else if (Double.class.getName().equals(dataType)) {
                if (CommonUtil.isNNandNB(objValue)) {
                    actObj = new Double(objValue.toString());
                }
                setMethod = instanceObj.getClass().getMethod(setMethodName, Double.class);
                setMethod.invoke(instanceObj, actObj);
            } else {
                throw new RuntimeException("Unsupport data type of value: " + objValue.getClass());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    
    /**
     * Transfer data from properties into entity.
     * @param props
     * @return
     */
    public static Object propsToEntity(Properties props, Class entityClass) {
        Method setMethod;
        Object resultObj = null;

        try {
            resultObj = entityClass.newInstance();

            // Scan properties
            String setMethodName;
            String strKey;
            for (Object key : props.keySet()) {
                strKey = (String) key;
                setMethodName = buildSetMethod(strKey);

                try {
                    setMethod = entityClass.getDeclaredMethod(setMethodName, String.class);
                    setMethod.invoke(resultObj, props.getProperty(strKey));
                } catch (SecurityException ex) {
                    LOG.warn("Have no permission for method.", ex);
                } catch (NoSuchMethodException ex) {
                    LOG.warn("Method not found.", ex);
                } catch (IllegalArgumentException ex) {
                    LOG.warn("Could not invoke method.", ex);
                } catch (IllegalAccessException ex) {
                    LOG.warn("Could not invoke method.", ex);
                } catch (InvocationTargetException ex) {
                    LOG.warn("Could not invoke method.", ex);
                }
            }
        } catch (InstantiationException ex) {
            LOG.error("Could not create instance", ex);
        } catch (IllegalAccessException ex) {
            LOG.error("Could not create instance", ex);
        }

        return resultObj;
    }
    
    /**
     * Build set method from property name.
     * <br/>
     * Rule: "set" + property name with upper first character.
     * @param property
     * @return set + property with first character is upper.
     */
    private static String buildSetMethod(String property) {
        return "set" + String.valueOf(property.charAt(0)).toUpperCase() + property.substring(1);
    }

}
