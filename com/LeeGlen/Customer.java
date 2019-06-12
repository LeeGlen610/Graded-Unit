package com.LeeGlen;

import java.util.HashSet;
import java.util.Set;

/**
 * Stores customer data.
 */
public class Customer {

    /**
     * ID of the customer.
     */
    private int id;
    /**
     * First name of the customer.
     */
    private String first_name;
    /**
     * Last name of the customer.
     */
    private String last_name;
    /**
     * Address of the customer.
     */
    private Address address;
    /**
     * How many transactions they've been involved in.
     */
    private Set<TransactionLog> transactionLogs = new HashSet<>();

    /**
     * Gets the id of the customer.
     * @return ID of the customer.
     */
    public int getId() {
        return id;
    } //END METHOD getId

    /**
     * Sets the id of the customer.
     * @param id The ID of the customer.
     */
    public void setId(int id) {
        this.id = id;
    } //END METHOD setId

    /**
     * Gets the first name of the customer.
     * @return First name of the customer.
     */
    public String getFirst_name() {
        return first_name;
    } //END METHOD getFirst_name

    /**
     * Sets the first name of the customer.
     * @param first_name The first name of the customer.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    } //END METHOD setFirst_name

    /**
     * Gets the Last Name of the customer.
     * @return Last Name of the customer.
     */
    public String getLast_name() {
        return last_name;
    } //END METHOD getLast_name

    /**
     * Sets the Last Name of the customer.
     * @param last_name The last Name of the customer.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    } //END METHOD setLast_name

    /**
     * Gets the address of the customer.
     * @return The Address of the customer.
     */
    public Address getAddress() {
        return address;
    } //END METHOD getAddress

    /**
     * Sets the address of the customer.
     * @param address The customers address.
     */
    public void setAddress(Address address) {
        this.address = address;
    } //END METHOD setAddress

    /**
     * Gets the transactions.
     * @return The customers transactions.
     */
    public Set<TransactionLog> getTransactionLogs() {
        return transactionLogs;
    } //END METHOD getTransactionLogs

    /**
     * Sets the transactions.
     * @param transactionLogs The customers transactions.
     */
    public void setTransactionLogs(Set<TransactionLog> transactionLogs) {
        this.transactionLogs = transactionLogs;
    } //END METHOD  setTransactionLogs

    /**
     * Prints the needed information.
     * @return Customer Information.
     */
    @Override
    public String toString() {
        return first_name + " " + last_name + " - " + address.toString();
    } //END METHOD toString
}//END CLASS Customer
