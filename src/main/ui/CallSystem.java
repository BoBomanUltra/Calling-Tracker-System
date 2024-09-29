package ui;

import model.Call;
import model.CallHistory;
import model.Date;

import java.util.*;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// A Calling Tracker System that allows users to add 
// or delete calls to their Call History. 
// Additionally, the user can view all their calls in the Call History
// and get only calls that match a given name from the user.

/*
 * Note: Around 60-70% of the following methods in this Scanner Class
 *       are using very similar templates found in the FlashCardReviewer Console Class
 *       in Lab 3. Other than that, the remaining methods are ones I wrote myself
 */
public class CallSystem {
    private static final String JSON_STORE = "./data/callhistory.json";
    private CallHistory callHistory;
    private int currentIndex = 0;
    private Scanner scanner;
    private Boolean running;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // creates an instance of the Calling Tracker System console application
    public CallSystem() {
        init();

        printDivider();
        System.out.println("Welcome to the Calling Tracker System!");
        printDivider();

        while (running) {
            mainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTs: initializes an application with starting values
    //          acts like a constructor
    private void init() {
        callHistory = new CallHistory();
        scanner = new Scanner(System.in);
        running = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // Displays and processes inputs for the main menu
    private void mainMenu() {
        displayMainMenu();
        String input = this.scanner.nextLine();
        processMainMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    private void displayMainMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new call to Call History");
        System.out.println("v: View your Call History");
        System.out.println("w: Search up all calls by a name");
        System.out.println("s: Save your CallHistory to file");
        System.out.println("l: Load your CallHistory from file");
        System.out.println("q: Exit the application");
        printDivider();
    }

     // EFFECTS: processes the user's input in the main menu
    private void processMainMenuCommands(String input) {
        printDivider();
        if (input.equals("a")) {
            addACall();
        } else if (input.equals("v")) {
            viewAllCalls();
        } else if (input.equals("w")) {
            filterHistoryByName();
        } else if (input.equals("q")) {
            closeTheApplication();
        } else if (input.equals("s")) {
            saveCallHistory();
        } else if (input.equals("l")) {
            loadCallHistory();
        } else {
            System.out.println("Invalid Input! Please try again.");
        }
        printDivider();
    }

    // EFFECTS: saves the Call History to file
    private void saveCallHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(callHistory);
            jsonWriter.close();
            System.out.println("Saved Call History to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        mainMenu();
    }
    
    // MODIFIES: this
    // EFFECTS: loads Call History from file
    private void loadCallHistory() {
        try {
            callHistory = jsonReader.read();
            System.out.println("Loaded Call History from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: Gets a filtered call history by matching for given name
    //          then displays all the calls in the filtered call history 
    //          one at a time.
    private void filterHistoryByName() {
        System.out.println("Enter a given Name to start the filtering: ");
        String input = this.scanner.nextLine();
        List<Call> filteredCalls = callHistory.filterByName(input);
        displayFilteredCallHistory(filteredCalls);

    }

    // MODIFIES: this
    // EFFECTS: Displays all calls one at a time
    private void viewAllCalls() {
        displayCallHistory(callHistory.getCallHistory());
    }


    // public void displayCallHistory(List<Call> calls) {
    //     if (calls.isEmpty()) {
    //         System.out.println("Error! There is no calls in your Call History. Add some first!");
    //         System.out.println("Moving you back to the Main Menu!");
    //         printDivider();
    //         mainMenu();
    //     }

    //     displayViewMenu();
    //     String input = "";
    //     while (!input.equals("q")) {
    //         Call currentCall = callHistory.getCallHistory().get(currentIndex);
    //         displayCall(currentCall);
    //         input = this.scanner.nextLine();
    //         handleViewCommands(input, callHistory.getCallHistory());
    //     }
    //     currentIndex = 0;
    // }

    // MODIFIES: this
    // EFFECTS: displays the callHistory and handles inputs related to viewing the calls
    //          as well as deleting an call from the callHistory
    public void displayCallHistory(List<Call> calls) {
        if (calls.isEmpty()) {
            System.out.println("Error! There are no calls in your Call History. Add some first!");
            System.out.println("Moving you back to the Main Menu!");
            printDivider();
            mainMenu();
            return;
        }
    
        currentIndex = 0;
        displayViewMenu();
        String input = "";
        while (!input.equals("q")) {
            if (currentIndex >= 0 && currentIndex < calls.size()) {
                Call currentCall = calls.get(currentIndex);
                displayCall(currentCall);
            } else {
                System.out.println("Error! No more calls to display.");
                break;
            }
            input = this.scanner.nextLine();
            handleViewCommands(input, calls);
        }
        currentIndex = 0;
    }


    // public void displayFilteredCallHistory(List<Call> calls) {
    //     if (calls.isEmpty()) {
    //         System.out.println("Error! There is no calls from your given name.");
    //         System.out.println("Add some calls that correspond with the name!");
    //         System.out.println("Moving you back to the Main Menu!");
    //         printDivider();
    //         mainMenu();
    //     }

    //     displayViewMenu();
    //     String input = "";
    //     while (!input.equals("q")) {
    //         Call currentCall = callHistory.getCallHistory().get(currentIndex);
    //         displayCall(currentCall);
    //         input = this.scanner.nextLine();
    //         handleViewCommands(input, callHistory.getCallHistory());
    //     }
    //     currentIndex = 0;
    // }

    // MODIFIES: this
    // EFFECTS: displays the filtered callHistory and handles inputs related to viewing the calls
    //          as well as deleting an call from the callHistory
    public void displayFilteredCallHistory(List<Call> calls) {
        if (calls.isEmpty()) {
            System.out.println("Error! There are no calls matching your given name.");
            System.out.println("Add some calls that correspond with the name!");
            System.out.println("Moving you back to the Main Menu!");
            printDivider();
            mainMenu();
            return;
        }
    
        currentIndex = 0;
        displayViewMenu();
        String input = "";
        while (!input.equals("q")) {
            if (currentIndex >= 0 && currentIndex < calls.size()) {
                Call currentCall = calls.get(currentIndex);
                displayCall(currentCall);
            } else {
                System.out.println("Error! No more calls to display.");
                break;
            }
            input = this.scanner.nextLine();
            handleViewCommands(input, calls);
        }
        currentIndex = 0;
    }

    private void displayCall(Call c) {
        printDivider();
        System.out.println("This Call is: " + (currentIndex + 1) + " in Call History");
        System.out.println("The Date is: " + c.getDate().getYear() + "-"
                            + c.getDate().getMonth() + "-" + c.getDate().getDay());
        System.out.println("The phone number is: " + c.getPhoneNumber());
        System.out.println("The status is: " + c.getStatus());
        System.out.println("The name is: " + c.getName());
        System.out.println("The title is: " + c.getTitle());
        int maxLineLength = 36;
        System.out.println("The summary is:\n" + formatSummary(c.getSummary(), maxLineLength));            
    }

    private void handleViewCommands(String input, List<Call> calls) {
        if (input.equals("r")) {
            removeCurrentCall(calls);
        } else if (input.equals("d")) {
            getNextCall(calls);
        } else if (input.equals("a")) {
            getPrevCall();
        } else if (input.equals("q")) {
            System.out.println("Going back to the main Menu");
            mainMenu();
        } else {
            System.out.println("Invalid Selection! Please try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the current call in the Call History
    private void removeCurrentCall(List<Call> calls) {
        Call currentCall = calls.get(currentIndex);
        callHistory.removeCall(currentCall);
        System.out.println("Removed the current Call.");
        System.out.println("Putting you back to the Main Menu.");
        printDivider();
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: if there is another call to display, increments the current index
    private void getNextCall(List<Call> calls) {
        if (currentIndex >= calls.size() - 1) {
            System.out.println("Error! No more new calls to display!");
        } else {
            currentIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: if there is a previous call to display, decrements the current index
    private void getPrevCall() {
        if (currentIndex <= 0) {
            System.out.println("Error! No more previous calls to display!");
        } else {
            currentIndex--;
        }
    }

    // MODIFIES: The String summary passed in parameter.
    // EFFECTS: Formats the summary, so that it'll automatically
    //          creates a new line after a certain line length is reached.
    //          Makes the summary look better when displayed.
    private String formatSummary(String summary, int maxLineLength) {
        StringBuilder formattedSummary = new StringBuilder();
        String[] words = summary.split(" ");
        int currentLineLength = 0;
        
        for (String word : words) {
            if (currentLineLength + word.length() + 1 > maxLineLength) {
                formattedSummary.append("\n");
                currentLineLength = 0;
            }
            if (currentLineLength > 0) {
                formattedSummary.append(" ");
                currentLineLength++;
            }
            formattedSummary.append(word);
            currentLineLength += word.length();
        }
        
        return formattedSummary.toString();
    }

    // EFFECTS: displays a list of commands that can be used in the view callHistory Menu
    private void displayViewMenu() {
        System.out.println("Enter 'r' to delete the current call.");
        System.out.println("Enter 'd' to move to the next call.");
        System.out.println("Enter 'a' to move to the previous call.");
        System.out.println("Enter 'q' to return to the menu.");
    }

    // MODIFIES: this
    // EFFECTS: adds a new call to the CallHistory
    private void addACall() {
        Date date = addADate();
        Call call = createACall(date);
        finishACall(call);
        callHistory.addCall(call);
        System.out.println("Added the call to Call History!");
    }

    // REQUIRES: User input for a year has to be four digits. 
    //           User input for a day should be either 1-2 digits and
    //           should be available in the month, (i.e. 32 is incorrect for any month,
    //           as no month has 32 days)
    // EFFECTS: gets user inputs to create a Date for the new Call
    private Date addADate() {
        System.out.println("First, let's create a date for your call!");
        System.out.println("Please enter the year of the call: ");
        System.out.println("A valid year is 4 digits, please make sure it is correct!");
        String year2 = this.scanner.nextLine();
        int year = Integer.parseInt(year2);
    
        System.out.println("Please enter the month of the call: ");
        String month = this.scanner.nextLine();
        while (!validMonth(month)) {
            System.out.println("Not a valid Month! A valid month is Uppercase, and spelled correctly");
            System.out.println("Please try again!");
            month = this.scanner.nextLine();
        }

        System.out.println("Please enter the day of the call: ");
        System.out.println("A valid day is either 1 or 2 digits! ");
        String day2 = this.scanner.nextLine();
        int day = Integer.parseInt(day2);
        Date date = new Date(year, month, day);
        System.out.println("Thank you! A Date has been created!");
        return date;
    }

    // REQUIRES: User input for a phone number should be proper
    //           (i.e. "6046621213", not something like "10294731053856138734" or "Just some Text").
    // EFFECTS: gets user input to create a basic call
    private Call createACall(Date d) {
        printDivider();
        System.out.println("Now, please enter the phone number: ");
        String phoneNumber = this.scanner.nextLine();
        printDivider();
        System.out.println("Please enter your status: ");
        System.out.println("MUST be one of the following: ");
        System.out.println("[Receiving: Accepted, Receiving: Rejected, Receiving: Missed, ");
        System.out.println("Outgoing: Accepted, Outgoing: Rejected, Outgoing: Missed]");
        String status = this.scanner.nextLine();
        while (!(checkStatus(status))) {
            printDivider();
            System.out.println("Invalid Status! Please try Again!");
            System.out.println("MUST be one of the following: ");
            System.out.println("[Receiving: Accepted, Receiving: Rejected, Receiving: Missed...");
            System.out.println("Outgoing: Accepted, Outgoing: Rejected, Outgoing: Missed]");
            status = this.scanner.nextLine();
        }
        Call call = new Call(d, phoneNumber, status);
        return call;
    }

    // MODIFIES: the Call passed in the parameter: c
    // EFFECTS: Finishes (Modifies) the Call, by allowing the user
    //          to set String values for the name, title, and summary.
    //          If the user does not wish to do so, then 
    private void finishACall(Call c) {
        printDivider();
        System.out.println("Do you want to add all name for this call? \n");
        System.out.println("y: yes");
        System.out.println("n: No, the default name will be 'unknown name' then");
        String input = this.scanner.nextLine();
        modifyCallName(c, input);

        printDivider();
        System.out.println("Do you want to add a title for this call? \n");
        System.out.println("y: yes");
        System.out.println("n: No, the default title will be 'No Title' then");
        input = this.scanner.nextLine();
        modifyCallTitle(c, input);

        printDivider();
        System.out.println("Do you want to add a summary for this call? \n");
        System.out.println("y: yes");
        System.out.println("n: No, the default summary will be left blank then");
        input = this.scanner.nextLine();
        modifyCallSummary(c, input);
    }

    // MODIFIES: the Call passed in parameter
    // EFFECTS: modifies the name of the Call depending
    //          on user input
    private void modifyCallName(Call c, String input) {
        if (input.equals("y")) {
            System.out.println("Enter a Name: ");
            String input2 = this.scanner.nextLine();
            while (wordCount(input2) > 5) {
                System.out.println("Invalid Name, Name too Long!");
                System.out.println("Keep name less than or equal to 5 words!");
                input2 = this.scanner.nextLine();
            }
            c.setName(input2);
        } else if (input.equals("n")) {
            // Nothing happens, as the constructor is already set to the default
        } else {
            System.out.println("Invalid Selection. Try again.");
        }
    }

    // MODIFIES: the Call passed in parameter
    // EFFECTS: modifies the Summary of the Call depending
    //          on user input
    private void modifyCallTitle(Call c, String input) {
        if (input.equals("y")) {
            System.out.println("Enter a Title: ");
            String input2 = this.scanner.nextLine();
            while (wordCount(input2) > 10) {
                System.out.println("Invalid Title, Title too Long!");
                System.out.println("Keep name less than or equal to 10 words!");
                input2 = this.scanner.nextLine();
            }
            c.setTitle(input2);
        } else if (input.equals("n")) {
            // Nothing happens, as the constructor is already set to the default
        } else {
            System.out.println("Invalid Selection. Try again.");
        }
    }

    // MODIFIES: the Call passed in parameter
    // EFFECTS: modifies the title of the Call depending
    //          on user input
    private void modifyCallSummary(Call c, String input) {
        if (input.equals("y")) {
            System.out.println("Enter a Summary: ");
            String input2 = this.scanner.nextLine();
            while (wordCount(input2) > 100) {
                System.out.println("Invalid Summary, Summary too Long!");
                System.out.println("Keep title less than or equal to 100 words!");
                input2 = this.scanner.nextLine();
            }
            c.setSummary(input2);
        } else if (input.equals("n")) {
            // Nothing happens, as the constructor is already set to the default
        } else {
            System.out.println("Invalid Selection. Try again.");
        }
    }

    // EFFECTS: Given a string, checks if a string is a valid month
    //          A valid month is UpperCase, and must be spelled correctly
    private boolean validMonth(String s) {
        Set<String> set = new HashSet<>();
        set.add("January");
        set.add("February");
        set.add("March");
        set.add("April");
        set.add("May");
        set.add("June");
        set.add("July");
        set.add("August");
        set.add("September");
        set.add("October");
        set.add("November");
        set.add("December");
        if (set.contains(s)) {
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: Prints a farewell message
    //          and closes the console by 
    //          by turning the running boolean off (making it false)
    private void closeTheApplication() {
        System.out.println("Thank you for using the Calling Tracker System!");
        System.out.println("Hope to see you back soon!");
        running = false;
    }

    // EFFECTS: prints out a line to act as a divider between text sections
    private void printDivider() {
        System.out.println("○------------------------------------○");
    }

    // EFFECTS: Given a String, return the word count of the string.
    //          I define a word as being seperated by space
    private int wordCount(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int count = s.split("\\s").length;
        return count;
    }

    // EFFECTS: Checks if the given status is a correct one.
    //          A correct status has to be one of the following 
    //          six options: "Receiving: Accepted", "Receiving: Rejected"
    //          "Receiving: Missed", "Outgoing: Accepted", 
    //          "Outgoing: Rejected", "Outgoing: Missed".
    private boolean checkStatus(String checkStatus) {
        Set<String> set = new HashSet<>();
        set.add("Receiving: Accepted");
        set.add("Receiving: Rejected");
        set.add("Receiving: Missed");
        set.add("Outgoing: Accepted");
        set.add("Outgoing: Rejected");
        set.add("Outgoing: Missed");
        if (set.contains(checkStatus)) {
            return true;
        } else {
            return false;
        }
    }
}