package com.programming.techie.service.impl;

import com.programming.techie.exception.ResourceNotFoundException;
import com.programming.techie.service.EmployeeService;
import lombok.AllArgsConstructor;
import com.programming.techie.dto.EmployeeDto;
import com.programming.techie.entity.Employee;
import com.programming.techie.mapper.EmployeeMapper;
import com.programming.techie.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("not fund : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee -> EmployeeMapper.mapToEmployeeDto(employee))).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        // 查找要更新的员工
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + employeeId));

        // 更新每个字段，只在新的值不为 null 时进行更新
        if (updatedEmployee.getFirstName() != null) {
            employee.setFirstName(updatedEmployee.getFirstName());
        }
        if (updatedEmployee.getLastName() != null) {
            employee.setLastName(updatedEmployee.getLastName());
        }
        if (updatedEmployee.getEmail() != null) {
            employee.setEmail(updatedEmployee.getEmail());
        }
        if (updatedEmployee.getCardId() != null) {
            employee.setCardId(updatedEmployee.getCardId());
        }

        // 保存更新后的员工对象
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }


    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("not fund : " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}

