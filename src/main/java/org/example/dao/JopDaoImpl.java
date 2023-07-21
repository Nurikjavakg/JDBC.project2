package org.example.dao;

import org.example.config.Config;
import org.example.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.time.chrono.JapaneseEra.values;

public class JopDaoImpl implements JobDao {

    @Override
    public void createJobTable() {

            String sql = "create table jobs(" +
                    "id serial PRIMARY KEY, " +
                    "position varchar, " +
                    "profession varchar, " +
                    "description varchar, " +
                    "experience varchar)";

            try (Connection connection = Config.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                System.out.println("Created table!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    @Override
    public void addJob(Job job) {
     String sql="insert into jobs("+
             "position,profession,description,experience"+
              ")values (?,?,?,?)";
       try(Connection connection = Config.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setString(1,job.getPosition());
           preparedStatement.setString(2,job.getProfession());
           preparedStatement.setString(3,job.getDescription());
           preparedStatement.setInt(4,job.getExperience());


           preparedStatement.executeUpdate();
           System.out.println("New job successfully added!");

       }catch (SQLException e){
           System.out.println(e.getMessage());
       }

    }


    @Override
    public Job getJobById(Long jobId) {
        String sql = "select * from jobs where id=?";

        try(Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,jobId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                );


            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs ORDER BY experience " + ascOrDesc;

        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;
        String sql= " select j.* from employees as em join jobs  j on em.job_id=j.id where em.id=?;";

            try(Connection connection = Config.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job=new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                );


            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }


    @Override
    public void deleteDescriptionColumn() {
        String sql = "Alter table jobs column description";
        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
