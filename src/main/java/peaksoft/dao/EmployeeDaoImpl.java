package peaksoft.dao;

import peaksoft.config.Util;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao{
    Connection connection = Util.getConnection();
    public EmployeeDaoImpl(){

    }
    public void createEmployee() {
        String query = """
                           CREATE TABLE IF NOT EXISTS employee(id serial primary key,
                           first_name varchar,
                           last_name varchar,
                           age int,
                           email varchar unique,
                           job_id integer references job(id));
                           """;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Succesfully created table employee ...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addEmployee(Employee employee) {
        String query = """
                insert into employee(first_name,last_name,age,email,job_id)
                values(?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println(employee.getFirstName() + " Soccessfully added... ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table employee");
            System.out.println("Deleted table employee... ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("truncate table employee");
            System.out.println("Table employee cleaned... ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateEmployee(Long id, Employee employee) {
        String query = """
                update employee 
                set first_name = ?,
                set last_name = ?,
                age = ?,
                set email = ?,
                job_id = ?,
                where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.setLong(6,id);

            int i = preparedStatement.executeUpdate();

            if(i > 0)
                System.out.println("Successfully updated table employee... ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployee = new ArrayList<>();


        try(Statement statement = connection.createStatement()) {

           ResultSet resultSet = statement.executeQuery("select * from employee");

            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));
                allEmployee.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allEmployee;
    }

    public Employee findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee = new Employee();

            if(!resultSet.next())
                System.out.println("Does not exists");

            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString(2));
            employee.setLastName(resultSet.getString(3));
            employee.setAge(resultSet.getInt(4));
            employee.setEmail(resultSet.getString(5));
            employee.setJobId(resultSet.getInt(6));

            resultSet.close();
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return null;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employeeList = new ArrayList<>();
        String query = """
                select employee.id,first_name,last_name,age,email,job_id 
                from employee join job j on employee.job_id = j.id
                where j.position = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();



            if(!resultSet.next())
                System.out.println("Does not exists");

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));

                employeeList.add(employee);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}
