package com.LeeGlen;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Manipulates data being passed through and from the database.
 */
public class Model {

    /**
     * Is used to create sessions to pull data from the database.
     */
    private Session session;

    /**
     * Checks to see if user defined details match details with the data in employee table.
     *
     * @param username The username the user specified.
     * @param password The password the user specified.
     * @return Either a job type or null.
     * @throws HibernateException If the database isn't running.
     */
    public String checkDetails(String username, String password) throws HibernateException {
        //Starts the hibernate session
        session = sessionStart();
        session.beginTransaction();
        //Runs a query to get all data from the employee table
        String HQL = "from Employee";
        Query q = session.createQuery(HQL);
        List<Employee> employees = q.getResultList();
        //Runs a query to get all data from the job history table
        HQL = "FROM JobHistory";
        q = session.createQuery(HQL);
        List<JobHistory> jobHistories = q.getResultList();
        //Finds the matching employee and then return their job type
        for (Employee employee :
                employees) {
            if (username.equals(employee.getId() + employee.getFirst_name()) && BCrypt.checkpw(password, employee.getPassword())) {
                for (JobHistory history :
                        jobHistories) {
                    if (history.getEmployee().getId() == employee.getId() && history.getEnd_date() == null) {
                        String jobType = history.getType();
                        return jobType;
                    }//END IF
                }//END FOREACH
            }//END IF
        }//END FOREACH
        //If there is no matching employee returns null
        return null;
    }//END METHOD checkDetails

