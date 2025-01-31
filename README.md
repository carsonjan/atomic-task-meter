# Atomic Task Meter

- track task duration & build daily agenda
- plan study schedules, schoolwork, and projects 

---
## Overview 

**Atomic Task Meter** is a lightweight, offline desktop application that allows users to track tasks related to multiple project profiles simultaneously. 

Different from most to-do list applications, the Atomic Task Meter is designed for uses that emphasize completing big projects by:
- breaking them down into atomic-sized tasks, and
- assigning them to a daily agenda, and
- tracking and comparing the time planned versus actually taken to finish them.

While allowing flexibility by not requiring users to time-block tasks on the calendar.  

## Project Background

I have been planning my daily tasks for years. This technique has allowed me to complete my schoolwork and routines on time without fear of missing deadlines or falling behind schedule.

However, most applications currently available on the market can be categorized into one of two types, and both of them have their own 
limitations:
- simple to-do list:  Overwhelming to read for a large amount of tasks nested into different projects and sub-projects. Especially when they span a long time period.
- calendar task blocks: Vulnerable to delays and changes, which is common in daily life. 

On top of that, most applications do not provide time-tracking functionality or cannot compare it with the planned time. In my opinion, such an evaluation process is crucial to improving personal workflow, hence increasing productivity. 

The purpose of this project is to address these issues. 

## User Stories
(functionality & command)

### Command variables
- `<name>` - a name
	- must be unique among other subjects/ events
	- must not include white space, forward slash (`/`), or double quote (`"`)
	- case insensitive
- `<date>` - a date, or an agenda's unique identifier
	- must be in the form of `01_Jan_2025`, include underscores 
	- case insensitive
- `<path>` - a path to a task
	- must be in the form of `subject//parent_task(s)/task`
	  (tasks separated with forward slash `/`)
	- must be unique
	- case insensitive
- `<time>` - a time, in hours (rounded to 0.25 h intervals)

| As a user, I want to be able to...                                | Use command...                           |
| ----------------------------------------------------------------- | ---------------------------------------- |
| **make** a new subject with its name                              | `mk subject <name>`                      |
| **make** a new event <br>with its name, and date                  | `mk event <name> <date>`                 |
| **make** a new task <br>with its path, and estimated time         | `mk task <path> <est. time>`             |
| **remove** an existing subject <br>(with all its tasks), or event | `rm subject <name>`<br>`rm event <name>` |
| **remove** a task <br>(with all its sub-tasks)                    | `rm task <path>`                         |
| **move** a task to another location                               | `mv <orig. path> <to. path>`             |
| **add** a task into an agenda                                     | `add <path> <date>`                      |
| **drop** a task from an agenda                                    | `drop <path> <date>`                     |
| **list** all tasks in a subject                                   | `ls subject <name>`                      |
| **list** all sub-tasks in a task                                  | `ls task <path>`                         |
| **list** all tasks and events in an agenda                        | `ls agenda <date>`                       |
| **start** timing a task                                           | `start <path>`                           |
| **stop** timing a task                                            | `stop <path>`                            |
| mark a task as **done**                                           | `done <path>`                            |
| mark a task as **undone**                                         | `undone <path>`                          |
| show current **state**<br>(task timing & file loaded)             | `state`                                  |
| **quit** the application                                          | `quit`                                   |
| **clear** the terminal                                            | `clear`                                  |
| get **help** with a list of commands                              | `help`                                   |
| get **help** for a specific command                               | `help <cmd. name>`                       |
