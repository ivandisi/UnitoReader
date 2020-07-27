package it.id.unitoreader

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception
import java.util.logging.Logger


class UnitoReaderProvider {

    constructor() {

    }

    fun getDataAsync(result: UnitoReaderResult){
        GlobalScope.launch {getDataAsyncSuspended(result)}
    }

    private suspend fun getDataAsyncSuspended(result: UnitoReaderResult){
        return withContext(Dispatchers.IO) {
            val data = getData()
            withContext(Dispatchers.Main) {
                result.onResult(data)
            }
        }
    }

    fun getData() : UnitoData {
        val doc: Document = Jsoup.connect("http://www.meteo.dfg.unito.it/principali").get()
        val data = UnitoData()

        try {
            val content =
                doc.body().allElements[0].child(0).child(0).getElementsByClass("content")[0]
            val table = content.getElementsByClass("divTable ridotta")[0].child(0)

            val temperature = table.child(1)
            val humidity = table.child(2)
            val wind = table.child(3)
            val pressure = table.child(4)


            data.temperature = temperature.child(1).text()
            data.temperatureMin =
                temperature.child(2).child(0).child(0).child(1).child(0).text().replace("[1]", "")
            data.temperatureMax =
                temperature.child(2).child(0).child(0).child(1).child(1).text().replace("[1]", "")

            data.humidity = humidity.child(1).text()
            data.humidityMin =
                humidity.child(2).child(0).child(0).child(1).child(0).text().replace("[1]", "")
            data.humidityMax =
                humidity.child(2).child(0).child(0).child(1).child(1).text().replace("[1]", "")

            data.wind = wind.child(1).text()

            data.pressure = pressure.child(1).text()
            data.pressureMin =
                pressure.child(2).child(0).child(0).child(1).child(0).text().replace("[1]", "")
            data.pressureMax =
                pressure.child(2).child(0).child(0).child(1).child(1).text().replace("[1]", "")
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.getLogger(UnitoReaderProvider::class.java.name).warning("Body is changed..")
        }

        return data;
    }

}