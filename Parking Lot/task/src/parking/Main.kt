package parking

class ParkingSpace(var registration: String = "", var carColor: String = "") {
    operator fun component1() = registration
    operator fun component2() = carColor
}

class ParkingLot(val numOfSpaces: Int) {
    val spaces = List<ParkingSpace>(
        this.numOfSpaces,
        init = { ParkingSpace() }
    )

    fun findFirstParkingSpace(registration: String): Int {
        for (i in spaces.indices) {
            if (spaces[i].registration == registration) {
                return i
            }
        }
        return -1
    }

    fun park(registration: String, carColor: String) {
        val openSpace = findFirstParkingSpace("")
        if (openSpace == -1) println("Sorry, the parking lot is full.")
        else {
            spaces[openSpace].registration = registration
            spaces[openSpace].carColor = carColor
            println("$carColor car parked in spot ${openSpace + 1}.")
        }
    }

    fun leave(spaceNumber: Int) {
        if (spaces[spaceNumber - 1].registration == "") println("There is no car in spot $spaceNumber.")
        else {
            spaces[spaceNumber - 1].carColor = ""
            spaces[spaceNumber - 1].registration = ""
            println("Spot $spaceNumber is free.")
        }
    }

    fun status() {
        if (spaces.all { it.registration == "" }) {
            println("Parking lot is empty.")
        } else {
            for (i in spaces.indices) {
                if (spaces[i].registration != "") println("${i + 1} ${spaces[i].registration} ${spaces[i].carColor}")
            }
        }
    }

    fun regByColor(color: String) {
        val validSpaces = spaces.filter { it.carColor.lowercase() == color.lowercase() }
        if (validSpaces.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            val carList = mutableListOf<String>()
            for ((registration, carColor) in validSpaces) {
                if (carColor.lowercase() == color.lowercase()) {
                    carList.add(registration)
                }
            }
            println(carList.joinToString(", "))
        }
    }

    fun spotByColor(color: String) {
        val validSpaces = spaces.filter { it.carColor.lowercase() == color.lowercase() }
        if (validSpaces.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            val spacesList = mutableListOf<Int>()
            var spaceNumber = 1
            for ((_ , carColor) in spaces) {
                if (carColor.lowercase() == color.lowercase()) {
                    spacesList.add(spaceNumber)
                }
                spaceNumber++
            }
            println(spacesList.joinToString(", "))
        }
    }

    fun spotByRegistration(registration: String) {
        val validSpaces = spaces.filter { it.registration == registration }
        if (validSpaces.isEmpty()) {
            println("No cars with registration number $registration were found.")
        } else {
            var spaceNumber = 1
            for ((reg, _) in spaces) {
                if (reg == registration) {
                    println(spaceNumber)
                return
                }
                spaceNumber++
            }
        }
    }

}

fun main() {
    var lot: ParkingLot? = null

    var input = readln().split(" ")

    while (input[0] != "exit") {
        when (input[0]) {
            "create" -> {
                val (_, noOfSpaces) = input
                lot = ParkingLot(noOfSpaces.toInt())
                println("Created a parking lot with ${input[1]} spots.")
            }

            "park" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else {
                    val (_, registration, color) = input
                    lot.park(registration, color)
                }
            }

            "leave" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else {
                    val (_, spaceNumber) = input
                    lot.leave(spaceNumber.toInt())
                }
            }

            "status" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else lot.status()
            }

            "reg_by_color" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else {
                    val (_, color) = input
                    lot.regByColor(color)
                }
            }

            "spot_by_color" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else {
                    val (_, color) = input
                    lot.spotByColor(color)
                }
            }

            "spot_by_reg" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else {
                    val (_, registration) = input
                    lot.spotByRegistration(registration)
                }
            }


        }
        input = readln().split(" ")
    }
}
