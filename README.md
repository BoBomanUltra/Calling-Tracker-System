# My Personal Project

## My Proposal:

### What will the application do?

The project I plan on designing will keep track of phone calls. I've named the project **"The Calling Tracker System"**. The main idea is that a user can save a **call** into the **Call History** *(a tracker / list that records calls : I capitalize as it is a name)*. Each call with have some features associated with it *(listed below)*:
- The **phone number** of the **contact** *(other participant in the call, **contact** refers to any of these terms: the opposing caller, receiver, or recipient)*. The phone number will be of type **String** *(I wanted to make it of type **int**, but most phone numbers are larger than the maximum value an integer can hold)* and I will **not** put any limitations on the length of this phone number to be inclusive of the differing phone number formats around the globe. 
- The **name** of the contact will also be included. The name will be of type **String**. If the user does not know the name, or does not input a name for the contact, then the default name setting is just **"unknown"**. I will put a word limit of maximum **ten words**. 
- The **status** of the call can be one of the following options: "Receiving: Accepted", "Receiving: Rejected", "Receiving: Missed", "Outgoing: Accepted", "Outgoing: Rejected", or "Outgoing: Missed". The status will be of type **String**. 
- The **summary** of the discussion within in the call. The summary will be of type **String**. The summary will most definitely be capped by a word limit *(maybe 100 words, but I'm undecided as of yet)*. Also, if the user does not add in anything for the summary, it will remain **blank**.
- The **title** of the call is similar to the subject of an email. The title is just the topic of the call. The title will be of type **String**. The title will most definitely be capped by a way shorter word limit than the summary. Also, if the user does not add in any title, it will remain **"No Title"*. 
- The **date** of the call represents what day the call was made. The date will be split into **three parts**. The first part will record the year, be of type **int**. The second part will record the month, be of type **String**. Finally, the third part will record the day, be of type **int**. Therefore, the date will be represented by its own Class.

This means that every **call input** into the **Call History** will have at least the six above properties. As stated earlier, the user will be able to add *(save)* a **call** input into the **Call History**. Note, I have decided not to care if two calls are the exact same, I feel like, if the user wants to input two same calls, or similar calls, then I should allow them to do so. Also, the user will also have the ability to remove *(delete a save)* a **call** input from the **Call History**. If I'm **ahead of the project**, here are some other features I was considering *(Note: all of these listed below are a WIP, and I have not flushed out details)*:
- The **call duration** is simply how long the call went. The call duration will be represented by hours and and minutes, both of which are of type **int**. By default, the call duration will start with 0 hours and 0 minutes, unless the user modifies it. Therefore, call duration will be represented by its own Class. Note that this feature will most certainly eventually be added along in the later phases. I have chosen not to currently for **Phase 1** as I felt I already had too much on my plate. 
- The user will be able to filter the **Call History** based on these statuses. For example, if I want to find out if I missed any calls, I would be able to get a new list showing only calls that satisfy **status == "Receiving: Missed"**.
- The user will also be able to filter the **Call History** to find only calls with a specific **contact** in mind (Currently in Phase 1).
- The user will be able to sort the **Call History** or a filtered **Call History** based on **call duration**, whether in increasing or decreasing. 
- The user will be able to tag a **call** as **important**, and the **Call History** can be filtered to find calls that are only **important**. Important will probably be of type **boolean**, and it'll be a simple checkbox in the ui.
- *Note this might be too advanced, but if time permits, I want to accompolish this:* The user will be able to filter twice, on both **status** and **name**. For example, I want to be able to find only outgoing, rejected calls from "Ben" in my contacts. 
- The user will be able to use a **search bar** to filter the **Call History** to find the title they are looking for.
- The application will have each **call** showing everything, but the **summary** in the **Call History**. If the user clicks on the **call**, then it will open up a more in-detailed call, showcasing the **summary**. The current view that I will be doing first will probably show everything, as it is easier to accomplish. 

### Who will use it?

The **Calling Tracker System** is designed for anyone who wants to efficiently manage and organize their phone call history. Simply put, the application is targeted at anybody with a phone that wants to record their calls in more-depth than what the simple phone app could offer. 

### Why is this project of interest to me?

**For the most part,** I want to do this project for my convenience. I get many calls and also call others, and I frequently forget what those calls are about. The summary feature helps me keeps track of important information. Sometimes, I want to find a call or contact a lot easier, which the filter and sorting features will help with. Basically, the functionalities of this **Calling Tracker System** are made by me to improve my quality of life, which I figured could be beneficial for others as well. Lastly, it looks good to design an application improving a certain practicality for people. 

## User Stories:

- As a user, I want to be able to add a **call** to my **Call History**.
- As a user, I want to be able to view a **list of calls** in my **Call History**.
- As a user, I want to be able to delete a **call** in my **Call History**.
- As a user, I want to be able to filter the **Call History** based on a name.
- As a user, I want to be given the option in the *main menu* to *load* my **Call History** from *file*.
- As a user, I want to be given the option in the *main menu* to *save* my **Call History** to *file*.

# Instructions for Grader

- You can add multiple Calls to Call History using the AddCallPanel (clicking the first button in main menu).
- You can view all your Calls in Call History using the CallHistoryPanel (clicking the second button in main menu).
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by removing
a Call you want from the Call History, simply by pressing the delete button next to the Call you wish to remove
in the Call History. 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking
on the third button in the main menu, and searching for a name. If a name matches anything to a subset of Calls, 
they will appear as a filtered Call History. If not, the panel simply asks the user to try again. 
- You can generate the third required action related to the user story "adding multiple Xs to a Y" by double-clicking
on the summary cell of any Call in the Call History to see the summary in-detail.
- You can locate my visual component by starting the application, the splash screen will appear for three seconds
before changing to the main menu
- You can save the state of my application by clicking the fourth button in main menu. 
- You can reload the state of my application by clicking the last button in main menu.

## Phase 4: Task 2

Thu Aug 08 22:48:40 PDT 2024

Added a call to the Call History: Name is Jeremy, Date is 2022-Mar-1

Thu Aug 08 22:49:07 PDT 2024

Added a call to the Call History: Name is Jonny, Date is 2021-June-9

Thu Aug 08 22:50:41 PDT 2024

Added a call to the Call History: Name is Jeremy, Date is 2020-July-9

Thu Aug 08 22:51:09 PDT 2024

Filtered the Call History by name: Jeremy, and got this many matching results: 2

Thu Aug 08 22:51:09 PDT 2024

Filtered the Call History by name: Jeremy, and got this many matching results: 2

Thu Aug 08 22:51:12 PDT 2024

Removed a call from Call History: Name is Jeremy, Date is 2022-Mar-1

Thu Aug 08 22:51:12 PDT 2024

Removed a call from Call History: Name is Jeremy, Date is 2020-July-9

Thu Aug 08 22:51:17 PDT 2024

Filtered the Call History by name: Jeremiah, and got this many matching results: 0

Thu Aug 08 22:51:22 PDT 2024

Filtered the Call History by name: Jonny, and got this many matching results: 1

Thu Aug 08 22:51:22 PDT 2024

Filtered the Call History by name: Jonny, and got this many matching results: 1

Thu Aug 08 22:51:23 PDT 2024

Removed a call from Call History: Name is Jonny, Date is 2021-June-9

### Example 2 (But this is the one a bit weird)

Fri Aug 09 10:14:08 PDT 2024

Added a call to the Call History: Name is Jeremy, Date is 2022-May-30

Fri Aug 09 10:14:24 PDT 2024

Added a call to the Call History: Name is Jonny, Date is 2023-July-30

Fri Aug 09 10:14:30 PDT 2024

Filtered the Call History by name: Jeremiah, and got this many matching results: 0

Fri Aug 09 10:14:32 PDT 2024

Filtered the Call History by name: Jeremy, and got this many matching results: 1

Fri Aug 09 10:14:32 PDT 2024

Filtered the Call History by name: Jeremy, and got this many matching results: 1

Fri Aug 09 10:14:33 PDT 2024

Removed a call from Call History: Name is Jeremy, Date is 2022-May-30

Fri Aug 09 10:14:35 PDT 2024

Displaying the entire Call History!

Fri Aug 09 10:14:36 PDT 2024

Removed a call from Call History: Name is Jonny, Date is 2023-July-30

## Phase 4: Task 3

Honestly, the Date class is somewhat not needed. I could have easily just implemented the year, month, and day fields into the Call class, 
and removed the Date class for less coupling, but also better cohesion. I did not need to split the date off, as the Single Responsibility Principle would consider those three fields as a single responsibility for class. 

The second thing of note I could've done is just remove the SummaryCellRenderer and the SummaryCellEditor. I had created these classes as I thought I could improve my summary viewing experience, but it did not work as intended. First of, the showSummaryDialog() in the CallHistoryPanel is the one that actually opens the new JTextArea pop-up and shows the detailed summary with line-wrapping. It is not the Summary Cell Editor that pop-ups, because I couldn't get it to work for some reason, and I figured it was easier to just create my own JTextArea and mouseListener in the same class to create that pop-up, which it was and it worked, so I kind of just had SummaryCellEditor there, and just left it there. It does absolutely nothing. As for SummaryCellRenderer it does remove the "..." cause of line-wrapping feature in the renderer, but honestly other than that. It doesn't provide anything, so I could just have the default JLabel rendering for the summary cell, and it would not change much at all, except the "...". Like I could just remove both these classes, have them run on the default renderer and cell editor and the program would work just fine. 

