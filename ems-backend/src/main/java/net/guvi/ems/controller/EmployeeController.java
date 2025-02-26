package net.guvi.ems.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.guvi.ems.dto.EmployeeDto;
import net.guvi.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="CRUD REST API for employee resource ",
 description = "Crud API for employee Resource")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private EmployeeService employeeService;

    @Operation(summary = "Add Employee REST API",description = "Add Employee REST API")
    @ApiResponse(responseCode = "200",description = "created")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

   @Operation(summary = "Get Employee REST API",description = "Get Employee REST API")
   @ApiResponse(responseCode = "200",description = "success")

   @GetMapping("{id}")
   public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
       EmployeeDto employeeDto =employeeService.getEmployeeById(employeeId);
       return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Get All Employees REST API",description = "Get All Employees REST API")
    @ApiResponse(responseCode = "200",description = "success")

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employees= employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

      @Operation(summary = "Update Employee REST API",description = "Update Employee REST API")
      @ApiResponse(responseCode = "200",description = "updated")

      @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto=employeeService.updateEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Delete Employee TEST API",description = "Delete Employee TEST API")
    @ApiResponse(responseCode = "200",description = "deleted")

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return  ResponseEntity.ok("Employee deleted successfully!");
    }
}
