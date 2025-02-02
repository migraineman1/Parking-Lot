package parking

class ParkingSpace(var registration: String = "", var carColor: String = "")

class ParkingLot(val numOfSpaces: Int) {
    val spaces = List<ParkingSpace>(
        this.numOfSpaces,
        init = { ParkingSpace() }
    )

    init {
        spaces[0].registration = "Loyola-1983"
        spaces[0].carColor = "Blue"

    }

    fun findFirstParkingSpace(registration: String): Int {
        for (i in spaces.indices) {
            if (spaces[i].registration == registration) { return i}
        }
        return -1
    }

    fun park(registration: String, carColor: String) {
        val openSpace = findFirstParkingSpace("")
        if (openSpace == -1) println("Lot full.")
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

}

fun main() {
    val parkingLot = ParkingLot(2)

    val input = readln().split(" ")

    if (input[0] == "park") {
        parkingLot.park(input[1], input[2])
    }
    if (input[0] == "leave") parkingLot.leave(input[1].toInt())
}
