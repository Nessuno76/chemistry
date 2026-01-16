@echo off
echo ========================================
echo    COMPILAZIONE APP CHEMISTRY NEWS
echo ========================================
echo.
echo Questo script compilera' l'APK automaticamente
echo.
pause

echo.
echo [1/3] Controllo Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERRORE: Java non trovato!
    echo Installa JDK da: https://adoptium.net/
    pause
    exit /b 1
)
echo Java OK!

echo.
echo [2/3] Compilazione APK in corso...
echo Questo puo' richiedere 3-5 minuti la prima volta.
echo.
call gradlew.bat assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo ========================================
    echo ERRORE DURANTE LA COMPILAZIONE!
    echo ========================================
    echo.
    echo Possibili cause:
    echo 1. API Key non configurata in NewsApiService.kt
    echo 2. Android SDK non trovato
    echo 3. Problemi di connessione internet
    echo.
    echo Apri il progetto in Android Studio per vedere l'errore completo.
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo    COMPILAZIONE COMPLETATA!
echo ========================================
echo.
echo APK creato in:
echo app\build\outputs\apk\debug\app-debug.apk
echo.
echo [3/3] Apertura cartella APK...
explorer app\build\outputs\apk\debug
echo.
echo ========================================
echo PROSSIMI PASSI:
echo 1. Copia app-debug.apk sul tuo Samsung S22
echo 2. Installa l'APK sul telefono
echo 3. Apri l'app e divertiti!
echo ========================================
echo.
pause
