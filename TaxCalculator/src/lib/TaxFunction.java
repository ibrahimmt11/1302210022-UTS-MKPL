package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
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
