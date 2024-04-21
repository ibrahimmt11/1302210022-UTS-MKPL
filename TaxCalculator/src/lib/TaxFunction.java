package lib;

public class TaxFunction {

	
	/**
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan dikurangi penghasilan tidak kena pajak.
	 * 
	 * Pegawai yang belum menikah dan tidak punya anak tidak kena pajak hingga Rp 54.000.000 penghasilannya. 
	 * Jika sudah menikah, tambahan tidak kena pajaknya Rp 4.500.000. 
	 * Dan jika sudah punya anak, tambahan tidak kena pajaknya Rp 4.500.000 per anak hingga anak ketiga. 
	 */
	
	
	 private static int calculateGrossAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = 54000000;
        if (isMarried) {
            nonTaxableIncome += 4500000;
        }
        if (numberOfChildren > 0) {
            nonTaxableIncome += (numberOfChildren <= 3 ? numberOfChildren : 3) * 4500000;
        }
        return nonTaxableIncome;
    }

    private static int calculateTaxableIncome(int grossAnnualIncome, int deductible, int nonTaxableIncome) {
        return grossAnnualIncome - deductible - nonTaxableIncome;
    }

    private static int calculateTax(int taxableIncome) {
        return (int) Math.round(0.05 * taxableIncome);
    }

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int grossAnnualIncome = calculateGrossAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(grossAnnualIncome, deductible, nonTaxableIncome);
        int tax = calculateTax(taxableIncome);
        return tax < 0 ? 0 : tax;
    }
}
