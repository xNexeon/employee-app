import java.math.BigDecimal
import java.math.RoundingMode

fun main(args: Array<String>) {
    var input: Int

    val firstName: String = "Joe"
    val lastName: String = "Soap"
    val gender: String = "Male"
    val employeeID: Int = 6143
    val employeeSalary: Double = 67543.21
    val employeePAYE: Double = 38.5
    val employeePRSI: Double = 5.2
    val annualBonus: Double = 1450.50
    val cycleDiscount: Double = 54.33

    do {
        input = menu(firstName, lastName)
        when (input) {
            1 -> println("Monthly Salary: ${String.format("%.2f", getMonthlySalary(employeeSalary))}")
            2 -> println("Monthly PRSI: ${String.format("%.2f", getMonthlyPRSI(employeeSalary, employeePRSI))}")
            3 -> println("Monthly PAYE: ${String.format("%.2f", getMonthlyPAYE(employeeSalary, employeePAYE))}")
            4 -> println("Monthly Gross Pay: ${String.format("%.2f", getGrossMonthlyPay(employeeSalary, annualBonus))}")
            5 -> println("Monthly Total Deductions: ${String.format("%.2f", getTotalMonthlyDeductions(employeeSalary, employeePAYE, employeePRSI, cycleDiscount))}")
            6 -> println("Monthly Net Pay: ${String.format("%.2f", getNetMonthlyPay(employeeSalary, employeePAYE, employeePRSI, cycleDiscount, annualBonus))}")
            7 -> println(getPayslip(firstName, lastName, employeeID, employeeSalary, employeePAYE, employeePRSI, annualBonus, cycleDiscount))
            0 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun menu(firstName: String, lastName: String): Int {
    print("""
         Employee Menu for $firstName $lastName
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          0. Exit
         Enter Option : """)
    return readln().toInt()
}

fun getMonthlySalary(employeeSalary: Double): Double {
    return BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}

fun getMonthlyPRSI(employeeSalary: Double, employeePRSI: Double): Double {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    return BigDecimal(monthlySalary * employeePRSI / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
}

fun getMonthlyPAYE(employeeSalary: Double, employeePAYE: Double): Double {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    return BigDecimal(monthlySalary * employeePAYE / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
}

fun getGrossMonthlyPay(employeeSalary: Double, annualBonus: Double): Double {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val monthlyBonus = BigDecimal(annualBonus / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    return BigDecimal(monthlySalary + monthlyBonus).setScale(4, RoundingMode.HALF_EVEN).toDouble()
}

fun getTotalMonthlyDeductions(employeeSalary: Double, employeePAYE: Double, employeePRSI: Double, cycleDiscount: Double): Double {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val payTAX = BigDecimal(monthlySalary * employeePAYE / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val payPRSI = BigDecimal(monthlySalary * employeePRSI / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    return BigDecimal(payTAX + payPRSI - cycleDiscount).setScale(4, RoundingMode.HALF_EVEN).toDouble()
}

fun getNetMonthlyPay(employeeSalary: Double, employeePAYE: Double, employeePRSI: Double, cycleDiscount: Double, annualBonus: Double): Double {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val monthlyBonus = BigDecimal(annualBonus / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val payTAX = BigDecimal(monthlySalary * employeePAYE / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val payPRSI = BigDecimal(monthlySalary * employeePRSI / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val grossPay = BigDecimal(monthlySalary + monthlyBonus).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val deductionsTotal = BigDecimal(payTAX + payPRSI - cycleDiscount).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    return BigDecimal(grossPay - deductionsTotal).setScale(4, RoundingMode.HALF_EVEN).toDouble()
}

fun getPayslip(firstName: String, lastName: String, employeeID: Int, employeeSalary: Double, employeePAYE: Double, employeePRSI: Double, annualBonus: Double, cycleDiscount: Double): String {
    val monthlySalary = BigDecimal(employeeSalary / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val monthlyBonus = BigDecimal(annualBonus / 12.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    val payTAX = BigDecimal(monthlySalary * employeePAYE / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val payPRSI = BigDecimal(monthlySalary * employeePRSI / 100).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val grossPay = BigDecimal(monthlySalary + monthlyBonus).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val deductionsTotal = BigDecimal(payTAX + payPRSI - cycleDiscount).setScale(4, RoundingMode.HALF_EVEN).toDouble()
    val netWorth = BigDecimal(grossPay - deductionsTotal).setScale(4, RoundingMode.HALF_EVEN).toDouble()

    return """
        ⏐-----------------------------------------------------------------⏐
        ⏐                                                                 ⏐
        ⏐----------------------Monthly Payslip----------------------------⏐
        ⏐ ⏐
        ⏐-----------------------------------------------------------------⏐
        ⏐                                                                 ⏐
 ⏐Employee Name: ${firstName.uppercase()} ${lastName.uppercase()}      Employee ID: $employeeID⏐
        ⏐                                                                 ⏐
        ⏐-----------------------------------------------------------------⏐
        ⏐                                                                 ⏐
        ⏐       PAYMENT DETAILS                      DEDUCTION DETAILS    ⏐
        ⏐                                                                 ⏐
        ⏐-----------------------------------------------------------------⏐
             Salary: ${String.format("%.2f", monthlySalary)}                        PAYE:  ${String.format("%.4f", payTAX)}
             Bonus: ${String.format("%.2f", monthlyBonus)}                          PRSI:  ${String.format("%.4f", payPRSI)}
                                                                         
                                                                       
        ⏐-----------------------------------------------------------------⏐
        Gross: ${String.format("%.4f", grossPay)}                  Total Deductions:  ${String.format("%.4f", deductionsTotal)}
                                                                        
        ⏐-----------------------------------------------------------------⏐
                             Net Worth: ${String.format("%.4f", netWorth)}                     
                                                                          
        ⏐-----------------------------------------------------------------⏐
    """
}


