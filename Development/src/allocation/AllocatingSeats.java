package allocation;

public class AllocatingSeats {
	private String Full_name, emp_id, email, floors, seat_num;

	public AllocatingSeats() {
		super();
	}

	public AllocatingSeats(String Full_name, String emp_id, String email, String floors, String seat_num) {
		super();
		this.Full_name = Full_name;
		this.emp_id = emp_id;
		this.email = email;
		this.floors = floors;
		this.seat_num = seat_num;
	}

	public String getFull_name() {
		return Full_name;
	}

	public void setFull_name(String Full_name) {
		this.Full_name = Full_name;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}

	public String getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(String seat_num) {
		this.seat_num = seat_num;
	}

}
