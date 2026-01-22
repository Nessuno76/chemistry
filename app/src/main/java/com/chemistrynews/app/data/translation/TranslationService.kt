package com.chemistrynews.app.data.translation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.net.URL

object TranslationService {

    suspend fun translateToItalian(text: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val encodedText = URLEncoder.encode(text, "UTF-8")
                val url = "https://api.mymemory.translated.net/get?q=$encodedText&langpair=en|it"

                val connection = URL(url).openConnection()
                connection.setRequestProperty("User-Agent", "Mozilla/5.0")

                val response = connection.getInputStream().bufferedReader().readText()

                // Parse JSON manualmente (semplice)
                val translatedText = response
                    .substringAfter("\"translatedText\":\"")
                    .substringBefore("\"")
                    .replace("\\n", "\n")

                translatedText
            } catch (e: Exception) {
                "Errore traduzione: ${e.message}"
            }
        }
    }
}
