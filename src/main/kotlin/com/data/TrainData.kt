package com.data

data class Train(val id: String, val name: String, val source: String, val destination: String, val depatureTime: String)

class TrainData {

    var trainSources = mutableListOf<Train>()

    fun insert(train: Train){
        trainSources.add(train)
    }

    fun filterByName(name: String): Train {
        return trainSources.filter { it.name == name }.first()
    }

    fun filterBySource(source: String): List<Train> {
        val trains = trainSources.filter { it.source == source }
        if (trains.isEmpty())
            throw Exception("No trains found from $source")
        return trains
    }

    fun filterByDestination(destination: String): List<Train> {
        val trains = trainSources.filter { it.destination == destination }
        if (trains.isEmpty())
            throw Exception("No trains found from $destination")
        return trains
    }

    fun filterByDepartureTime(departureTime: String): List<Train> {
        val trains = trainSources.filter { it.depatureTime == departureTime }
        if (trains.isEmpty())
            throw Exception("No trains found which departs at $departureTime")
        return trains
    }

    fun printDetails(train: Train): String {
        return "TrainName: ${train.name}\nSource: ${train.source}\nDestination: ${train.destination}\nDepatureTime: ${train.depatureTime}"
    }
}


