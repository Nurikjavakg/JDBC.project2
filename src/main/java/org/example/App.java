package org.example;

import org.example.config.Config;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeServiceImpl;
import org.example.service.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
       // Config.getConnection();

        JobServiceImpl jobService = new JobServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        while (true){
            int number = new Scanner(System.in).nextInt();
            switch (number){
                case 1-> {
                    jobService.createJobTable();
                }
                case 2->{
                    employeeService.createEmployee();
                }
                case 3->{
                    jobService.addJob(new Job("Mentor","java","Backend developer",3));

                }
                case 4->{
                    System.out.println(jobService.getJobById(1L));
                }
                case 5->{
                    System.out.println(jobService.sortByExperience("asc"));
                }
                case 6->{
                    System.out.println(jobService.getJobByEmployeeId(2L));

                }
                case 7->{
                    jobService.deleteDescriptionColumn();
                }
                case 8->{
                    employeeService.addEmployee(new Employee("Tom","Krus",56,"krus@gmail.com",1));
                }
                case 9->{
                    employeeService.dropTable();
                }
                case 10->{
                    employeeService.cleanTable();
                }
                case 11->{
                    employeeService.updateEmployee(1L,new Employee("Tom","Hardy",39,"hardy@gmail.com",2));
                }
                case 12->{
                    System.out.println(employeeService.getAllEmployees());
                }
                case 13->{
                    System.out.println(employeeService.findByEmail("bit@gmail.com"));
                }
                case 14->{
                    System.out.println(employeeService.getEmployeeById(2L));
                }
                case 15->{
                    System.out.println(employeeService.getEmployeeByPosition("Instructor"));;
                }

            }
            }

    }
}
