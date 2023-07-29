package org.example.dao;

import org.example.config.Config;
import org.example.model.Employee;
import org.example.model.Job;
import org.postgresql.replication.PGReplicationStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.chrono.JapaneseEra.values;

public class EmployeeImpl implements EmployeeDao{
    @Override
    public void createEmployee() {
        String sql = "create table employees(" +
                "id serial primary key, " +
                "first_name varchar, " +
                "last_name varchar, " +
                "age int, " +
                "email varchar, " +
                "job_id int references jobs(id))";

        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Created table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees("+
                "first_name,last_name,age,email,job_id"+
                 ")values (?,?,?,?,?)";
                try(Connection connection = Config.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setString(1,employee.getFirstName());
                    preparedStatement.setString(2,employee.getLastName());
                    preparedStatement.setInt(3,employee.getAge());
                    preparedStatement.setString(4,employee.getEmail());
                    preparedStatement.setLong(5,employee.getJobId());

                    preparedStatement.executeUpdate();
                    System.out.println("New employee is added!");

                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }

    }

    @Override
    public void dropTable() {
        String sql="drop table employees";

        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Table is deleted");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void cleanTable() {
        String sql= "delete from employees";
        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Information is deleted");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql =  "update employees set first_name=? , last_name=?, age=?,email=?,job_id=? where id=?";
        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getJobId());
            preparedStatement.setLong(6,id);

            preparedStatement.executeUpdate();
            System.out.println("Employee is successfully updated!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees";
        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }


    @Override
    public Employee findByEmail(String email) {

        String sql= "select * from employees where email=?";

        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")
                );


            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        String sql = "select employees.*,j.* from employees" +
                "  inner join jobs j on employees.job_id=j.id where employees.id=?";
        Map<Employee,Job> employeeJobMap = new HashMap<>();


        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee= new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")
                );
                Job job=new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                );

                employeeJobMap.put(employee,job);


            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        String sql = "select em.* from employees as em inner join jobs on em.job_id=jobs.id where jobs.position=?";
        List<Employee>employees = new ArrayList<>();
        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,position);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")
                ));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return employees;
    }
}
