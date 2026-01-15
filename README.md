# Chemistry News App 🧪📰

Un'applicazione Android avanzata per cercare, catalogare e approfondire notizie sulla chimica, con sistema di filtraggio intelligente basato sugli interessi e funzionalità di approfondimento tramite AI.

## 🌟 Caratteristiche Principali

### 1. **Ricerca Notizie Intelligente**
- Ricerca automatica di notizie sulla chimica da fonti internazionali
- Integrazione con News API per contenuti aggiornati
- Rilevamento automatico delle categorie basato sul contenuto

### 2. **Sistema di Catalogazione**
- Database locale Room per salvare articoli preferiti
- Organizzazione automatica per categoria
- Accesso offline agli articoli salvati

### 3. **Menu Selezione Interessi**
Seleziona le tue aree di interesse tra:
- 🧬 **Chimica Organica** - Sintesi, meccanismi di reazione
- ⚛️ **Chimica Inorganica** - Metalli, coordinazione
- 🔬 **Chimica Analitica** - Spettroscopia, cromatografia
- ⚡ **Chimica Fisica** - Termodinamica, chimica quantistica
- 🧫 **Biochimica** - Proteine, enzimi, DNA
- 🔧 **Scienza dei Materiali** - Polimeri, nanotecnologia
- 💊 **Farmaceutica** - Farmaci, chimica medicinale
- 🌱 **Chimica Ambientale** - Chimica verde, sostenibilità

### 4. **Filtraggio Avanzato**
- Filtra articoli per categorie selezionate
- Ricerca personalizzata basata sugli interessi
- Keyword matching intelligente per ogni categoria

### 5. **Approfondimento con AI**
- Generazione automatica di riassunti per ogni articolo
- Evidenziazione dei punti chiave
- Implicazioni pratiche e sviluppi futuri

## 📱 Architettura dell'App

### Tecnologie Utilizzate
- **Kotlin** - Linguaggio di programmazione moderno
- **Jetpack Compose** - UI dichiarativa moderna
- **Room Database** - Persistenza dati locale
- **Retrofit** - Chiamate API REST
- **Coroutines & Flow** - Programmazione asincrona
- **MVVM Architecture** - Separazione logica/UI
- **Material Design 3** - Design system moderno
- **Coil** - Caricamento immagini ottimizzato

### Struttura del Progetto

```
app/
├── data/
│   ├── local/          # Database Room
│   │   ├── AppDatabase.kt
│   │   ├── ArticleDao.kt
│   │   └── Converters.kt
│   ├── model/          # Modelli dati
│   │   ├── Article.kt
│   │   └── ChemistryCategory.kt
│   ├── remote/         # API Services
│   │   ├── NewsApiService.kt
│   │   └── RetrofitClient.kt
│   ├── repository/     # Repository pattern
│   │   └── NewsRepository.kt
│   └── preferences/    # User preferences
│       └── UserPreferences.kt
├── ui/
│   ├── components/     # Componenti UI riutilizzabili
│   │   └── ArticleCard.kt
│   ├── screens/        # Schermate principali
│   │   ├── SearchScreen.kt
│   │   ├── InterestsScreen.kt
│   │   ├── SavedArticlesScreen.kt
│   │   ├── ArticleDetailScreen.kt
│   │   └── MainScreen.kt
│   ├── viewmodel/      # ViewModels
│   │   ├── NewsViewModel.kt
│   │   ├── SavedArticlesViewModel.kt
│   │   └── ViewModelFactory.kt
│   ├── navigation/     # Navigazione
│   │   └── NavGraph.kt
│   └── theme/          # Tema e stili
│       ├── Theme.kt
│       └── Type.kt
└── MainActivity.kt
```

## 🚀 Setup e Installazione

### Prerequisiti
- Android Studio Hedgehog (2023.1.1) o superiore
- JDK 17 o superiore
- Android SDK API 24+ (Android 7.0)
- News API Key (gratuita)

### Passaggi di Installazione

1. **Clone del Repository**
```bash
git clone <repository-url>
cd prova-lvgl
```

