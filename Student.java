import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.Comparator;
import java.io.FileWriter;
class Student
{
    String ID;
    String name;
    Student(String stdid, String stdname)
    {
        ID=stdid;
        name=stdname;
    }
    public String getId()
    {
        return ID;
    }
    public String getname()
    {
        return name;
    }
    public static void project1()
    {
        System.out.println("Welcome to Project 1");
        System.out.println("---------------------------");
        System.out.println("See the below menu for next steps:");
        LinkedList <Student> StudentRoster = new LinkedList<Student>();
        ArrayList<Student> StudentArray=new ArrayList<Student>();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("--------------------------------------");
            System.out.println("Enter 1 to Load Roaster File into LinkedList");
            System.out.println("Enter 2 to search Student");
            System.out.println("Enter 3 to add Student");
            System.out.println("Enter 4 to drop Student");
            System.out.println("Enter 5 to Save the LinkedList into a sorted array");
            System.out.println("Enter 6 to Save the sorted array to a different file");
            System.out.println("Enter 7 to go to Project 2");
            int opted=sc.nextInt();
            switch(opted)
            {
                case 1:
                {
                    String newline="";
                    
                    try{
                    FileInputStream roster=new FileInputStream("StudentRosterFile.txt");
                    Scanner filescan=new Scanner(roster);
              
                    while(filescan.hasNextLine())
                    {
                        newline=filescan.nextLine();
                        String [] words = newline.split(" ");
                        StudentRoster.add(new Student(words[0],words[1]));//adding the students
                        if(StudentRoster.size()>=10)
                            break;
                    }
                    filescan.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("The full list is:");
                    for(Student std:StudentRoster)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                    
                }
                case 2:
                {
                    System.out.println("Enter 1 for search by ID");
                    System.out.println("Enter 2 for search by Name");
                    int choice=sc.nextInt();
                    switch(choice)
                    {
                        case 1: 
                        {
                            System.out.println("Enter ID to search:");
                            String id=sc.next();
                            for(Student std:StudentRoster)
                            {
                                if(std.getId().equals(id))
                                {
                                    System.out.println("Student Data found");
                                    System.out.println(std.getId()+"\t"+std.getname());
                                }
                            }
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Enter name to search:");
                            String name=sc.next();
                            for(Student std:StudentRoster)
                            {
                                if(std.getname().equals(name))
                                {
                                    System.out.println("Student Data found");
                                    System.out.println(std.getId()+"\t"+std.getname());
                                }
                            }
                            break;
                        }   
                    }
                    break;
                }
                case 3:
                {
                    if(StudentRoster.size()>=10)
                    {
                        System.out.println("Sorry can't add more than 10 students");
                        break;
                    }
                    System.out.println("Enter id of student:");
                    String id=sc.next();
                    System.out.println("Enter name of student:");
                    String name=sc.next();
                    StudentRoster.add(new Student(id,name));
                    System.out.println("The student is added");
                    System.out.println("The full list is:");
                    for(Student std:StudentRoster)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Enter id of student:");
                    String id=sc.next();
                    for(Student std:StudentRoster)
                    {
                        if(std.getId().equals(id))
                        {
                            StudentRoster.remove(std);
                            System.out.println(std.getId()+"\t"+std.getname()+" is dropped.");
                            break;
                        }
                    }
                    System.out.println("The full list is:");
                    for(Student std:StudentRoster)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                }
                case 5:
                {
                    StudentArray.clear();
                    for(Student std:StudentRoster)
                    {
                        StudentArray.add(new Student(std.getId(),std.getname()));
                    }
                    System.out.println("The Sorted Student data array is:");
                    Collections.sort(StudentArray, new Comparator<Student>() {
                        public int compare(Student std1, Student std2) {
                            return std1.getname().compareTo(std2.getname());
                        }
                    });
                    for(Student std:StudentArray)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                }
                case 6:
                {
                    try{    
                       PrintStream fileStream = new PrintStream(new File("SortedStudentList.txt"));
                       for(Student std:StudentArray)
                        {
                            fileStream.println(std.getId()+" "+std.getname());
                        }
                       
                    }
                    catch(Exception e){System.out.println(e);}    
                    System.out.println("Success...");
                    break;
                }
                case 7:
                project2();
                break;
            }
        }
    }
    
