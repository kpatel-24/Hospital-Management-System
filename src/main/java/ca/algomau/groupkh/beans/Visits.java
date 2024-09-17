package ca.algomau.groupkh.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Visits {
    private Long id;
    public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Visits [id=" + id + ", doctorId=" + doctorId + ", patientId=" + patientId + ", dateOfVisit="
				+ dateOfVisit + ", notes=" + notes + "]";
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	private Long doctorId;
    private Long patientId;
    private String dateOfVisit;
    private String notes;
}