package com.LeeGlen;

/**
 * Stores an address.
 */
public class Address {

   /**
    * ID of the address.
    */
   private int id;
   /**
    * The first line of the address.
    */
   private String first_line;
   /**
    * The second line of the address.
    */
   private String second_line;
   /**
    * The postcode of the address.
    */
   private String postcode;
   /**
    * The county of the address.
    */
   private String county;
   /**
    * The employee who possibly lives there.
    */
   private Employee employee;
   /**
    * The customer who possibly lives there.
    */
   private Customer customer;
   /**
    * The supplier who is possibly based in.
    */
   private Supplier supplier;

   /**
    * Gets the ID of the address.
    * @return The address ID.
    */
   public int getId() {
      return id;
   } //END METHOD getId

   /**
    * Sets the ID of the address.
    * @param id The address ID.
    */
   public void setId(int id) {
      this.id = id;
   } //END METHOD setId

   /**
    * Gets the first line of the address.
    * @return The addresses first line.
    */
   public String getFirst_line() {
      return first_line;
   } //END METHOD getFirst_line

   /**
    * Sets the first line of the address.
    * @param first_name The addresses first line.
    */
   public void setFirst_line(String first_name) {
      this.first_line = first_name;
   } //END METHOD setFirst_line

   /**
    * Gets the second line of the address.
    * @return The address second line.
    */
   public String getSecond_line() {
      return second_line;
   } //END METHOD getSecond_line

   /**
    * Sets the second line of the address.
    * @param second_line The address second line.
    */
   public void setSecond_line(String second_line) {
      this.second_line = second_line;
   } //END METHOD setSecond_line

   /**
    * Gets the postcode of the address.
    * @return The address postcode.
    */
   public String getPostcode() {
      return postcode;
   } //END METHOD getPostcode

   /**
    * Sets the postcode of the address.
    * @param postcode The address postcode.
    */
   public void setPostcode(String postcode) {
      this.postcode = postcode;
   } //END METHOD setPostcode

   /**
    * Gets the county of the address.
    * @return The address county.
    */
   public String getCounty() {
      return county;
   } //END METHOD getCounty

   /**
    * Set the county of the address.
    * @param county The address county.
    */
   public void setCounty(String county) {
      this.county = county;
   } //END METHOD setCounty

   /**
    * Gets the employee associated with the address.
    * @return The employee associated with the address.
    */
   public Employee getEmployee() {
      return employee;
   } //END METHOD getEmployee

   /**
    * Sets the employee associated with the address.
    * @param employee The employee associated with the address.
    */
   public void setEmployee(Employee employee) {
      this.employee = employee;
   } //END METHOD setEmployee

   /**
    * Gets the customer associated with the address.
    * @return The customer associated with the address.
    */
   public Customer getCustomer() {
      return customer;
   } //END METHOD getCustomer

   /**
    * Sets the customer associated with the address.
    * @param customer The customer associated with the address.
    */
   public void setCustomer(Customer customer) {
      this.customer = customer;
   } //END METHOD setCustomer

   /**
    * Gets the supplier associated with the address.
    * @return The supplier associated with the address.
    */
   public Supplier getSupplier() {
      return supplier;
   } //END METHOD getSupplier

   /**
    * Sets the supplier associated with the address.
    * @param supplier The supplier associated with the address.
    */
   public void setSupplier(Supplier supplier) {
      this.supplier = supplier;
   } //END METHOD setSupplier

   /**
    * Returns information from the class that's needed to see.
    * @return The supplier associated with the address.
    */
   @Override
   public String toString() {
      //If there is a second line return with a second line otherwise don't include second line
      if (second_line != null) {
         return first_line + ", " + second_line + ", " + postcode;
      } else {
         return first_line + ", " + postcode;
      }//END IF/ELSE
   }//END METHOD toString
} //END CLASS Address
