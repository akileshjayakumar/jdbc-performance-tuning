# Java Database Connectivity (JDBC) Performance Tuning

## Overview

This project focuses on optimizing JDBC database applications to enhance performance. It includes revised versions of two tasks, showcasing significant improvements in execution time and resource management.

## Key Features

- **Performance Analysis**: Comparison between original and optimized versions of database queries.
- **Efficiency Improvements**: Implements best practices in handling JDBC connections and SQL queries.

## Setup

### Prerequisites

- **[Java JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)** or higher
- **[Oracle JDBC Driver](https://www.oracle.com/database/technologies/appdev/jdbc.html)**
- Access to an Oracle database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/akileshjayakumar/jdbc-performance-tuning
   ```
2. Navigate to the cloned directory:
   ```bash
   cd jdbc-performance-tuning
   ```

### Running the Applications

1. Compile the Java files:
   ```bash
   javac -d . src/java/*.java
   ```
2. Run the applications:
   ```bash
   java -cp . java.task1
   java -cp . java.task2
   ```

## Comparative Analysis

- **Task 1**: Original vs. Solution
  - Original: The application inefficiently processed large datasets on the client side.
  - Solution: Optimized to perform data aggregation on the database server, reducing processing time and system load.
  
- **Task 2**: Original vs. Solution
  - Original: Involved unnecessary data retrieval and processing on the client side.
  - Solution: Streamlined to execute a single, efficient SQL query, minimizing data transfer and processing time.
