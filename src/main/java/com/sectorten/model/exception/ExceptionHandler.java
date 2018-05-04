/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.exception;

/**
 *
 * @author nbb
 */
import com.sectorten.view.common.JsfUtil;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBTransactionRolledbackException;

/**
 *
 *
 */
public class ExceptionHandler {

    public static String exceptionHandler(Exception exception) {

        String retMessage = JsfUtil.getMessageFromBunndle("error_general");

        try {
            if (Objects.equals(EJBTransactionRolledbackException.class, exception.getClass())) {
                if (Objects.equals(BusinessLogicViolationException.class, exception.getCause().getCause().getClass())) {
                    retMessage = JsfUtil.getMessageFromBunndle(exception.getCause().getCause().getMessage());
                } else if (Objects.equals(BusinessLogicViolationException.class, exception.getCause().getCause().getCause().getClass())) {
                    retMessage = JsfUtil.getMessageFromBunndle(exception.getCause().getCause().getCause().getMessage());
                } else if (Objects.equals(BusinessLogicViolationException.class, exception.getCause().getCause().getCause().getCause().getClass())) {
                    retMessage = JsfUtil.getMessageFromBunndle(exception.getCause().getCause().getCause().getCause().getMessage());
                }//unique constrain in addedituser
                else if (Objects.equals(java.sql.SQLIntegrityConstraintViolationException.class, exception.getCause().getCause().getCause().getCause().getClass())) {
                    String exceptionMessage = exception.getCause().getCause().getCause().getCause().toString();
                    retMessage = SQLIntegrityConstraintViolation(exceptionMessage);
                }

            } else if (Objects.equals(BusinessLogicViolationException.class, exception.getClass())) {//bb
                retMessage = JsfUtil.getMessageFromBunndle(exception.getMessage());
            } else if (Objects.equals(BusinessLogicViolationException.class, exception.getCause().getClass())) {//ejb,entities
                retMessage = JsfUtil.getMessageFromBunndle(exception.getCause().getMessage());
            } else if (Objects.equals(BusinessLogicViolationException.class, exception.getCause().getCause().getCause().getClass())) {//rmi
                retMessage = JsfUtil.getMessageFromBunndle(exception.getCause().getCause().getCause().getMessage());
            } else if (Objects.equals(java.sql.SQLIntegrityConstraintViolationException.class, exception.getCause().getCause().getCause().getCause().getClass())
                    || (exception.getCause().getCause().getCause().getCause().getClass().getName().contains("MySQLIntegrityConstraintViolationException") //
                    )) {
                // SQLIntegrityConstraintViolationException Handler
                // Exception Message String That Contains Oracle Error Code.
                String exceptionMessage = exception.getCause().getCause().getCause().getCause().toString();
                retMessage = SQLIntegrityConstraintViolation(exceptionMessage);

            } else if (Objects.equals(java.sql.SQLDataException.class, exception.getCause().getCause().getCause().getCause().getClass())) {

                // Exception Message String That Contains Oracle Error Code.
                String exceptionMessage = exception.getCause().getCause().getCause().getCause().toString();

                if (exceptionMessage.contains("1406")) {
                    //Cause: value larger than specified precision allowed for this column.
                    // When inserting or updating records, a numeric value was entered that exceeded the precision defined for the column.
                    retMessage = JsfUtil.getMessageFromBunndle("value_larger_than_speified_precision");
                } else {
                    retMessage = JsfUtil.getMessageFromBunndle("unhandled_exception");
                }
            }

        } catch (Exception generalException) {
            Logger.getLogger(ExceptionHandler.class.getName()).log(Level.SEVERE, null, generalException);
            retMessage = JsfUtil.getMessageFromBunndle("general_exception");
        }

//for debuginig in development in save (add,edit) show which field has exception
//        @SuppressWarnings("ThrowableResultIgnored")
//        Exception cause = (Exception) (((EJBException) exception).getCausedByException()).getCause();
//        if (cause instanceof ConstraintViolationException) {
//            @SuppressWarnings("ThrowableResultIgnored")
//            ConstraintViolationException cve = (ConstraintViolationException) cause;
//            for (ConstraintViolation<? extends Object> v : cve.getConstraintViolations()) {
//                System.err.println(v);
//                System.err.println("==>>" + v.getMessage());
//            }
//        }
//        Assert.fail("ejb exception");
        return retMessage;

    }

    private static String SQLIntegrityConstraintViolation(String exceptionMessage) {
        String retMessage = "";
        if (exceptionMessage.contains("1062") || exceptionMessage.contains("Duplicate")) {
            //Cause: An UPDATE or INSERT statement attempted to insert a duplicate key.
            //System.out.println("unique constraint violated");

            retMessage = uniqueKeyViolation(exceptionMessage);

        } else if (exceptionMessage.contains("1048")) {

            retMessage = JsfUtil.getMessageFromBunndle("not_null_field_violation");

        } else if (exceptionMessage.contains("1364")) {
            //Cause: The values being inserted do not satisfy the named check constraint.
            //System.out.println("check constraint violated");
            retMessage = JsfUtil.getMessageFromBunndle("check_constraint_violation");

        } else if (exceptionMessage.contains("1452")) {
            //Cause: A foreign key value has no matching primary key value.
            //System.out.println("integrity constraint violated - parent key not found");
            retMessage = "foreign_key_violation";
        } else if (exceptionMessage.contains("1451") || exceptionMessage.contains("Cannot delete or update")) {
            //Cause: attempted to delete a parent key value that had a foreign key dependency.
            //System.out.println("integrity constraint (string.string) violated - child record found");
            retMessage = relatedRecordViolation(exceptionMessage);
        } else {
            retMessage = JsfUtil.getMessageFromBunndle("unhandled_exception");
        }

        return retMessage;
    }

    private static String uniqueKeyViolation(String exceptionMessage) {
        String retMessage;
        if (exceptionMessage.contains("PK")) {

            retMessage = JsfUtil.getMessageFromBunndle("pk_constraint_violation");
        } else if (exceptionMessage.contains("UK") || exceptionMessage.contains("UNIQUE")) {

            try {
                retMessage = JsfUtil.getMessageFromBunndle("unique_key_violation");
            } catch (Exception exception) {
                retMessage = JsfUtil.getMessageFromBunndle("unique_key_violation");
                Logger.getLogger(ExceptionHandler.class.getName()).log(Level.SEVERE, null, exception);
            }
        } else {
            retMessage = JsfUtil.getMessageFromBunndle("unique_key_violation");
        }

        return retMessage;
    }

    private static String relatedRecordViolation(String exceptionMessage) {
        String retMessage;

        try {
            // String firstTable = JsfUtil.getMessageFromBunndle(splittedDbFkMessage[1]);
            //String secondTable = JsfUtil.getMessageFromBunndle(splittedDbFkMessage[2]);

            retMessage = JsfUtil.getMessageFromBunndle("db_exception_delete_fk_violation");
        } catch (Exception e) {
            retMessage = JsfUtil.getMessageFromBunndle("child_record_found_on_delete");
        }

        return retMessage;
    }
}
