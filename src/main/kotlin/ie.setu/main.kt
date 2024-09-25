package ie.setu

import kotlin.math.round

// Employee Class
class Employee(
    var firstName: String,
    var surname: String,
    var gender: Char,
    var employeeID: Int,
    var grossSalary: Double,
    var payePercentage: Double,
    var prsiPercentage: Double,
    var annualBonus: Double,
    var cycleToWorkMonthlyDeduction: Double
) {

    fun getPayslip() = """
    |_______________________________________________________________________                                                                 
    |     Monthly Payslip:             ${getFullName()}, ID: $employeeID                                                                                   
    |_______________________________________________________________________                                                                 
    |     PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})     
    |          Salary: ${getMonthlySalary()}
    |          Bonus:  ${getMonthlyBonus()}
    |_______________________________________________________________________
    |     DEDUCTION DETAILS (total Deductions: ${getTotalMonthlyDeductions()})    
    |          PAYE: ${getMonthlyPAYE()}
    |          PRSI: ${getMonthlyPRSI()}
    |          Cycle To Work: $cycleToWorkMonthlyDeduction
    |_______________________________________________________________________
    |    NET PAY: ${getNetMonthlyPay()}
    |_______________________________________________________________________
""".trimMargin("|")

    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. $firstName $surname"
        'f', 'F' -> "Ms.  $firstName $surname"
        else -> "$firstName $surname"
    }

    fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
    fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
    fun getMonthlyBonus() = roundTwoDecimals(annualBonus / 12)
    fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + getMonthlyBonus())
    fun getTotalMonthlyDeductions() = roundTwoDecimals(getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkMonthlyDeduction)
    fun getNetMonthlyPay() = roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())

    fun roundTwoDecimals(number: Double) = round(number * 100) / 100

    override fun toString(): String {
        return "Employee(firstName='$firstName', surname='$surname', gender=$gender, employeeID=$employeeID, grossSalary=$grossSalary, payePercentage=$payePercentage, prsiPercentage=$prsiPercentage, annualBonus=$annualBonus, cycleToWorkMonthlyDeduction=$cycleToWorkMonthlyDeduction)"
    }
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()
    private var lastId = 0

    private fun getId(): Int {
        return lastId++
    }

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { it.employeeID == id }
    }

    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }
}

var employees = EmployeeAPI()

fun menu(): Int {
    print(
        """ 
         |Employee Menu
         |   1. Add Employee
         |   2. List All Employees
         |   3. Search Employees 
         |   4. Print Payslip for Employee
         |  -1. Exit
         |       
         |Enter Option : """.trimMargin()
    )
    return readln().toInt()
}

fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            -99 -> dummyData() // For testing
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun list() {
    employees.findAll().forEach { println(it) }
}


fun search() {
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    return employees.findOne(readLine()!!.toInt())
}

fun paySlip() {
    val employee = getEmployeeById()
    employee?.let { println(it.getPayslip()) }
}

fun add() {
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus = readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction = readLine()!!.toDouble()

    employees.create(
        Employee(
            firstName, surname, gender, 0, grossSalary, payePercentage,
            prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction
        )
    )
}

fun dummyData() {
    employees.create(Employee("Joe", "Soap", 'm', 0, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", 'f', 0, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", 'f', 0, 75685.41, 40.0, 8.5, 4500.0, 0.0))
}

// Main function to run the application
fun main(args: Array<String>) {
    start()
}