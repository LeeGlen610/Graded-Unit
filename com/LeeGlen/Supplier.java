package com.LeeGlen;

import java.util.HashSet;
import java.util.Set;

/**
 * Stores the supplier data.
 */
public class Supplier {

    /**
     * ID of the supplier.
     */
    private int id;
    /**
     * Name of the supplier.
     */
    private String name;
    /**
     * Type of the supplier.
     */
    private String type;
    /**
     * Address of the supplier.
     */
    private Address address;
    /**
     * Products given by the supplier.
     */
    private Set<Product> products = new HashSet<>();

    /**
     * Gets the ID of the supplier.
     * @return ID of the supplier.
     */
    public int getId() {
        return id;
    }//END METHOD getId

    /**
     * Sets the ID of the supplier.
     * @param id ID of the supplier.
     */
    public void setId(int id) {
        this.id = id;
    }//END METHOD setId

    /**
     * Gets the name of the supplier.
     * @return Name of the supplier.
     */
    public String getName() {
        return name;
    }//END METHOD getName

    /**
     * Sets the name of the supplier.
     * @param name Name of the supplier.
     */
    public void setName(String name) {
        this.name = name;
    }//END METHOD setName

    /**
     * Gets the type of the supplier.
     * @return Type of the supplier.
     */
    public String getType() {
        return type;
    }//END METHOD getType

    /**
     * Sets the type of the supplier.
     * @param type Type of the supplier
     */
    public void setType(String type) {
        this.type = type;
    } //END METHOD setType

    /**
     * Gets the address of the supplier.
     * @return Address of the supplier.
     */
    public Address getAddress() {
        return address;
    } //END METHOD getAddress

    /**
     * Sets the address of the supplier.
     * @param address Address of the supplier.
     */
    public void setAddress(Address address) {
        this.address = address;
    } //END METHOD setAddress

    /**
     * Gets the products provided by the supplier.
     * @return Products provided by the supplier.
     */
    public Set<Product> getProducts() {
        return products;
    } //END METHOD getProduct

    /**
     * Sets the products provided by the supplier.
     * @param products Products provided by the supplier.
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }//END METHOD setProducts

    /**
     * Prints out the needed information.
     * @return String of the needed data of the supplier.
     */
    @Override
    public String toString() {
        return id + " " + name + " " + type;
    }//END METHOD toString
}//END CLASS Supplier
