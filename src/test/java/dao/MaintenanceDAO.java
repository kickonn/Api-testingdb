package dao;


import appmanager.HelperBase;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static appmanager.ExtentCucumberFormatter.testStepFailed;

public class MaintenanceDAO {


    public JdbcTemplate jdbcTemplate;

    public MaintenanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int getTableRecordCount(String tableName) {
        String sql = " select * from " + tableName;
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
        return data.size();
    }

    public List<Map<String, Object>> getAllRecordsFromTable(String tableName) {
        String sql = " select * from " + tableName;
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
        return data;
    }



}

