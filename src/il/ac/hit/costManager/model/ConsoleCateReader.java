package il.ac.hit.costManager.model;

import java.util.Scanner;

//this class is used for testing the model and entering data manually

public class ConsoleCateReader {

    public Category readCategory(){
        int cateID;
        String cateName;

        Scanner scan = new Scanner(System.in);
        // Items item = null;
        System.out.println("Please enter Category ID:");
        cateID = scan.nextInt();
        scan.nextLine();//Enter Data
        System.out.println("Please enter Category Name:");
        cateName = scan.nextLine(); //Enter Data


        return new Category(cateID,cateName);
    }

}
