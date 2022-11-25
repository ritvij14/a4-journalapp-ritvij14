# JournalApp

Project: Journal App

Name: Ritvij Kumar Sharma

BITS ID: 2019A8PS0666G

Email: f20190666@goa.bits-pilani.ac.in

## What does the app do?

It stores journal entries along with start and end times and the date. These entries can be deleted, shared and edited.

## Process of making this app

1. Instead of adding code, I used the user interface to implement the navigation graphs and the actions. They can be referred to repeatedly using the same technique. Instead of utilising the nav graphs to transmit anything, I used sharedViewModel to pass the one bit of information I needed to pass ( StartTime Pressed or EndTime Pressed )

2. I added a delete option and a warning dialogue asking, "Are you sure?" to the menu.xml file.

3. For sharing I used Intent. I set the type of the intent as "text/*" which means it can handle texts and hyperlinks and JSON.

4. Created a simple info fragment and added it into the nav graph

## Testing

I manually tested all parts of the app along with some test cases

## Completion time

21 hours

## Difficulty

9/10