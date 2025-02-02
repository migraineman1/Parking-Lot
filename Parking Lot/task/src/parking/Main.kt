package parking

class ParkingSpace(var registration: String = "", var carColor: String = "")

class ParkingLot(val numOfSpaces: Int) {
    val spaces = List<ParkingSpace>(
        this.numOfSpaces,
        init = { ParkingSpace() }
    )

    fun findFirstParkingSpace(registration: String): Int {
        for (i in spaces.indices) {
            if (spaces[i].registration == registration) { return i}
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
        if (spaces[spaceNumber - 1].registration == "" ) println("There is no car in spot $spaceNumber.")
        else {
            spaces[spaceNumber - 1].carColor = ""
            spaces[spaceNumber - 1].registration = ""
            println("Spot $spaceNumber is free.")
        }
    }

    fun status() {
        if (spaces.all { it.registration == "" }) { println("Parking lot is empty.") }
        else {
            for (i in spaces.indices) {
                if (spaces[i].registration != "") println("${i + 1} ${spaces[i].registration} ${spaces[i].carColor}")
            }
        }
    }

}

fun main() {
    var lot:ParkingLot? = null

    var input = readln().split(" ")

    while (input[0] != "exit") {
        when (input[0]) {
            "create" -> {
                lot = ParkingLot(input[1].toInt())
                println("Created a parking lot with ${input[1]} spots.")
            }
            "park" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else lot.park(input[1], input[2])
            }
            "leave" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else lot.leave(input[1].toInt())
            }
            "status" -> {
                if (lot == null) println("Sorry, a parking lot has not been created.")
                else lot.status()
            }
        }
        input = readln().split(" ")
    }
}
