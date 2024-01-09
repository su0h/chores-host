# Chores
**Chores** is a chore-tracking full-stack web application that I built solely for *practice* (and potentially for personal household use). It simply displays the chore assignments for the day with the help of both Spring Boot and Angular. This repository contains the *backend* aspect of this project (proceed [here](https://www.github.com/su0h/chores-web) for the *frontend* repository).

For now, task assignments only shift among the concerned individuals - once during weekdays (for evening chores) and *potentially* twice during weekends + holidays (for afternoon + evening chores). "Potentially" because sometimes, no afternoon chores are needed here at home. For example: 

| Day 1                  | Day 2                  | Day 3                  |
|------------------------|------------------------|------------------------|
| Person A: Sweep Floors | Person A: Clean Tables | Person A: Wash Dishes  |
| Person B: Clean Tables | Person B: Wash Dishes  | Person B: Sweep Floors |
| Person C: Wash Dishes  | Person C: Sweep Floors | Person C: Clean Tables |

## ...but why?
For context, there are three (3) of us who are sharing the chore workload here at home. Each person normally has one task to do at a time, and the task assignment "shifts" on a daily basis to rotate the tasks among us (twice if it is a weekend or a holiday). 

More often than not, everyone here in the household is unable to keep track of the task assignments for the day. *Quite weird if you think of it.* Every evening, someone would be asking "Sino hain?" *Who will prepare the dining table?* "Sino ligpit?" *Who will clean up the dining table?* "Sino hugas?" *Who will wash the dishes?*. 

I have to admit, sometimes the pestering can become quite tedious and annoying personally - especially when one of the assignees is busy working. With this, as a part of my personal desire to automate as many things as possible here at home, I have decided to work on **Chores** for the convenient dissemination of information re: the chore assignment for the day.

*P.S. I decided to add a Spring Boot backend since I needed to keep track of the current state of task assignments, and I also needed to keep track of when to shift once or twice (or even undo a shift when no chores were done for a certain schedule; this required a database). I simply could not make this work with an Angular frontend alone.*

## Installation
TBD (Docker?)

## Example API Usage
TBD