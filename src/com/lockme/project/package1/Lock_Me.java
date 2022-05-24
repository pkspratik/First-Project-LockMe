package com.lockme.project.package1;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/*
 This class allows users to find, create and delete files from the file
system.
*/
public class Lock_Me {
	static File foldername = new File("F://FirstProject");
	// Just one sc is use for taking  input from user 
	static Scanner sc = new Scanner(System.in).useLocale(Locale.US);
	
	public static void main(String[] args) {
		//Boolean to identify if the loop continues or not
		boolean b_exit = false;
		// Option Selected
		Integer response = 0;
		// Exits when user selects option 5
		while (!b_exit) {
			// The menu is painted on screen
			outputConsole();
			try {
				// User input is read
				response = Integer.parseInt(sc.nextLine());
				MessagePrint("  Selected: " + response);
				// Choose the option selected by user
				switch (response) {
				case 1: {
					//All files are painted on screen
					Random random = new Random();
					int rn = random.nextInt(6) + 1;
					switch (rn) {
					case 1:
						// Get all files using a for loop
						getAllFiles_forLoop();
						break;
					case 2:
						// Get all files using a while loop
						getAllFiles_while();
						break;
					case 3:
						// Get all files using a for each loop
						getAllFiles_forEachLoop();
						break;
					case 4:
						// Get all files using an iterator of a linkedlist
						getAllFiles_Iterator();
						break;
					case 5:
						// Get all files using an iterator of a linkedlist
						getAllFiles_LambdaExpression();
						break;
					case 6:
						// Get all files using the Enumeration Interface
						getAllFiles_EnumerationInterface();
						break;
					} break;
				} case 2: {
					//User defines name and contents of a new file
					createFile(sc);
					break;
				} case 3: { //User chooses a file to delete
					deleteFile(sc);
					break;
				} case 4: {
					//Determine is a file exists
					searchFiles(sc);
					break;
				} case 5: {
					//Exit the program
					b_exit = true;
					break;
				} default: {
					//On error the program ends
					System.out.println(" Enter Valid option");
					b_exit = true;
				}
				}
			}catch(NumberFormatException e){
				MessagePrint("Please print only numbers");
			}
		}
	}
	/*
	  Get All files using a for loop
	 */
	public static void getAllFiles_forLoop() {
		//Name of files are stored in an array
		File[] listOfFiles = foldername.listFiles();
		//We use an Arraylist to contain the list of files
		List<File> alListOfFiles = new ArrayList<File>();
		Collections.addAll(alListOfFiles, listOfFiles);
		//Read the Arraylist using a for loop with the name of the files1
		try {
			for (int i = 0; i < alListOfFiles.size(); i++) {
				System.out.println(alListOfFiles.get(i));
			}
			//On error an exception is raised
		} catch (Exception e) {
			MessagePrint("Error: file not found");
		}
	}
	/*
	  Get all files using a while loop and a linkedlist
	 */
	public static void getAllFiles_while() {
		File[] listOfFiles = foldername.listFiles();
		LinkedList<File> llListOfFiles = new LinkedList<File>();
		//Get the list into the linkedlist
		Collections.addAll(llListOfFiles, listOfFiles);
		int counter = 0;
		//Traverse the linkedlist
		while (llListOfFiles.size() > counter) {
			System.out.println(llListOfFiles.get(counter));
			counter++;
		}
	}
	/*
	  Get all the files using a for each loop
	 */
	public static void getAllFiles_forEachLoop() {
		File[] listOfFiles = foldername.listFiles();
		//Use a simplified loop
		for (File myFile : listOfFiles) {
			System.out.println(myFile.getName());
		}
	}
	/*
	  Get all the files using a lambda expression
	 */
	public static void getAllFiles_LambdaExpression() {
		File[] listOfFiles = foldername.listFiles();
		List<File> alListOfFiles = new ArrayList<>();
		Collections.addAll(alListOfFiles, listOfFiles);
		//The lambda expression get the file and prints it
		alListOfFiles.forEach((file) -> {
			System.out.println(file.getName());
		});
	}
	
