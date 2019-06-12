package com.LeeGlen;

import org.hibernate.HibernateException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controls where the data goes.
 */
public class Controller {

    /**
     * Used to get data from database.
     */
    private Model model = new Model();
    /**
     * Holds username for further use.
     */
    private String username;
    /**
     * Holds employee's job for further use.
     */
    private String jobType;

    /**
     * Will pass the username and password and will check to see if it is valid.
     * @param employeeUsername The Employee's Username.
     * @param employeePassword The Employee's Password.
     * @return The Employee's Job.
     * @throws HibernateException Throws Exception When Database Crashes or Isn't Available.
     */
    public String logIn(String employeeUsername, String employeePassword) throws HibernateException {
        //Initialise Variables
        username = employeeUsername;
        String password = employeePassword;
        //Checks to see if the username ad password is valid.
            jobType = model.checkDetails(username, password);
            //If the job type was given a null value then it sets the string to hold error.
            if (jobType == null)
            {
                jobType = "ERROR";
            }//END IF
            //Returns value back to original call
        return jobType;
    }//END METHOD logIn

    /**
     * Will pass in the product name and the amount of stocked added.
     * to add the stock to the product specified.
     * @param prodcut Product that will have stock added to.
     * @param addedStock The amount of stock going to be added.
     * @return returns a string to say if the product has been found or not.
     */
    public String addStock(String prodcut, int addedStock){
        //Initialise Variables
        String found;
        found = model.addStock(prodcut, addedStock, username);
        //Returns value back to original call
        return found;
    }//END METHOD addStock

    /**
     * Searches the product specified by the user.
     * @param productName The product name the user specified.
     * @return The products from the table.
     */
    public ArrayList<Product> searchProduct(String productName){
        //Gets the products from the table
        ArrayList<Product> products = model.searchProduct(productName);
        //Returns value back to original call
        return products;
    }//END METHOD searchProduct

    /**
     * Gets the transaction logs on a specified date.
     * @param date The date the user chose.
     * @return The transaction log data from the database.
     */
    public ArrayList<TransactionLog> displayTransactionLog(LocalDate date){
        //Initialise variables.
        ArrayList<TransactionLog> transactionLogs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Checks to see if date from date picker matches the regular expression
        while (true) {
            String dateOfTransaction = dateFormat.format(java.sql.Date.valueOf(date));
           if (dateOfTransaction.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")){ //https://www.regextester.com/96683 - This is where I found the regular expression
               //Gets the transactions from the database that happened on specified date
               transactionLogs = model.displayTransactions(date);
               return transactionLogs;
            } else {
               //Will return null
                return transactionLogs;
            }//END IF/ELSE
        }//END WHILE
    }//END METHOD displayTransactionLog

    /**
     * Gets the data based of the choice.
     * @param choice The choice the user picked.
     * @return The data from the database.
     */
    public ArrayList displayEmployeeCustomerSupplierInfo(String choice){
        //Returns data from the database based on the choice back to original call
       return model.displayEmployeeCustomerSupplierInfo(choice);
    }//END METHOD displayEmployeeCustomerSupplierInfo

    /**
     * Gets the report based on the choice..
     * @param choice The choice the user picked.
     * @return The report.
     */
    public StringBuilder generateReports(String choice){
        //Returns data from the database based on the choice back to original call
        return model.generateReport(choice);
    }//END METHOD generateReports

    /**
     * Gets the user's job type.
     * @return The current user's job type.
     */
    public String getJobType(){
        //Returns the job type back to original call
        return jobType;
    }//END METHOD getJobType

    /**
     * Creates a new transaction log.
     * @param productPurchased The product that has been purchased.
     * @return If the product has been purchased or not.
     */
    public String purchaseProduct(String productPurchased){
        //Returns product found or not found to original call
        return model.purchaseProduct(username, productPurchased);
    }//END METHOD purchaseProduct

    /**
     * Closes the Hibernate session.
     */
    public void exit(){
        //Closes The Hibernate Session
        model.closeSession();
    }//END METHOD exit

}//END CLASS Controller
