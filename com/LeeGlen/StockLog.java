package com.LeeGlen;

import java.util.Date;

/**
 * Stores the stock logs.
 */
public class StockLog {

    /**
     * ID of the stock log.
     */
    private int id;
    /**
     * Date when stock was added.
     */
    private Date dateOfStockAdded;
    /**
     * Employee associated with stock log.
     */
    private Employee employee;
    /**
     * Product associated with stock log.
     */
    private Product product;

    /**
     * Gets the ID of the stock log.
     * @return ID of the stock log.
     */
    public int getId() {
        return id;
    } //END METHOD getId

    /**
     * Sets the ID of the stock log.
     * @param id ID of the stock log.
     */
    public void setId(int id) {
        this.id = id;
    }//END METHOD setId

    /**
     * Gets the date of stock added.
     * @return Date of stock added.
     */
    public Date getDateOfStockAdded() {
        return dateOfStockAdded;
    } //END METHOD getDateOfStockAdded

    /**
     * Sets the date of stock added.
     * @param dateOfStockAdded Date of stock added.
     */
    public void setDateOfStockAdded(Date dateOfStockAdded) {
        this.dateOfStockAdded = dateOfStockAdded;
    } //END METHOD setDateOfStockAdded

    /**
     * Gets the employee associated with the stock log.
     * @return Employee associated with the stock log.
     */
    public Employee getEmployee() {
        return employee;
    } //END METHOD getEmployee

    /**
     * Sets the employee associated with the stock log.
     * @param employee Employee associated with the stock log.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    } //END METHOD setEmployee

    /**
     * Gets the product associated with the stock log.
     * @return Product associated with the stock log.
     */
    public Product getProduct() {
        return product;
    } //END METHOD getProduct

    /**
     * Sets the product associated with the stock log.
     * @param product Product associated with the stock log.
     */
    public void setProduct(Product product) {
        this.product = product;
    } //END METHOD setProduct
}//END CLASS StockLog
