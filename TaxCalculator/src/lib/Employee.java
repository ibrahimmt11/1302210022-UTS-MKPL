package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private JoinDate joinDate;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * 
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	private int calculateForeignerSalaryIncrease(int baseSalary) {
        return (int) (baseSalary * 1.5);
    }

    public void setMonthlySalary(int grade) {
        int baseSalary;

        if (grade == 1) {
            baseSalary = 3000000;
        } else if (grade == 2) {
            baseSalary = 5000000;
        } else if (grade == 3) {
            baseSalary = 7000000;
        } else {
            throw new IllegalArgumentException("Invalid grade");
        }

        monthlySalary = isForeigner ? calculateForeignerSalaryIncrease(baseSalary) : baseSalary;
    }
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {

		LocalDate date = LocalDate.now();
	
		int monthWorkingInYear;
		if (date.getYear() == joinDate.getYear()) {
			monthWorkingInYear = date.getMonthValue() - joinDate.getMonth().getValue();
		} else {
			monthWorkingInYear = 12;
		}
	
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
	
}
