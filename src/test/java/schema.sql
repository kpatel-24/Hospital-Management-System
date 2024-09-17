CREATE TABLE doctor (
    doctorId BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctorName VARCHAR(100),
    specialization VARCHAR(100),
    department VARCHAR(100)
);

CREATE TABLE patient (
    patientId BIGINT AUTO_INCREMENT PRIMARY KEY,
    patientName VARCHAR(100),
    diagnosis VARCHAR(100),
    doctorId BIGINT,
    admissionDate VARCHAR(100),
    dischargeDate VARCHAR(100),
    FOREIGN KEY (doctorId) REFERENCES doctor(doctorId)
);

CREATE TABLE visit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctorId BIGINT,
    patientId BIGINT,
    dateOfVisit VARCHAR(100),
    notes VARCHAR(255),
    FOREIGN KEY (doctorId) REFERENCES doctor(doctorId),
    FOREIGN KEY (patientId) REFERENCES patient(patientId)
);
