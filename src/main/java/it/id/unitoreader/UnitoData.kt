package it.id.unitoreader

data class UnitoData(
    var temperature: String = "",
    var temperatureMin: String = "",
    var temperatureMax: String = "",

    var humidity: String = "",
    var humidityMin: String = "",
    var humidityMax: String = "",

    var wind: String = "",

    var pressure: String = "",
    var pressureMin: String = "",
    var pressureMax: String = ""
){
    override fun toString():String = StringBuilder().append("Data from Unito").append("\n")
        .append(temperature).append(" ").append(temperatureMin).append(" ").append(temperatureMax).append("\n")
        .append(humidity).append(" ").append(humidityMin).append(" ").append(humidityMax).append("\n")
        .append(wind).append(" ").append("\n")
        .append(pressure).append(" ").append(pressureMin).append(" ").append(pressureMax).append("\n").toString()
}