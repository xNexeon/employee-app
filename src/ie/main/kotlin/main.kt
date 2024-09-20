val fname = "Joe"
val sname = "Soap"
val gender = "m"
val employeeId = 6143
val grossSal = 67543.21
val paye = 0.385 // assuming 38.5% PAYE rate
val prsi = 0.052 // assuming 5.2% PRSI rate
val annualBonus = 1450.50
val ctwScheme = 54.33

// Calculating monthly amounts
val monthlyGross = grossSal / 12
val monthlyBonus = annualBonus / 12

// Calculating deductions
val payeDeduction = monthlyGross * paye
val prsiDeduction = monthlyGross * prsi
val totalDeductions = payeDeduction + prsiDeduction + ctwScheme

// Calculating net pay
val netPay = (monthlyGross + monthlyBonus) - totalDeductions

fun main() {
    println("Pay Slip Printer")
    printPayslip()
}

fun printPayslip(){
    println("------------------------------------------------------")
    println("                   Monthly Payslip                    ")
    println("------------------------------------------------------")
    println("   Employee Name: $fname $sname ($gender)     Employee ID: $employeeId")
    println("------------------------------------------------------")
    println("    PAYMENT DETAILS              DEDUCTION DETAILS    ")
    println("------------------------------------------------------")
    println("    Salary: %.2f            PAYE: %.2f".format(monthlyGross, payeDeduction))
    println("    Bonus: %.2f              PRSI: %.2f".format(monthlyBonus, prsiDeduction))
    println("                               Cycle To Work: %.2f".format(ctwScheme))
    println("------------------------------------------------------")
    println("    Gross: %.2f           Total Deductions: %.2f".format(monthlyGross + monthlyBonus, totalDeductions))
    println("              NET PAY: %.2f".format(netPay))
    println("------------------------------------------------------")
}
