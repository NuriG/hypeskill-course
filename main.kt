package cinema

fun main() {
    println("Enter the number of rows:")
    val a = readln().toInt()
    println("Enter the number of seats in each row:")
    val b = readln().toInt()
    val listCinema: MutableList<MutableList<Char>> = MutableList(a) { MutableList(b) { 'S' } }
    while (true) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val button = readln()
        when (button) {
            "1" -> showTheSeats(a, b, listCinema)
            "2" -> buyTicket(a, b, listCinema)
            "3" -> statistics(a, b, listCinema)
            "0" -> break

        }
    }
}
fun showTheSeats(a: Int, b: Int, listCinema: MutableList<MutableList<Char>>) {
    println("Cinema:")
    for (i in 0..b) {
        if (i == 0) {
            print("  ")
        } else if (i == b) {
            print("$i\n")
        } else {
            print("$i ")
        }
    }
    for (i in 0 until a) {
        print("${i+1} ")
        for (j in 0 until b) {
            if (j != b-1) {
                print("${listCinema[i][j]} ")
            } else {
                print(listCinema[i][j])
            }
        }
        println()
    }
}
fun buyTicket(a: Int, b: Int, listCinema: MutableList<MutableList<Char>>) {
    println("Enter a row number:")
    val c = readln().toInt()
    println("Enter a seat number in that row:")
    val d = readln().toInt()
    var s: Int = 8
    if (a * b <= 60) {
        s = 10
    } else {
        if (c <= a / 2) {
            s = 10
        }
    }
    if (c <= a && d <= b) {
        if (listCinema[c-1][d-1] != 'B') {
            println("Ticket price: $$s")
            listCinema[c-1][d-1] = 'B'
        } else {
            println("That ticket has already been purchased!")
            println()
            buyTicket(a, b, listCinema)
        }
    } else {
        println("Wrong input!")
        println()
        buyTicket(a, b, listCinema)
    }
}
fun statistics(a: Int, b: Int, listCinema: MutableList<MutableList<Char>>) {
    var sum = 0
    var sum2 = 0
    var s = 8
    var counter = 0
    for (i in 0 until a) {
        for (j in 0 until b) {
            if (listCinema[i][j] == 'B') {
                s = 8
                counter++
                if (a * b <= 60) {
                    s = 10
                } else {
                    if (i < a / 2) {
                        s = 10
                    }
                }
                sum += s
            }
        }
    }
    val x = counter * 1.0 / (a * b) * 100
    val formatPercentage = "%.2f".format(x)
    if (a * b <= 60) {
        sum2 = 10 * a * b
    } else {
        sum2 = ((a / 2) * 10 + (a - a / 2) * 8) * b
    }
    println("Number of purchased tickets: $counter")
    println("Percentage: ${formatPercentage}%")
    println("Current income: \$$sum")
    println("Total income: \$$sum2")
}
