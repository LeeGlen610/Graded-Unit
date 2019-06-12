package com.LeeGlen;

import java.util.HashSet;
import java.util.Set;

/**
 * Stores the product data.
 */
public class Product {
    /**
     * ID of the product.
     */
    private int id;
    /**
     * Name of the product.
     */
    private String name;
    /**
     * Type of the product.
     */
    private String type;
    /**
     * Price of the product.
     */
    private double price;
    /**
     * Stock of the product.
     */
    private int stock;
    /**
     * Supplier associated with the product.
     */
    private Supplier supplier;
    /**
     * Stock logs associated with the product.
     */
    private Set<StockLog> stockLogs = new HashSet<>();
    /**
     * Transaction logs associated with the product.
     */
    private Set<TransactionLog> transactionLogs = new HashSet<>();

    /**
     * Gets the ID of the product.
     * @return ID of the product
     */
    public int getId() {
        return id;
    } //END METHOD getId

    /**
     * Sets the ID of the product.
     * @param id ID of the product.
     */
    public void setId(int id) {
        this.id = id;
    } //END METHOD setId

    /**
     * Gets the name of the product.
     * @return Name of the product.
     */
    public String getName() {
        return name;
    } //END METHOD getName

    /**
     * Sets the name of the product.
     * @param name Name of the product.
     */
    public void setName(String name) {
        this.name = name;
    } //END METHOD setName

    /**
     * Gets the type of the product.
     * @return Type of the product.
     */
    public String getType() {
        return type;
    } //END METHOD getType

    /**
     * Sets the type of the product.
     * @param type Type of the product.
     */
    public void setType(String type) {
        this.type = type;
    } //END METHOD setType

    /**
     * Gets the price of the product.
     * @return Price of the product.
     */
    public double getPrice() {
        return price;
    } //END METHOD getPrice

    /**
     * Sets the price of the product.
     * @param price Price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    } //END METHOD setPrice

    /**
     * Gets the stock of the product.
     * @return Stock of the product.
     */
    public int getStock() {
        return stock;
    } //END METHOD getStock

    /**
     * Sets the stock of the product.
     * @param stock Stock of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    } //END METHOD setStock

    /**
     * Gets the supplier associated with the product.
     * @return Supplier associated with the product.
     */
    public Supplier getSupplier() {
        return supplier;
    } //END METHOD getSupplier

    /** Sets the supplier associated with the product.
     * @param supplier Supplier associated with the product.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    } //END METHOD setSupplier

    /**
     * Gets the stock logs associated with the product.
     * @return Stock logs associated with the product.
     */
    public Set<StockLog> getStockLogs() {
        return stockLogs;
    } //END METHOD getStockLogs

    /**
     * Sets the stock logs associated with the product.
     * @param stockLogs Stock logs associated with the product.
     */
    public void setStockLogs(Set<StockLog> stockLogs) {
        this.stockLogs = stockLogs;
    }//END METHOD setStockLogs

    /**
     * Get the transaction logs associated with the product
     * @return Transaction logs associated with the product
     */
    public Set<TransactionLog> getTransactionLogs() {
        return transactionLogs;
    }//END METHOD getTransactionLogs

    /**
     * Set the transaction logs associated with the product
     * @param transactionLogs Transaction logs associated with the product
     */
    public void setTransactionLogs(Set<TransactionLog> transactionLogs) {
        this.transactionLogs = transactionLogs;
    } //END METHOD setTransactionLogs

    /**
     * Prints out the needed information
     * @return String of product information.
     */
    @Override
    public String toString() {
        return name + " - " + type + " - " + price + " - " + stock;
    } //END METHOD toString
} //END CLASS Product
