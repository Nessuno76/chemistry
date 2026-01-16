# Chemistry News App - Script di Setup Automatico
# Esegui questo nella cartella del repository clonato

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  CHEMISTRY NEWS APP - SETUP PROGETTO  " -ForegroundColor Cyan  
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Creazione progetto in corso..." -ForegroundColor Yellow
Write-Host ""

$ErrorActionPreference = "Stop"

# Funzione per creare file
function Create-ProjectFile {
    param($Path, $Content)
    $dir = Split-Path $Path
    if ($dir -and !(Test-Path $dir)) {
        New-Item -ItemType Directory -Path $dir -Force | Out-Null
    }
    Set-Content -Path $Path -Value $Content -Encoding UTF8
    Write-Host "  [OK] $Path" -ForegroundColor Green
}

Write-Host "[1/10] File Gradle principali..." -ForegroundColor Yellow
Create-ProjectFile "build.gradle.kts" @"
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}
"@

Create-ProjectFile "settings.gradle.kts" @"
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ChemistryNewsApp"
include(":app")
"@


Write-Host ""
Write-Host "[OK] Setup completato!" -ForegroundColor Green
Write-Host ""
Write-Host "Prossimi passi:" -ForegroundColor Cyan
Write-Host "1. git add ." -ForegroundColor Yellow
Write-Host "2. git commit -m 'Initial commit'" -ForegroundColor Yellow
Write-Host "3. git push origin main" -ForegroundColor Yellow
Write-Host ""