2. **Configurazione News API**

   a. Registrati gratuitamente su [NewsAPI.org](https://newsapi.org/)

   b. Ottieni la tua API key

   c. Apri il file `app/src/main/java/com/chemistrynews/app/data/remote/NewsApiService.kt`

   d. Sostituisci `YOUR_API_KEY_HERE` con la tua API key:

   ```kotlin
   companion object {
       const val BASE_URL = "https://newsapi.org/"
       const val API_KEY = "la_tua_api_key_qui"  // <-- Inserisci qui
   }
   ```

3. **Sync Gradle**
   - Apri il progetto in Android Studio
   - Attendi il completamento del sync di Gradle
   - Risolvi eventuali dipendenze mancanti

4. **Build e Run**
   - Connetti un dispositivo Android o avvia un emulatore
   - Clicca su Run (▶️) o premi `Shift + F10`
   - L'app verrà installata e avviata sul dispositivo

## 📖 Come Usare l'App

### 1. **Schermata Cerca**
- Tap su "Cerca Notizie sulla Chimica" per ottenere le ultime notizie
- Scorri gli articoli disponibili
- Tap su un articolo per vedere i dettagli
- Tap sull'icona bookmark per salvare un articolo

### 2. **Schermata Interessi**
- Seleziona le categorie di tuo interesse
- Tap su "Cerca Articoli" per trovare notizie pertinenti
- Gli articoli verranno filtrati automaticamente

### 3. **Schermata Salvati**
- Visualizza tutti gli articoli salvati
- Tap per leggere i dettagli
- Tap sul bookmark per rimuovere dalla lista

### 4. **Dettaglio Articolo**
- Leggi il contenuto completo
- Visualizza immagini e categorie
- Tap "Genera Riassunto AI" per ottenere un'analisi approfondita
- Tap sull'icona browser per aprire l'articolo originale

## 🔧 Configurazione Avanzata

### Personalizzazione delle Categorie

Modifica `ChemistryCategory.kt` per aggiungere nuove categorie:

```kotlin
enum class ChemistryCategory(val displayName: String, val keywords: List<String>) {
    // Aggiungi nuove categorie qui
    NUOVA_CATEGORIA(
        "Nome Visualizzato",
        listOf("keyword1", "keyword2", "keyword3")
    )
}
```

### Integrazione AI Personalizzata

Per integrare un servizio AI reale (come Claude API o OpenAI):

1. Aggiungi le dipendenze necessarie in `app/build.gradle.kts`
2. Modifica `NewsViewModel.generateAISummary()`:

```kotlin
private suspend fun generateRealAISummary(content: String): String {
    // Implementa qui la chiamata alla tua AI API
    val response = yourAIService.generateSummary(content)
    return response.summary
}
```

### Modifica dello Stile

Personalizza i colori in `ui/theme/Theme.kt`:

```kotlin
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFTUOCOLORE),
    // ... altri colori
)
```

## 🐛 Troubleshooting

### Errore: "API Key non valida"
- Verifica di aver inserito la API key corretta in `NewsApiService.kt`
- Controlla che la key sia attiva su newsapi.org

### Errore: "Nessuna connessione internet"
- Verifica che il dispositivo sia connesso a internet
- Controlla i permessi in `AndroidManifest.xml`

### Build fallito
```bash
# Pulisci e rebuilda il progetto
./gradlew clean
./gradlew build
```

### Emulatore non avvia
- Verifica che AVD sia configurato correttamente
- Prova a usare un dispositivo fisico
- Riavvia Android Studio

## 📝 Note Importanti

### Limitazioni News API (Piano Gratuito)
- 100 richieste al giorno
- 50 articoli per richiesta
- Notizie degli ultimi 30 giorni
- Alcune fonti potrebbero essere limitate

Per uso in produzione, considera l'upgrade a un piano a pagamento.

### Privacy e Dati
- L'app salva gli articoli localmente sul dispositivo
- Nessun dato viene condiviso con server terzi
- Le preferenze sono salvate in DataStore locale

## 🚀 Sviluppi Futuri

- [ ] Integrazione con API AI reale (Claude, OpenAI)
- [ ] Notifiche push per nuovi articoli
- [ ] Modalità dark completa
- [ ] Export articoli in PDF
- [ ] Condivisione articoli su social media
- [ ] Widget home screen
- [ ] Supporto multilingua
- [ ] Ricerca avanzata con filtri temporali
- [ ] Grafici e statistiche di lettura

## 🤝 Contribuire

Contributi, issues e feature requests sono benvenuti!

## 📄 Licenza

Questo progetto è open source e disponibile sotto la [MIT License](LICENSE).

## 👨‍💻 Autore

Sviluppato con ❤️ per gli appassionati di chimica

---

**Buona ricerca! 🧪✨**