    /**
     * Will find a product specified by the user and then adds stock specified again by the user
     * and then stores a log about the user adding the stock.
     *
     * @param productName The product name user specified.
     * @param stockAdded  The amount of stock the user wants to add.
     * @param username    The username of the user to identify who this employee is.
     * @return Either Found Product or Can't Find Product.
     */
    public String addStock(String productName, int stockAdded, String username) {
        boolean found = false;
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
            openSession();
        }//END IF
        //Runs a query to get all data from the product table
        String HQL = "from Product";
        Query q = session.createQuery(HQL);
        List<Product> list = q.getResultList();
        //Searches for the products name or id and will add the stock
        for (Product product :
                list) {
            try {
                if (product.getName().equals(productName) || product.getId() == Integer.parseInt(productName)) {
                    StockLog stockLog = new StockLog();
                    //Runs queries to get all data from the employee and stock log table
                    HQL = "from Employee";
                    q = session.createQuery(HQL);
                    List<Employee> employees = q.getResultList();
                    HQL = "from StockLog";
                    q = session.createQuery(HQL);
                    List<StockLog> stockLogs = q.getResultList();
                    //Will create a new log in stock log table
                    for (Employee employee :
                            employees) {
                        if ((employee.getId() + employee.getFirst_name()).equals(username)) {
                            stockLog.setId(stockLogs.size() + 1);
                            stockLog.setDateOfStockAdded(new Date());
                            stockLog.setEmployee(employee);
                            stockLog.setProduct(product);
                            session.save(stockLog);
                        }//END IF
                    }//END FOREACH
                    //Will add up the stock of the product
                    product.setStock(product.getStock() + stockAdded);
                    found = true;
                    break;
                }//END IF
            } catch (NumberFormatException e) {
                //Returns the string back to the original call if its the last product.
                if (product.getId() == list.size()) {
                    return "Can't Find Product";
                }
            }//END TRY/CATCH
        }//END FOR
        //If product not found will return not found string
        if (!found) {
            return "Can't Find Product";
        }
        //Will save what just happened to the database to save the data and will return found product string.
        session.getTransaction().commit();
        session.close();
        return "Found Product";
        //If the id isn't a number
    }//END METHOD addStock

    /**
     * Will search for a product specified by the user.
     *
     * @param productName The product the user has specified.
     * @return All of the product with that name.
     */
    public ArrayList<Product> searchProduct(String productName) {
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
            openSession();
        }//END IF
        ArrayList<Product> theProducts = new ArrayList<>();
        //Runs a query to get all data from the product table
        String HQL = "from Product";
        Query q = session.createQuery(HQL);
        List<Product> products = q.getResultList();
        //Looks for the product specified by the user
        for (Product product :
                products) {
            try {
            if (product.getName().equalsIgnoreCase(productName) || product.getId() == Integer.parseInt(productName)) {
                theProducts.add(product);
                break;
            }
            } catch (NumberFormatException e) {
                //Returns null back to the original call
            if (product.getId() == products.size()) {
                return null;
            }
            }//END TRY/CATCH
        }//END FOREACH
        //If there is no product found then return null
        if (theProducts.isEmpty()) {
            return null;
        }//END IF
        //Returns the arraylist
        return theProducts;
    }//END METHOD searchProduct


    /**
     * Will get all of the transactions specified by a certain date.
     *
     * @param date Date specified by user.
     * @return All of the transactions.
     */
    public ArrayList<TransactionLog> displayTransactions(LocalDate date) {
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
            openSession();
        }//END IF
        //Initialise variables
        //Defines how the date is formatted
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Gets the date and turns it into a string
        String dateOfTransaction = format.format(java.sql.Date.valueOf(date));
        String dateForTransaction;
        ArrayList<TransactionLog> transactionLogsArray = new ArrayList<>();
        //Runs a query to get all data from the transaction log table
        String HQL = "FROM TransactionLog";
        Query q = session.createQuery(HQL);
        List<TransactionLog> transactionLogs = q.getResultList();
        //Looks for all transactions that happened on users specified date
        for (TransactionLog transactionLog :
                transactionLogs) {
            dateForTransaction = format.format(transactionLog.getYear());
            if (dateForTransaction.equals(dateOfTransaction)) {
                transactionLog.getCustomer();
                transactionLog.getProduct();
                transactionLog.getEmployee();
                //Stores the result into the arraylist
                transactionLogsArray.add(transactionLog);
            }//END IF
        }//END FOREACH
        //Returns the arraylist
        return transactionLogsArray;
    }//END METHOD displayTransactions

    /**
     * Will get the information of either employees, customers or suppliers.
     *
     * @param choice Choice of wanted data specified by user.
     * @return Either employee, customer or supplier info.
     */
    public ArrayList displayEmployeeCustomerSupplierInfo(String choice) {
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
            openSession();
        }//END IF
        //If the user has chosen employees
        if (choice.equalsIgnoreCase("Employees")) {
            //Runs a query to get all data from the employee table
            String HQL = "FROM Employee";
            Query q = session.createQuery(HQL);
            List<Employee> employees = q.getResultList();
            //Returns the results
            return new ArrayList<>(employees);
        } else if (choice.equalsIgnoreCase("Suppliers")) {
            //Starts the hibernate session
//            session = sessionStart();
//            session.beginTransaction();
            //Runs a query to get all data from the transaction log table
            String HQL = "FROM Supplier";
            Query q = session.createQuery(HQL);
            List<Supplier> suppliers = q.getResultList();
            //Returns the results
            return new ArrayList<>(suppliers);
        } else if (choice.equalsIgnoreCase("Customers")) {
            //Starts the hibernate session
//            session = sessionStart();
//            session.beginTransaction();
            //Runs a query to get all data from the transaction log table
            String HQL = "FROM Customer";
            Query q = session.createQuery(HQL);
            List<Customer> customers = q.getResultList();
            //Returns the results
            return new ArrayList<>(customers);
        } else {
            return null;
        }//END IF/ELSE
    }//END METHOD displayEmployeeCustomerSupplierInfo


    /**
     * Gets the information needed for one of the four reports.
     *
     * @param report Choice of report specified by user.
     * @return The report.
     */
    public StringBuilder generateReport(String report) {
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
            openSession();
        }//END IF
        //Initialise variables
        String tempMessage;
        StringBuilder theReport = new StringBuilder();
        //Checks to see which report the user has chosen
        switch (report) {
            //If the user has chosen to see how much the employee's have sold
            case "Employee Sold Most":
                //Runs a query to get all of the employees with the amount of transactions associated with them from the transaction log table
                String HQL = "SELECT employee.id, count(*) FROM TransactionLog GROUP BY employee.id ORDER BY count(*) DESC";
                Query q = session.createQuery(HQL);
                List<Object[]> eCounts = q.getResultList();
                //Runs a query to get all data from the employee table
                HQL = "FROM Employee";
                q = session.createQuery(HQL);
                List<Employee> employees = q.getResultList();
                //Creates the title of the report
                theReport.append("Employees Who've Sold The Most!\n");
                //Creates the report
                for (Object[] countings :
                        eCounts) {
                    for (Employee employee :
                            employees) {
                        //Checks for matching id
                        if (employee.getId() == (int) countings[0]) {
                            //Creates the report by using the employees first name and last name and their associated count.
                            tempMessage = employee.getFirst_name() + " " + employee.getLast_name() + " Has Sold " + countings[1] + " Products!\n";
                            theReport.append(tempMessage);
                        }//END IF
                    }//END FOREACH
                }//END FOREACH
                break;
            case "Most Popular Product - All Time":
                //Runs a query to get all of the products with the amount of transactions associated with them from the transaction log table
                HQL = "SELECT product.id, count(*) FROM TransactionLog GROUP BY product.id ORDER BY count(*) DESC";
                q = session.createQuery(HQL);
                List<Object[]> pCounts = q.getResultList();
                //Runs a query to get all data from the product table
                HQL = "FROM Product";
                q = session.createQuery(HQL);
                List<Product> products = q.getResultList();
                //Creates the title of the report
                theReport.append("Most Popular Products of All Time:\n");
                //Creates the report
                for (Object[] countings :
                        pCounts) {
                    for (Product product :
                            products) {
                        //Checks for matching id
                        if (product.getId() == (int) countings[0]) {
                            //Creates the report by using the products name and their associated count.
                            tempMessage = product.getName() + " Has Been Sold " + countings[1] + " Times!\n";
                            theReport.append(tempMessage);
                        }//END IF
                    }//END FOREACH
                }//END FOREACH
                break;
            case "Products Out of Stock":
                //Runs a query to get the name of the product that are out of stock from the product table
                HQL = "SELECT name FROM Product WHERE stock = 0";
                q = session.createQuery(HQL);
                List<String> stocks = q.getResultList();
                //Creates the title of the report
                theReport.append("Products That Are Out Of Stock:\n");
                for (String stock :
                        stocks) {
                    //Creates the report by using the name of the product and a message saying it is out of stock
                    tempMessage = "The Product " + stock + " is currently out of stock!\n";
                    theReport.append(tempMessage);
                }//END FOREACH
                break;
            default:
                //Initialise variables
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                //Checks to see if the user has chosen to see all the transactions from the previous week
                if (report.equals("By Week")) {
                    //Gets the previous week
                    calendar.add(Calendar.WEEK_OF_YEAR, -1);
                    //Creates the title of the report
                    theReport.append("Popular Products of The Last Week:\n");
                    //Checks to see if the user has chosen to see all the transactions from the previous month
                } else if (report.equals("By Month")) {
                    //Gets the previous month
                    calendar.add(Calendar.MONTH, -1);
                    //Creates the title of the report
                    theReport.append("Popular Products Of The Last Month:\n");
                    //Checks to see if the user has chosen to see all the transactions from the previous year
                } else if (report.equals("By Year")) {
                    //Gets the previous year
                    calendar.add(Calendar.YEAR, -1);
                    //Creates the title of the report
                    theReport.append("Popular Products Of The Last Year:\n");
                }//END IF/ELSE
                //Runs a query to get all of the transactions from a certain time frame (Last Week, Month or Year) from the transaction log table
                String dateHQL = "FROM TransactionLog WHERE year <= current_date and year >= ?1";
                q = session.createQuery(dateHQL);
                //Sets the ?1 to be calender's time
                q.setParameter(1, calendar.getTime());
                List<TransactionLog> transactionLogs = q.getResultList();
                for (TransactionLog transactionLog :
                        transactionLogs) {
                    //Creates a report from the toString of transaction log
                    tempMessage = transactionLog.getProduct().toString() + "\n";
                    theReport.append(tempMessage);
                }//END FOREACH
                break;
        }//END SWITCH
        //Returns the created report
        return theReport;
    }//END METHOD generateReport

    /**
     * Will create a new transaction log and takes off the user defined product's stock by one.
     *
     * @param employeeUsername The username of the user to identify who the employee is.
     * @param productComingIn  The product that has been bought.
     * @return Either Product Match or Product Doesn't Match.
     */
    public String purchaseProduct(String employeeUsername, String productComingIn) {
        //If the session isn't already open session is started up.
        if (!session.isOpen()) {
           openSession();
        }//END IF
        //Runs a query to get all of the employees from the employee table
        String HQL = "FROM Employee";
        Query query = session.createQuery(HQL);
        List<Employee> employees = query.getResultList();
        //Runs a query to get all of the products from the product table
        HQL = "FROM Product";
        query = session.createQuery(HQL);
        List<Product> products = query.getResultList();
        //Runs a query to get all of the products from the transaction log table
        HQL = "FROM TransactionLog ";
        query = session.createQuery(HQL);
        List<TransactionLog> transactionLogs = query.getResultList();
        //Initialise variables
        Employee theEmployee = new Employee();
        Product theProduct;
        TransactionLog transactionLog = new TransactionLog();
        //Finds the employee that help make the transaction
        for (Employee employee :
                employees) {
            if (employeeUsername.equals(employee.getId() + employee.getFirst_name())) {
                theEmployee = employee;
            }
        }
        //If the product name given by the user matches a product name in the database a transaction log is created
        for (Product product :
                products) {
            try {
                if (productComingIn.equals(product.getName()) || Integer.parseInt(productComingIn) == product.getId()) {
                    //Transaction log is created and saved
                    theProduct = product;
                    transactionLog.setId(transactionLogs.size() + 1);
                    transactionLog.setTotal_price(theProduct.getPrice());
                    transactionLog.setYear(new Date());
                    transactionLog.setEmployee(theEmployee);
                    transactionLog.setCustomer(null);
                    transactionLog.setProduct(product);
                    session.save(transactionLog);
                    //Saves what happened to the database and closes the session.
                    session.getTransaction().commit();
                    session.close();
                    //Returns a string saying the product matches
                    return "Product Match!";
                }//END IF/ELSE
            } catch (NumberFormatException e) {
                //if the id matches the size of the product then it'll return a doesn't match string
                if (product.getId() == products.size()) {
                    return "Product Doesn't Match";
                }
            }//END TRY/CATCH
        }//END FOREACH
        //If there isn't a match then return doesn't match string
        return "Product Doesn't Match";
    }//END METHOD purchaseProduct

    /**
     * Will start up hibernate to access the database defined by the configuration file.
     *
     * @return A New Session.
     */
    private Session sessionStart() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("HibernateXML/hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = meta.getSessionFactoryBuilder().build();
        return sessionFactory.openSession();
    }//END METHOD sessionStart

    /**
     * Closes the session when exit is pressed.
     */
    public void closeSession() {
        session.close();
    } //END METHOD closeSession

    /**
     * Opens a hibernate session.
     */
    private void openSession() {
        session = sessionStart();
        session.beginTransaction();
    } //END METHOD openSession

} //END CLASS Model
