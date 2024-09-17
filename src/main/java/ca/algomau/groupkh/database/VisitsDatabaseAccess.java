package ca.algomau.groupkh.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.algomau.groupkh.beans.Visits;

@Repository
public class VisitsDatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertVisit(Visits visit) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO visit (doctorId, patientId, dateOfVisit, notes) VALUES (:doctorId, :patientId, :dateOfVisit, :notes)";
        namedParameters.addValue("doctorId", visit.getDoctorId());
        namedParameters.addValue("patientId", visit.getPatientId());
        namedParameters.addValue("dateOfVisit", visit.getDateOfVisit());
        namedParameters.addValue("notes", visit.getNotes());
        jdbc.update(query, namedParameters);
    }

    public List<Visits> getVisitList() {
        String query = "SELECT * FROM visit";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Visits.class));
    }

    public List<Visits> searchVisits(Visits searchCriteria) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder("SELECT * FROM visit WHERE 1=1");

        if (searchCriteria.getDateOfVisit() != null && !searchCriteria.getDateOfVisit().isEmpty()) {
            query.append(" AND dateOfVisit = :dateOfVisit");
            namedParameters.addValue("dateOfVisit", searchCriteria.getDateOfVisit());
        }
        if (searchCriteria.getNotes() != null && !searchCriteria.getNotes().isEmpty()) {
            query.append(" AND notes LIKE :notes");
            namedParameters.addValue("notes", "%" + searchCriteria.getNotes() + "%");
        }
        return jdbc.query(query.toString(), namedParameters, new BeanPropertyRowMapper<>(Visits.class));
    }
}