    public static void project2()
    {
        System.out.println("Welcome to Project 2");
        System.out.println("-----------------------------");
        System.out.println("See the below menu for next steps:");
        Queue <Student> StudentQueue = new LinkedList<>();
        ArrayList<Student> StudentArray=new ArrayList<Student>();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("--------------------------------------");
            System.out.println("Enter 1 to Load Sorted Roaster File from Project 1 into ArrayList");
            System.out.println("Enter 2 to search Student");
            System.out.println("Enter 3 to add Student");
            System.out.println("Enter 4 to drop a Student (This will add a new student from the Waiting List Queue)");
            //System.out.println("Enter 5 to Save the LinkedList into a sorted array");
            System.out.println("Enter 5 to Save and Close");
            
            int opted=sc.nextInt();
            switch(opted)
            {
                case 1:
                
                {
                    String newline="";
                    
                    try{
                    FileInputStream roster=new FileInputStream("SortedStudentList.txt");
                    Scanner filescan=new Scanner(roster);
              
                    while(filescan.hasNextLine())
                    {
                        newline=filescan.nextLine();
                        String [] words = newline.split(" ");
                        StudentArray.add(new Student(words[0],words[1]));//adding the students
                        
                    }
                    filescan.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("The full list is:");
                    for(Student std:StudentArray)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Enter 1 for search by ID");
                    System.out.println("Enter 2 for search by Name");
                    int choice=sc.nextInt();
                    switch(choice)
                    {
                        case 1: 
                        {
                            System.out.println("Enter ID to search:");
                            String id=sc.next();
                            for(Student std:StudentArray)
                            {
                                if(std.getId().equals(id))
                                {
                                    System.out.println("Student Data found");
                                    System.out.println(std.getId()+"\t"+std.getname());
                                }
                            }
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Enter name to search:");
                            String name=sc.next();
                            for(Student std:StudentArray)
                            {
                                if(std.getname().equals(name))
                                {
                                    System.out.println("Student Data found");
                                    System.out.println(std.getId()+"\t"+std.getname());
                                }
                            }
                            break;
                        }   
                    }
                    break;
                }
                case 3:
                {
                    if(StudentArray.size()==10)
                    {
                        System.out.println("Enter id of student:");
                        String id=sc.next();
                        System.out.println("Enter name of student:");
                        String name=sc.next(); 
                        StudentQueue.add(new Student(id,name));    
                        
                        System.out.println("The student is added to the waiting list");
                    }
                    else if(StudentArray.size()<10)
                    {
                        System.out.println("Enter id of student:");
                        String id=sc.next();
                        System.out.println("Enter name of student:");
                        String name=sc.next();
                        StudentArray.add(new Student(id,name));
                        System.out.println("The student is added");
                      
                    }
                    System.out.println("The full list is:");
                    for(Student std:StudentArray)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Enter id of student:");
                    String id=sc.next();
                    for(Student std:StudentArray)
                    {
                        if(std.getId().equals(id))
                        {
                            StudentArray.remove(std);
                            System.out.println(std.getId()+"\t"+std.getname()+" is dropped.");
                            break;
                        }
                    }
                    StudentArray.add(StudentQueue.remove());
                    System.out.println("A Student from waiting list is added");
                    System.out.println("The full list is:");
                    for(Student std:StudentArray)
                    {
                        System.out.println(std.getId()+"\t"+std.getname());
                    }
                    
                    break;
                }
                case 5:
                {
                    Collections.sort(StudentArray, new Comparator<Student>() {
                        public int compare(Student std1, Student std2) {
                            return std1.getname().compareTo(std2.getname());
                        }
                    });
                    try{    
                       PrintStream fileStream = new PrintStream(new File("FinalStudentRoaster.txt"));
                       for(Student std:StudentArray)
                        {
                            fileStream.println(std.getId()+" "+std.getname());
                        }
                       
                    }
                    catch(Exception e){System.out.println(e);}    
                    System.out.println("Students are added to the Final Roaster!");
                    
                    try{    
                       PrintStream fileStream = new PrintStream(new File("WaitingList.txt"));
                       for(Student std:StudentQueue)
                        {
                            fileStream.println(std.getId()+" "+std.getname());
                        }
                       
                    }
                    catch(Exception e){System.out.println(e);}    
                    System.out.println("Waiting List is stored in a file");
                    System.exit(0);
                }
            }
            
        }
    }
    
    
    public static void main(String args[])
    {
        project1();
    }
}