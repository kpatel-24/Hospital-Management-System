package ca.algomau.groupkh.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.algomau.groupkh.beans.Doctor;

@Repository
public class DoctorDatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertDoctor(Doctor doctor) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO doctor (doctorName, specialization, department) VALUES (:doctorName, :specialization, :department)";
        namedParameters.addValue("doctorName", doctor.getDoctorName());
        namedParameters.addValue("specialization", doctor.getSpecialization());
        namedParameters.addValue("department", doctor.getDepartment());
        jdbc.update(query, namedParameters);
    }

    public List<Doctor> getDoctorList() {
        String query = "SELECT * FROM doctor";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Doctor.class));
    }

    public List<Doctor> searchDoctors(Doctor searchCriteria) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder("SELECT * FROM doctor WHERE 1=1");

        if (searchCriteria.getDoctorName() != null && !searchCriteria.getDoctorName().isEmpty()) {
            query.append(" AND doctorName = :doctorName");
            namedParameters.addValue("doctorName", searchCriteria.getDoctorName());
        }
        if (searchCriteria.getSpecialization() != null && !searchCriteria.getSpecialization().isEmpty()) {
            query.append(" AND specialization = :specialization");
            namedParameters.addValue("specialization", searchCriteria.getSpecialization());
        }
        if (searchCriteria.getDepartment() != null && !searchCriteria.getDepartment().isEmpty()) {
            query.append(" AND department = :department");
            namedParameters.addValue("department", searchCriteria.getDepartment());
        }
        return jdbc.query(query.toString(), namedParameters, new BeanPropertyRowMapper<>(Doctor.class));
    }
}
