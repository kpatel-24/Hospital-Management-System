package ca.algomau.groupkh.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.algomau.groupkh.beans.Patients;

@Repository
public class PatientsDatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertPatient(Patients patient) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO patient (patientName, diagnosis, doctorId, admissionDate, dischargeDate) VALUES (:patientName, :diagnosis, :doctorId, :admissionDate, :dischargeDate)";
        namedParameters.addValue("patientName", patient.getPatientName());
        namedParameters.addValue("diagnosis", patient.getDiagnosis());
        namedParameters.addValue("doctorId", patient.getDoctorId());
        namedParameters.addValue("admissionDate", patient.getAdmissionDate());
        namedParameters.addValue("dischargeDate", patient.getDischargeDate());
        jdbc.update(query, namedParameters);
    }

    public List<Patients> getPatientList() {
        String query = "SELECT * FROM patient";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Patients.class));
    }

    public List<Patients> searchPatients(Patients searchCriteria) {
        String query = "SELECT * FROM patient WHERE patientName = :patientName OR diagnosis = :diagnosis";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("patientName", searchCriteria.getPatientName());
        namedParameters.addValue("diagnosis", searchCriteria.getDiagnosis());
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Patients.class));
    }
}
