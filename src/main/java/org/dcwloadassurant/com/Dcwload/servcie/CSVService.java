package org.dcwloadassurant.com.Dcwload.servcie;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSVService {
    public void insertnewjobs(List<List<String>> valuesArray) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://ArunKumarK\\SQLEXPRESS:1433; encrypt=true; trustServerCertificate=true; databaseName=CIS",
                        "cis",
                        "Acs@12345")
//                Connection connection = DriverManager.getConnection(
//                        "jdbc:sqlserver://10.183.242.41\\SQLEXPRESS:1433; encrypt=true; trustServerCertificate=true; databaseName=Assurant",
//                        "cis-STG",
//                        "F2Qaiql3")
        ) {
            // Fetch variable IDs from the database
            Map<Integer, String> variableIds = fetchVariableIds(connection);

            int groupId = getGroupId(connection);



            for (List<String> dataRow : valuesArray) {
                if (dataRow.size() != variableIds.size()) {
                    throw new IllegalArgumentException("Data row size does not match variable count");
                }

                int varid = 1;
                for (String variableValue : dataRow) {
                    int companyId = 1;
                    String variableName = variableIds.get(varid);

                    if (variableName == null) {
                        throw new IllegalArgumentException("Variable ID " + varid + " does not exist in the database");
                    }

                    // Insert each variable-value pair into the database

                    insertRow(connection, companyId, groupId, variableName, variableValue, varid);

                    varid++;
                }
                insertintogroupstable(connection,dataRow.get(2),groupId,dataRow.get(20));
                // Increment groupId for the next data row
                groupId++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertintogroupstable(Connection connection, String flexjob,int groupid, String sla) throws SQLException {
        String insertQuery = "INSERT INTO groups VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, 1);  // Second parameter
            preparedStatement.setString(2, "Assurant");  // Third parameter
            preparedStatement.setObject(3, null);  // Fourth parameter
            preparedStatement.setInt(4, 0);  // Fifth parameter
            preparedStatement.setObject(5, null);  // Sixth parameter
            preparedStatement.setString(6, flexjob);  // Seventh parameter
            preparedStatement.setObject(7, null);  // Eighth parameter
            preparedStatement.setObject(8, null);  // Ninth parameter
            preparedStatement.setObject(9, null); // Tenth parameter
            preparedStatement.setObject(10, null); // Eleventh parameter
            preparedStatement.setObject(11, null); // Twelfth parameter
            preparedStatement.setObject(12, null); // Thirteenth parameter
            preparedStatement.setInt(13, 0);  // Fourteenth parameter
            preparedStatement.setObject(14, null); // Fifteenth parameter
            preparedStatement.setObject(15, null); // Sixteenth parameter
            preparedStatement.setObject(16, null); // Seventeenth parameter
            preparedStatement.setObject(17, null); // Eighteenth parameter
            preparedStatement.setObject(18, null); // Nineteenth parameter
            preparedStatement.setInt(19, 0);  // Twentieth parameter
            preparedStatement.setObject(20, null); // Twenty-first parameter
            preparedStatement.setObject(21, null); // Twenty-second parameter
            preparedStatement.setObject(22, null); // Twenty-third parameter
            preparedStatement.setObject(23, null); // Twenty-fourth parameter
            preparedStatement.setObject(24, null); // Twenty-fifth parameter
            preparedStatement.setObject(25, null); // Twenty-sixth parameter
            preparedStatement.setObject(26, null); // Twenty-seventh parameter
            preparedStatement.setObject(27, null); // Twenty-eighth parameter
            preparedStatement.setObject(28, null); // Twenty-ninth parameter
            preparedStatement.setObject(29, null); // Thirtieth parameter
            preparedStatement.setString(30, "No"); // Thirty-first parameter

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

        }
        insertQuery = "INSERT INTO Groups_DistributedPrint VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){

            preparedStatement.setInt(1, groupid);  // Second parameter
            preparedStatement.setObject(2, "");  // Third parameter
            preparedStatement.setObject(3, "");  // Fourth parameter
            preparedStatement.setString(4, "Lynnfield");  // Fifth parameter
            preparedStatement.setString(5, sla);  // Sixth parameter
            preparedStatement.setInt(6, 0);  // Seventh parameter
            preparedStatement.setObject(7, null);  // Eighth parameter
            preparedStatement.setObject(8, LocalDate.now());  // Ninth parameter
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static int getGroupId(Connection connection) throws SQLException {
        String sql = "SELECT ISNULL(MAX(group_id), 0) + 1 AS next_group_id FROM company_variables_groups_values";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("next_group_id");
            } else {
                throw new SQLException("Failed to get next group_id");
            }
        }
    }

    private static Map<Integer, String> fetchVariableIds(Connection connection) throws SQLException {
        Map<Integer, String> variableIds = new HashMap<>();
        String sql = "SELECT variable_id, variable_name FROM company_variables";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String variableName = resultSet.getString("variable_name");
                int variableId = resultSet.getInt("variable_id");
                variableIds.put(variableId, variableName);
            }
        }
        return variableIds;
    }

    private static void insertRow(Connection connection, int companyId, int groupId, String variableName, String variableValue, int variableId) throws SQLException {
        String sql = "INSERT INTO company_variables_groups_values (company_id, group_id, variable_name, variable_value, variable_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, companyId);
            statement.setInt(2, groupId);
            statement.setString(3, variableName);
            statement.setString(4, variableValue);
            statement.setInt(5, variableId);
            statement.executeUpdate();
        }
    }


}