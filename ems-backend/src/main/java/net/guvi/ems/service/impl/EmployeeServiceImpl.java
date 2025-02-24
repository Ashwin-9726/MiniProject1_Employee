package net.guvi.ems.service.impl;

import lombok.AllArgsConstructor;
import net.guvi.ems.dto.EmployeeDto;
import net.guvi.ems.entity.Employee;
import net.guvi.ems.exception.ResourceNotFoundException;
import net.guvi.ems.mapper.EmployeeMapper;
import net.guvi.ems.repository.EmployeeRepository;
import net.guvi.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee =employeeRepository.findById(employeeId)
               .orElseThrow(()->
                       new ResourceNotFoundException("Employee Does not exists with the given id"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee) ;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exists with the given id:"+employeeId)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exists with the given id:"+employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
