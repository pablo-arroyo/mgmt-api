
# Instructions to run
For this project you'll need PostgreSQL & Java 11 on your local machine.

Create a new user called `uni` with your preferred password (just remember to update it in the `application.properties` file).

Create a new database called `my_university` whose owner would be the newly created `uni` user.

Run the `my_university.sql` script file in the root of this project.

After that, you'll be able to run the project in your local machine and hit the created endpoints.

# Design considerations
 * Used database indexes for `id` columns on all tables for better performance.
 * Used an `MVC` approach for the general structure of the project.
 * Used Java `Streams` for grouping professor with their respective courses to avoid repeated data and a more functional approach. 

# Improvements
  * Create a docker container for the database.
  * Cause of time issues, definitely add more robust Unit Tests and different scenarios.
  * Add integration testing for all the created endpoints.
  * Look for OOP design patterns that could be applied in the current structure.
  * There's a bug where a schedule cannot be created, fix that.


