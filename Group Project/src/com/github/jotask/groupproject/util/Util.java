package com.github.jotask.groupproject.util;

/**
 * This class is for hold utils methods that can be used in any part
 *
 * This class is final needs be static and have private constructor
 * for make sure that anyone can create or instantiate any instance
 * of this class
 *
 * @author Jose Vives.
 * @since 08/12/2015
 */
public final class Util {

    /** Private constructor for make suret that is impossible make an
     * instance of this class
     */
    private Util(){}

    /** Static method for convert between SQL date to java DATE
     *
     * @param javaDate
     *              the java Date
     * @return
     *              the java date converted to java SQL date
     */
    public static java.sql.Date toSQLDate(java.util.Date javaDate){
        return  new java.sql.Date(javaDate.getTime());
    }

    /**
     * Static method for convert between java DATE to SQL date
     *
     * @param sqlDate
     *              the SQL Date that we want convert
     * @return
     *              the SQL date converted to the java Date
     */
    public static java.util.Date toJavaDate(java.sql.Date sqlDate){
        return new java.util.Date(sqlDate.getTime());
    }

}
