package peaksoft.dao;

import peaksoft.config.Util;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao{
    Connection connection = Util.getConnection();
    public void createJobTable() {
        String query = """
                create table if not exists job(id serial primary key,
                position varchar,
                profession varchar,
                description varchar,
                experience int
     
                );
                """;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Successfully created job table... ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addJob(Job job) {
        String query = """
                insert into job(position,profession,description,experience)
                values(?,?,?,?);
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3,job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println(job.getPosition() + " successfully added... ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Job getJobById(Long jobId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from job where id = ?");
            preparedStatement.setLong(1,jobId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Job job = new Job();

            if(!resultSet.next())
                System.out.println("Does not exists");

            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getString(2));
            job.setProfession(resultSet.getString(3));
            job.setDescription(resultSet.getString(4));
            job.setExperience(resultSet.getInt(5));

            resultSet.close();
            return job;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobList = new ArrayList<>();
        String query = """
                select * from job
                order by experience desc;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
                jobList.add(job);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jobList;
    }

    public Job getJobByEmployeeId(Long employeeId) {
        String query = """
                select job_id,position,profession,description,experience 
                from job join employee e on job.id = e.job_id
                where e.id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,employeeId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteDescriptionColumn() {

    }
}
