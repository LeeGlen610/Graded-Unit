package com.LeeGlen;

import java.util.Date;

/**
 * Stores the transactions.
 */
public class TransactionLog {

    /**
     * ID of the transaction log.
     */
    private int id;
    /**
     * Total price of the transaction.
     */
    private double total_price;
    /**
     * The date that the transaction took place.
     */
    private Date year;
    /**
     * Employee associated with the transaction.
     */
    private Employee employee;
    /**
     * Customer associated with the transaction.
     */
    private Customer customer;
    /**
     * Product associated with the transaction.
     */
    private Product product;

    /**
     * Gets the ID of the transaction.
     * @return ID of the transaction.
     */
    public int getId() {
        return id;
    } //END METHOD getId

    /**
     * Sets the ID of the transaction.
     * @param id ID of the transaction.
     */
    public void setId(int id) {
        this.id = id;
    } //END METHOD setId

    /**
     * Gets the total price of the transaction.
     * @return Total price of the transaction.
     */
    public double getTotal_price() {
        return total_price;
    } //END METHOD getTotal_price

    /**
     * Sets the total price of the transaction.
     * @param total_price Total price of the transaction.
     */
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }//END METHOD setTotal_price

    /**
     * Gets the date that the transaction took place.
     * @return The date that the transaction took place.
     */
    public Date getYear() {
        return year;
    } //END METHOD setYear

    /**
     * Sets the date that the transaction took place.
     * @param year The date that the transaction took place.
     */
    public void setYear(Date year) {
        this.year = year;
    }//END METHOD getYear
    /**
     * Gets the employee associated with the transaction.
     * @return The employee associated with the transaction.
     */
    public Employee getEmployee() {
        return employee;
    } //END METHOD getEmployee

    /**
     * Sets the employee associated with the transaction.
     * @param employee The employee associated with the transaction.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    } //END METHOD setEmployee

    /**
     * Gets the customer associated with the transaction.
     * @return The customer associated with the transaction.
     */
    public Customer getCustomer() {
        return customer;
    } //END METHOD getCustomer

    /**
     * Sets the customer associated with the transaction.
     * @param customer The customer associated with the transaction.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    } //END METHOD setCustomer

    /**
     * Gets the product associated with the transaction.
     * @return Product associated with the transaction.
     */
    public Product getProduct() {
        return product;
    } //END METHOD getProduct

    /**
     * Sets the product associated with the transaction.
     * @param product Product associated with the transaction.
     */
    public void setProduct(Product product) {
        this.product = product;
    } //END METHOD setProduct
}//END CLASS TransactionLog