	 // Get all files using the enumeration interface 
	public static void getAllFiles_EnumerationInterface() {
		File[] listOfFiles = foldername.listFiles();
		List<File> alListOfFiles = new ArrayList<>();
		Collections.addAll(alListOfFiles, listOfFiles);
		//Use the interface to iterate through the list elements
		Enumeration<File> e = Collections.enumeration(alListOfFiles);
		while (e.hasMoreElements()) {
			System.out.println(e.nextElement().getName());
		}
	}
	
	// this method is return all existing files
	public static void getAllFiles_Iterator() {
		//Name of files are stored in an array
		File[] listOfFiles = foldername.listFiles();
		//We use an Arraylist to contain the list of files
		List<File> alListOfFiles = new ArrayList<File>();
		Collections.addAll(alListOfFiles, listOfFiles);
		LinkedList<File> llListOfFiles = new LinkedList<>(alListOfFiles);
		//Read the Arraylist using an iterator with the name of the files
		try {
			Iterator<File> alListOfFilesIterator = llListOfFiles.iterator();
			while (alListOfFilesIterator.hasNext()) {
				System.out.println(alListOfFilesIterator.next());
			}
			//On error an exception is raised
		} catch (Exception e) {
			MessagePrint("Error: file not found");
		}
	}
	
	// this is delete method.delete existing file 
	public static void deleteFile(Scanner scdelete) {
		try {
			//Read the name of the file to delete
			System.out.println("Write the name of the file you want to delete:");
			File fileToDelete = new File(foldername + "//" +
					scdelete.nextLine()+".txt");
			//On success
			if (fileToDelete.delete()) {
				MessagePrint("File deleted successfully.");
			} else {
				//On error
				MessagePrint("There was an error deleting the file");
			}
			//On error an exception is raised
		} catch (Exception e) {
			MessagePrint("There was an error deleting the file");
		}
	}
	
	//this method is use for search existing file 
	public static void searchFiles(Scanner scSearch) {
		try {
			//Name of the file to find
			System.out.println("Write the name of the file you want to find:");
			File fileTofind = new File(foldername + "//" +
					scSearch.nextLine()+".txt");
			// If the file existes
			if (fileTofind.exists()) {
				MessagePrint("File exists");
			} else {
				//If not a message is sent
				MessagePrint("File does not exist");
			}
			//On error an exception is raised
		} catch (Exception e) {
			MessagePrint("There was an error searching the file.");
		}
	}
	
	// this method use for create new file
	public static void createFile(Scanner scCreate) {
		//Writer object to use
		FileWriter writer = null;
		try {
			//Read the name of the file to create
			System.out.println("What is the name of your new file?");
			File newFile = new File( "F://FirstProject//" +scCreate.nextLine()+".txt");
			writer = new FileWriter(newFile);
			//Read the contents of the file to create
			System.out.println("And the contents of your file are...");
			writer.write(scCreate.nextLine());
			writer.close();
			MessagePrint("File created successfully");
			//On error an exception is raised
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	 
	// print method of output 
	public static void outputConsole() {
		System.out.println("\n");
		System.out.println("****************************************************");
		System.out.println("********** Welcome to LockMe.com *******");
		System.out.println("****************************************************");
		System.out.println("*********** Designed by Pratik Fontanot ***********");
		System.out.println("****************************************************");
		System.out.println(" 1) Display all the files");
		System.out.println(" 2) Add a new file ");
		System.out.println(" 3) Delete a file ");
		System.out.println(" 4) Search a file ");
		System.out.println(" 5) Exit ");
		System.out.println("");
		System.out.println("  Select an option ");
	}
	static void MessagePrint(String message){
		System.out.println(" -----------------------------");
		System.out.println(" " + message);
		System.out.println(" -----------------------------");
	}
}