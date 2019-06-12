package com.LeeGlen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores the employee's data.
 */
public class Employee {

    /**
     * ID of the employee.
     */
    private int id;
    /**
     * First Name of the employee.
     */
    private String first_name;
    /**
     * Last Name of the employee.
     */
    private String last_name;
    /**
     * Salary of the employee.
     */
    private double salary;
    /**
     * Manager of the employee.
     */
    private Employee manager;
    /**
     * Address of the employee.
     */
    private Address address;
    /**
     * Password of the employee.
     */
    private String password;
    /**
     * Job Histories assigned to employee.
     */
    private Set<JobHistory> jobHistories = new HashSet<>();
    /**
     * Stock Logs assigned to employee.
     */
    private Set<StockLog> stockLogs = new HashSet<>();
    /**
     * Transaction Logs assigned to employee.
     */
    private Set<TransactionLog> transactionLogs = new HashSet<>();

    /**
     * Gets the ID of the employee.
     * @return the employees ID.
     */
    public int getId() {
        return id;
    } //END METHOD getId

    /**
     * Sets the ID of the employee.
     * @param id The employees ID.
     */
    public void setId(int id) {
        this.id = id;
    } //END METHOD setId

    /**
     * Gets the first name of the employee.
     * @return The employees first name.
     */
    public String getFirst_name() {
        return first_name;
    } //END METHOD getFirst_name

    /**
     * Sets the first name of the employee.
     * @param first_name The employees first name.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    } //END METHOD setFirst_nae

    /**
     * Gets the last name of the employee.
     * @return Last name of the employee.
     */
    public String getLast_name() {
        return last_name;
    } //END METHOD getLast_name

    /**
     * Sets the last name of the employee.
     * @param last_name Last name of the employee.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    } //END METHOD setLast_name

    /**
     * Gets the salary of the employee.
     * @return Salary of the employee
     */
    public double getSalary() {
        return salary;
    } //END METHOD getSalary

    /**
     * Sets the salary of the employee.
     * @param salary Salary of the employee.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    } //END METHOD setSalary

    /**
     * Gets the manager of the employee.
     * @return Manager of the employee.
     */
    public Employee getManager() {
        return manager;
    } //END METHOD getManager

    /**
     * Gets the manager of the employee.
     * @param manager Manager of the employee.
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    } //END METHOD setManager

    /**
     * Gets the address of the employee.
     * @return The Address of the employee.
     */
    public Address getAddress() {
        return address;
    } //END METHOD getAddress

    /**
     * Sets the address of the employee.
     * @param address The address of the employee.
     */
    public void setAddress(Address address) {
        this.address = address;
    } //END METHOD setAddress

    /**
     * Gets the password of the employee.
     * @return Password of the employee.
     */
    public String getPassword() {
        return password;
    } //END METHOD getPassword

    /**
     * Sets the password of the employee.
     * @param password Password of the employee.
     */
    public void setPassword(String password) {
        this.password = password;
    } //END METHOD setPassword

    /**
     * Gets the job histories associated with the employee.
     * @return Job histories associated with the employee.
     */
    public Set<JobHistory> getJobHistories() {
        return jobHistories;
    } //END METHOD getJobHistories

    /**
     * Sets the job histories associated with the employee.
     * @param jobHistories Job histories associated with the employee.
     */
    public void setJobHistories(Set<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    } //END METHOD setJobHistories

    /**
     * Gets the stock logs associated with the employee.
     * @return Stock logs associated with the employee.
     */
    public Set<StockLog> getStockLogs() {
        return stockLogs;
    } //END METHOD getStockLogs

    /**
     * Sets the stock logs associated with the employee.
     * @param stockLogs Stock logs associated with the employee.
     */
    public void setStockLogs(Set<StockLog> stockLogs) {
        this.stockLogs = stockLogs;
    } //END METHOD setStockLogs

    /**
     * Gets transaction logs associated with the employee.
     * @return Transaction logs associated with the employee.
     */
    public Set<TransactionLog> getTransactionLogs() {
        return transactionLogs;
    } //END METHOD getTransactionLogs

    /**
     * Sets the transaction logs associated with the employee.
     * @param transactionLogs Transaction logs associted with the employee.
     */
    public void setTransactionLogs(Set<TransactionLog> transactionLogs) {
        this.transactionLogs = transactionLogs;
    } //END METHOD setTransactionLogs

    /**
     * Prints out the needed information of the employee.
     * @return String of employee data.
     */
    @Override
    public String toString() {
        return first_name + " " + last_name;
    } //END METHOD toString
} //END CLASS Employee
