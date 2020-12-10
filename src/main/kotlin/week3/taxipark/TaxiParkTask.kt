package taxipark

import kotlin.math.max

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = allDrivers.minus(trips.map { it.driver }.toSet())


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    val passengersMap = this.allPassengers.associateWith { 0 }.toMutableMap()
    trips.forEach { trip ->
        trip.passengers.forEach { passenger ->
            val t: Int = passengersMap[passenger]!!
            passengersMap[passenger] = t + 1
        }
    }
    return passengersMap.filter { it.value >= minTrips }.keys
}


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    val tripsWithThatDriver = trips.filter { trip -> trip.driver == driver }
    val passengersInTheseTrips = tripsWithThatDriver.flatMap { trip -> trip.passengers }
        .groupingBy { it }.eachCount()
        .filter { entry -> entry.value > 1 }
    return passengersInTheseTrips.keys
}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    val allPassengersInTripsMap = trips.flatMap { trip -> trip.passengers.associateWith { trip.discount }.toList() }
    val twoLists = allPassengersInTripsMap.partition { pair -> pair.second != null }
    val passengersInTripWithDiscount = twoLists.first
    val passengersInTripWithoutDiscount = twoLists.second
    val withDiscountMap = passengersInTripWithDiscount.map { pair -> pair.first }.groupingBy { it }.eachCount()
    val withoutDiscountMap = passengersInTripWithoutDiscount.map { pair -> pair.first }.groupingBy { it }.eachCount()
    val resultSet = mutableSetOf<Passenger>()
    for (passenger in withDiscountMap.keys) {
        if (withoutDiscountMap[passenger] != null) {
            if (withDiscountMap[passenger]!! > withoutDiscountMap[passenger]!!) resultSet.add(passenger)
        } else resultSet.add(passenger)
    }
    return resultSet
}


/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    val durations = trips.map { trip -> trip.duration }
    val maxDuration = durations.max() ?: return null
    val maxDurationOrder = maxDuration / 10
    val mapOfPeriods: MutableMap<IntRange, Int> = mutableMapOf()
    for (i in 0..maxDurationOrder) {
        mapOfPeriods[i * 10..i * 10 + 9] = 0
    }
    durations.forEach { duration ->
        mapOfPeriods.keys.forEach { period ->
            if (duration in period) {
                mapOfPeriods[period] = mapOfPeriods[period]!! + 1
            }
        }
    }
    return mapOfPeriods.maxBy { entry -> entry.value }?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isEmpty()) {
        return false
    } else {
        val totalTripsCost = this.trips.map { it.cost }.sum()
        val mapCostByDriverSorted =  trips
            .groupBy { it.driver }
            .mapValues { (_, trips) -> trips.sumByDouble { it.cost }}
            .toList()
            .sortedByDescending { (_, value) -> value}.toMap()

        var currentSum = 0.0
        var numberOfDrivers = 0
        for (value in mapCostByDriverSorted.values){
            numberOfDrivers++
            currentSum += value
            if (currentSum >= (totalTripsCost * 0.8)) break
        }

        return numberOfDrivers <= (allDrivers.size * 0.2)
    }
}