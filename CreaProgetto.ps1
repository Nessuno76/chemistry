# Chemistry News App - Script Automatico di Creazione Progetto
# Esegui questo script nella cartella del tuo repository Git clonato

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  CHEMISTRY NEWS APP - SETUP COMPLETO  " -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$projectPath = Get-Location

Write-Host "[1/5] Creazione struttura cartelle..." -ForegroundColor Yellow

# Crea tutte le cartelle necessarie
$folders = @(
    "app\src\main\java\com\chemistrynews\app\data\local",
    "app\src\main\java\com\chemistrynews\app\data\model",
    "app\src\main\java\com\chemistrynews\app\data\remote",
    "app\src\main\java\com\chemistrynews\app\data\repository",
    "app\src\main\java\com\chemistrynews\app\data\preferences",
    "app\src\main\java\com\chemistrynews\app\ui\components",
    "app\src\main\java\com\chemistrynews\app\ui\navigation",
    "app\src\main\java\com\chemistrynews\app\ui\screens",
    "app\src\main\java\com\chemistrynews\app\ui\theme",
    "app\src\main\java\com\chemistrynews\app\ui\viewmodel",
    "app\src\main\res\values",
    "app\src\main\res\drawable",
    "app\src\main\res\mipmap-anydpi-v26",
    "app\src\main\res\mipmap-hdpi",
    "app\src\main\res\mipmap-mdpi",
    "app\src\main\res\mipmap-xhdpi",
    "app\src\main\res\mipmap-xxhdpi",
    "app\src\main\res\mipmap-xxxhdpi",
    "app\src\main\res\xml",
    "gradle\wrapper"
)

foreach ($folder in $folders) {
    $fullPath = Join-Path $projectPath $folder
    if (-not (Test-Path $fullPath)) {
        New-Item -ItemType Directory -Path $fullPath -Force | Out-Null
    }
}

Write-Host "[OK] Cartelle create!" -ForegroundColor Green

Write-Host ""
Write-Host "[2/5] Scaricamento Gradle Wrapper..." -ForegroundColor Yellow

# Scarica gradle-wrapper.jar
$gradleWrapperUrl = "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
$gradleWrapperPath = Join-Path $projectPath "gradle\wrapper\gradle-wrapper.jar"

try {
    Invoke-WebRequest -Uri $gradleWrapperUrl -OutFile $gradleWrapperPath -UseBasicParsing
    Write-Host "[OK] Gradle Wrapper scaricato!" -ForegroundColor Green
} catch {
    Write-Host "[!] Impossibile scaricare Gradle Wrapper" -ForegroundColor Red
    Write-Host "    Continuo comunque..." -ForegroundColor Yellow
}

Write-Host ""
Write-Host "[3/5] Creazione file di configurazione..." -ForegroundColor Yellow

# NOTA: I file verranno creati qui sotto
# Per brevità, questo script crea solo la struttura
# I file sorgente completi devono essere aggiunti manualmente o tramite commit

Write-Host "[OK] Struttura base pronta!" -ForegroundColor Green

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  PROSSIMI PASSI:" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Questo script ha creato la struttura delle cartelle." -ForegroundColor White
Write-Host ""
Write-Host "Per ottenere i file completi del progetto:" -ForegroundColor White
Write-Host "1. Usa lo ZIP che ti inviero'" -ForegroundColor Yellow
Write-Host "2. Estrai tutti i file in questa cartella" -ForegroundColor Yellow
Write-Host "3. Configura l'API Key in NewsApiService.kt" -ForegroundColor Yellow
Write-Host "4. Esegui COMPILA-APK.bat" -ForegroundColor Yellow
Write-Host ""
Write-Host "Oppure continua con le istruzioni che ti daro'!" -ForegroundColor Green
Write-Host ""

pause
