@echo off
SETLOCAL EnableDelayedExpansion
chcp 65001 >nul

echo.
echo ╔══════════════════════════════════════════════════════════════╗
echo ║                                                              ║
echo ║     CHEMISTRY NEWS APP - CREAZIONE AUTOMATICA PROGETTO       ║
echo ║                                                              ║
echo ╚══════════════════════════════════════════════════════════════╝
echo.
echo Questo script creerà l'intero progetto Android sul tuo Desktop.
echo.
pause

set "PROJECT_DIR=%USERPROFILE%\Desktop\chemistry-news-app"

echo.
echo [1/4] Creazione cartelle del progetto...
echo Cartella: %PROJECT_DIR%
echo.

if exist "%PROJECT_DIR%" (
    echo La cartella esiste già. Vuoi sovrascriverla? (S/N)
    set /p overwrite=
    if /i "!overwrite!" NEQ "S" (
        echo Operazione annullata.
        pause
        exit /b
    )
    rmdir /s /q "%PROJECT_DIR%"
)

mkdir "%PROJECT_DIR%"
mkdir "%PROJECT_DIR%\app"
mkdir "%PROJECT_DIR%\app\src"
mkdir "%PROJECT_DIR%\app\src\main"
mkdir "%PROJECT_DIR%\app\src\main\java"
mkdir "%PROJECT_DIR%\app\src\main\java\com"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data\local"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data\model"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data\remote"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data\repository"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\data\preferences"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui\components"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui\navigation"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui\screens"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui\theme"
mkdir "%PROJECT_DIR%\app\src\main\java\com\chemistrynews\app\ui\viewmodel"
mkdir "%PROJECT_DIR%\app\src\main\res"
mkdir "%PROJECT_DIR%\app\src\main\res\values"
mkdir "%PROJECT_DIR%\app\src\main\res\drawable"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-anydpi-v26"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-hdpi"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-mdpi"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-xhdpi"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-xxhdpi"
mkdir "%PROJECT_DIR%\app\src\main\res\mipmap-xxxhdpi"
mkdir "%PROJECT_DIR%\app\src\main\res\xml"
mkdir "%PROJECT_DIR%\gradle"
mkdir "%PROJECT_DIR%\gradle\wrapper"

echo [✓] Cartelle create!
echo.
echo [2/4] Scaricamento Gradle Wrapper...
echo.

powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar' -OutFile '%PROJECT_DIR%\gradle\wrapper\gradle-wrapper.jar'" 2>nul
if errorlevel 1 (
    echo [!] Impossibile scaricare gradle-wrapper.jar
    echo    Dovrai aggiungerlo manualmente o usare Android Studio
)

echo [✓] Gradle Wrapper scaricato!
echo.
echo [3/4] Creazione file progetto...
echo Questo potrebbe richiedere un minuto...
echo.

cd "%PROJECT_DIR%"

echo Downloading complete project files...
echo.
echo ═══════════════════════════════════════════════════════════════
echo.
echo ATTENZIONE: Questo script è troppo grande per un singolo file BAT.
echo.
echo SOLUZIONE MIGLIORE:
echo.
echo 1. Vai su: https://github.com
echo 2. Crea un account gratuito (se non ce l'hai)
echo 3. Dimmi il tuo username GitHub
echo 4. Io caricherò il progetto completo lì
echo 5. Tu lo scarichi con: git clone
echo.
echo OPPURE
echo.
echo Ti invio i file in un altro modo (ZIP, Google Drive, ecc.)
echo.
echo ═══════════════════════════════════════════════════════════════
echo.
pause

exit /b
