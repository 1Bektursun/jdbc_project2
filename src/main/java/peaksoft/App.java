package peaksoft;

import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.services.EmployeeService;
import peaksoft.services.EmployeeServiceImpl;
import peaksoft.services.JobService;
import peaksoft.services.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        JobService jobservice = new JobServiceImpl();

        EmployeeService employeeService = new EmployeeServiceImpl();



        while(true){
            System.out.println("\n" +
                    "1. Create job table\n" +
                    "2. Create employee table\n" +
                    "3. Drop employee table\n" +
                    "4. Clean employee table\n" +
                    "5. Add employee \n" +
                    "6. Get all employees\n" +
                    "7. Find by email employee\n" +
                    "8. \n" +
                    "9. Get employee by position\n" +
                    "10. Add job\n" +
                    "11. Get job by id \n" +
                    "12. Sort by experience \n" +
                    "13. \n" +
                    "Exit");


            String str = scanner.nextLine();
            if(str.equals("Exit"))
                break;

            switch (str) {
                case "1":
                    jobservice.createJobTable();
                    break;
                case "2":
                    employeeService.createEmployee();
                    break;
                case "3":
                    employeeService.dropTable();
                    break;
                case "4":
                    employeeService.cleanTable();
                    break;
                case "5":
                    employeeService.addEmployee(new Employee(1L,"Muhammed","Nurbai uulu",23,"mentor@gmail.com",1));
                    break;
                case "6":
                    System.out.println(employeeService.getAllEmployees());
                    break;
                case "7":
                    System.out.println(employeeService.findByEmail("mentor@gmail.com"));
                    break;
                case "8":
                    break;
                case "9":
                    System.out.println(employeeService.getEmployeeByPosition("Instructor"));
                    break;
                case "10":
                    jobservice.addJob(new Job(1L,"Mentor","Java","Java developer",2));
                    break;
                case "11":
                    System.out.println(jobservice.getJobById(2L));
                    break;
                case "12":
                    System.out.println(jobservice.sortByExperience(""));


            }
        }

        //employeeService.dropTable();
        //employeeService.cleanTable();
        //employeeService.addEmployee(new Employee(1L,"Muhammed","Nurbai uulu",23,"mentor@gmail.com",1));
        //employeeService.addEmployee(new Employee(2L,"Nurbek","Zholdoshbaev",26,"nur@gmail.com",2));
        //employeeService.addEmployee(new Employee(3L,"Aijamal","Akylbekova",25,"aijamal@gmail.com",3));
        //jobservice.addJob(new Job(1L,"Mentor","Java","Java developer",2));
        //jobservice.addJob(new Job(2L,"Management","manager","manager ",3));
        //jobservice.addJob(new Job(3L,"Instructor","Java","Java developer",4));
    }
}
