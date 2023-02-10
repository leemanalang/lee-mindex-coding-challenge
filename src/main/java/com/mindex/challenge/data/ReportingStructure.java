package com.mindex.challenge.data;

public class ReportingStructure {

    private int numberOfReports;
    private Employee employee;
    public ReportingStructure(){
        this.numberOfReports = 0;
        this.employee = null;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
