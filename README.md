# MADFitness - Workout Log Software

MADFitness is a fitness software designed to help users log and manage their workout sessions effectively. This README provides comprehensive information on the software's functionality and how to use it.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Setting Up Database Connection](#setting-up-database-connection)
4. [Pages Overview](#pages-overview) <!-- New Section -->
   - [Database Login Page](#database-login-page)
   - [New Workout Page](#new-workout-page)
   - [Manage Workouts Page](#manage-workouts-page)
   - [Exercises Page](#exercises-page)
   - [Statistics Page](#statistics-page)
5. [Screenshots and Animated GIFs](#screenshots-and-animated-gifs)
   - [Database Login Page](#database-login-page-gif)
   - [New Workout Page](#new-workout-page-gif)
   - [Manage Workouts Page](#manage-workouts-page-gif)
   - [Exercises Page](#exercises-page-gif)
   - [Statistics Page](#statistics-page-gif)
6. [Database Schema](#database-schema)
7. [Authors](#authors)
8. [Acknowledgments](#acknowledgments)

## Introduction

MADFitness is a workout log software that simplifies the process of recording and managing workout sessions. It offers four main pages: New Workout Page, Manage Workouts Page, Exercises Page, and Statistics Page.

## Features

- Log and track workout sessions
- Manage and view detailed information about past workouts
- Add and view exercises along with their descriptions
- View workout statistics through an interactive pie chart

## Getting Started

To get started with MADFitness, follow these steps:

1. Clone the repository: `git clone https://github.com/arcboy1/MADfitness.git`
2. Navigate to the project directory: `cd MADFitness`
3. Open it in your IDE

### Prerequisites

Before running MADFitness, ensure you have the following installed:

- [IntelliJ IDEA](https://www.jetbrains.com/idea/) or any IDE with Java SDK 17
- Terminal

### Setting Up Database Connection

Before running the application, set up the database connection by executing the `dbconnect.sh` script. This script will prompt you for the necessary credentials to establish a connection to the server. <!-- Marked Section -->

```bash
./dbconnect.sh
```

## Pages Overview

### Database Login Page

The Database Login Page allows users to securely log in and access their workout data. Users need to enter their credentials to gain entry.

### New Workout Page

The New Workout Page enables users to create and log a new workout session. Users can add exercises, sets, reps, and weights with ease.

### Manage Workouts Page

On the Manage Workouts Page, users can view and edit their past workout sessions. This page provides an overview of all logged workouts.

### Exercises Page

The Exercises Page offers a comprehensive database of exercises. Users can view the description of each exercise, and add exercises to their workout log.

### Statistics Page

The Statistics Page provides visual representations of the user's workout data, including progress charts and analytics over time.

## Screenshots and Animated GIFs

### Database Login Page
<img width="1016" alt="Screenshot 2023-12-05 at 4 14 19 AM" src="https://github.com/arcboy1/MADfitness/assets/146121270/1bb84d88-8a84-417e-9565-42939c9f6a5e">

### New Workout Page
![ezgif com-video-to-gif](https://github.com/arcboy1/MADfitness/assets/146121270/adf24058-1c01-4b89-820b-74d01a3c7b74)

### Manage Workouts Page
![ezgif com-video-to-gif (1)](https://github.com/arcboy1/MADfitness/assets/146121270/13aebf24-d7d7-4e94-95d7-186a5690abce)

### Exercises Page
![ezgif com-video-to-gif (2)](https://github.com/arcboy1/MADfitness/assets/146121270/f0b9259c-904a-4126-ad8f-42ea017f5c1d)


### Statistics Page 
<img width="1016" alt="Screenshot 2023-12-05 at 3 47 43 AM" src="https://github.com/arcboy1/MADfitness/assets/146121270/01e728c1-6682-4116-81d9-594a64015944">

## Database Schema

<img width="1016" alt="Screenshot 2023-12-05 at 5 11 21 AM" src="https://github.com/arcboy1/MADfitness/assets/146121270/6994bbf2-f42c-411c-bfbe-d638b64cc0ee">

## Authors
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="50%">
        <a href="https://github.com/muhamedkibash">
          <img src="https://avatars.githubusercontent.com/u/146121270?s=400&u=319002d3dc5e3f1062fad1b747e0f5ee26cffb69&v=4" width="100px;" alt="muhamedkibash"/>
          <br />
          <sub><b>Muhamed Abu Al-Kibash</b></sub>
        </a>
        <br />
        <a href="#content-muhamed" title="Content">💻</a>
      </td>
      <td align="center" valign="top" width="50%">
        <a href="https://github.com/arcboy1">
          <img src="https://avatars.githubusercontent.com/u/116191639?v=4" width="100px;" alt="noahtaggart"/>
          <br />
          <sub><b>Noah Taggart</b></sub>
        </a>
        <br />
        <a href="#content-noah" title="Content">💻</a>
      </td>
    </tr>
  </tbody>
</table>

## Acknowledgments

We extend our appreciation to the open-source community for their contributions and support in the development of MADFitness. Additionally, special thanks to the following:

- The creators of [Scene Builder](https://gluonhq.com/products/scene-builder/): For providing a valuable tool that significantly eased the development of our JavaFX user interface.
