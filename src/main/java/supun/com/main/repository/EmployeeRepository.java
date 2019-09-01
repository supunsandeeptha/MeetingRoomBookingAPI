	package supun.com.main.repository;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	import supun.com.main.model.Employee;

	@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	}
