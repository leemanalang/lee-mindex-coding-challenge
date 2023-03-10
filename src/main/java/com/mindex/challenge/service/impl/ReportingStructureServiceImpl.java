package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure generateReportingStructure(String id){
        LOG.debug("Creating report for employee with id [{}]", id);

        Employee parent = employeeRepository.findByEmployeeId(id);

        ReportingStructure report = new ReportingStructure();

        addChildren(parent, report);
        report.setEmployee(parent);

        return report;
    }

    private void addChildren(Employee parent, ReportingStructure report) {
        if(parent == null || parent.getDirectReports() == null){
            return;
        }
        List<Employee> children = new ArrayList<>();

        for(Employee directEmployee : parent.getDirectReports()){
            Employee childEmployee = employeeRepository.findByEmployeeId(directEmployee.getEmployeeId());
            children.add(childEmployee);
            parent.setDirectReports(children);
            report.setNumberOfReports(report.getNumberOfReports()+1);

            addChildren(childEmployee, report);
        }
    }
}
