package com.LeeGlen;

import java.util.Date;

/**
 * Stores the job history.
 */
public class JobHistory {
    /**
     * ID of the job history.
     */
    private int id;
    /**
     * Type of the job history.
     */
    private String type;
    /**
     * Start date of the job history.
     */
    private Date start_date;
    /**
     * End date of the job history.
     */
    private Date end_date;
    /**
     * Employee associated with the job history.
     */
    private Employee employee;

    /**
     * Gets The ID of the job history.
     * @return ID of the job history.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets The ID of the job history.
     * @param id ID of the job history.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets The type of the job history.
     * @return Type of the job history.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets The type of the job history.
     * @param type Type of the job history.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets The start date of the job history.
     * @return Start date of the job history.
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * Sets the start date of the job history.
     * @param start_date Start date of the job history.
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * Get the end date of the job history.
     * @return End date of the job history.
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * Set the end date of the job history.
     * @param end_date End date of the job history.
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * Get the employee associated with the job history.
     * @return Employee associated with the job history.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the employee associated with the job history.
     * @param employee Employee associated with the job history.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

} //END CLASS JobHistory
