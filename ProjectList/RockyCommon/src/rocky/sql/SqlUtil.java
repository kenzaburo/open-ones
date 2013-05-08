package rocky.sql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * This class provides utilities for the query processing.
 * @author Le Ngoc Thach
 */
public class SqlUtil {
    /**
     * [Give the description for method].
     * @param pstmt
     * @param index
     * @param value
     * @return 0: Success; -1: Unsupported data type.
     * @throws SQLException
     */
    public static int setValue(PreparedStatement pstmt, int index, Object value) throws SQLException {
        if (value == null) {
            pstmt.setString(index, null);
        } else if (value instanceof BigInteger) {

            pstmt.setInt(index, (Integer) value);
        } else if (value instanceof BigDecimal) {

            pstmt.setBigDecimal(index, (BigDecimal) value);
        } else if (value instanceof java.util.Date) {

            pstmt.setDate(index, new java.sql.Date(((java.util.Date) value).getTime()));
        } else if (value instanceof Integer) {

            pstmt.setInt(index, ((Integer) value).intValue());
        } else if (value instanceof String) {

            pstmt.setString(index, ((String) value));
        } else if (value instanceof Timestamp) {

            pstmt.setTimestamp(index, (Timestamp) value);
        } else {
            return -1;
        }

        return 0;
    }
}
