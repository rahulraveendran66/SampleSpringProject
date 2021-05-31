package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.PcDetails;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.PcDetailsRepository;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	@Autowired 
	private PcDetailsRepository pcdetailsRepository;
	
	
	//get employee
	@GetMapping("employees")
	public List<Employee> getAllEmployee(){
		return this.employeeRepository.findAll();
	}
	
	//get employee by id
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	
	//save employee
	
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return this.employeeRepository.save(employee);
		
	}
	
	
	//update employee -here employees table will be updated
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	
	//delete employee
	@DeleteMapping("employees/{id}")
	public Map<String , Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		this.employeeRepository.delete(employee);
		Map<String , Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	
	//get Pcdetails
	@GetMapping("pcDetails")
	public List<PcDetails> getAllPcDetails() {
		return this.pcdetailsRepository.findAll();
	}
	
	//get pcdetails by id
	
	@GetMapping("/pcDetails/{id}")
	public ResponseEntity<PcDetails> getPcDetailsById(@PathVariable(value = "id") Long pcdetailsId)
			throws ResourceNotFoundException {
		PcDetails pcdetails = pcdetailsRepository.findById(pcdetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		return ResponseEntity.ok().body(pcdetails);
	}
	
	//save pcdetails
	@PostMapping("pcDetails")
	public PcDetails createPcDetails(@RequestBody PcDetails pcdetails) {
		return this.pcdetailsRepository.save(pcdetails);
		
	}
	
	//update pcdetails
	@PutMapping("pcDetails/{id}")
	public ResponseEntity<PcDetails> updatePcDetails(@PathVariable(value = "id") Long pcdetailsId,
			@RequestBody PcDetails pcDetailsinformations) throws ResourceNotFoundException{
		
		PcDetails pcdetails = pcdetailsRepository.findById(pcdetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		
		pcdetails.setPcName(pcDetailsinformations.getPcName());
		pcdetails.setPcUser(pcDetailsinformations.getPcUser());
		
		
		
		return ResponseEntity.ok(this.pcdetailsRepository.save(pcdetails));
	}
	
	//delete pcdetails
	@DeleteMapping("pcDetails/{id}")
	public Map<String , Boolean> deletePcDetails(@PathVariable(value = "id") Long pcdetailsId) throws ResourceNotFoundException{
		PcDetails pcdetails = pcdetailsRepository.findById(pcdetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		
		this.pcdetailsRepository.delete(pcdetails);
		Map<String , Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	//assigning user
	@PutMapping("pcDetails/assign/{eid}/{pid}")
	public Map<String , Boolean> assignPcDetails(@PathVariable(value="eid") Long employeeId , @PathVariable(value="pid") Long pcdetailsId) throws ResourceNotFoundException{
		PcDetails pcdetails = pcdetailsRepository.findById(pcdetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		
		pcdetails.setPcUser(employee);
		this.pcdetailsRepository.save(pcdetails);
		Map<String , Boolean> response = new HashMap<>();
		response.put("connected", Boolean.TRUE);
		return response;
		
	}
	
	//unassigning user
	@PutMapping("pcDetails/unassign/{pid}")
	public Map<String , Boolean> unassignPcDetails(@PathVariable(value="pid") Long pcdetailsId) throws ResourceNotFoundException{
		PcDetails pcdetails = pcdetailsRepository.findById(pcdetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Pc Details not found for this id :: " + pcdetailsId));
		
		
		pcdetails.setPcUser(null);
		this.pcdetailsRepository.save(pcdetails);
		Map<String , Boolean> response = new HashMap<>();
		response.put("connected", Boolean.TRUE);
		return response;
	}
	
	

}
