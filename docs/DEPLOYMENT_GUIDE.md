# 🚀 Руководство по развертыванию Yandex Maps Workshop

<div align="center">

![Deployment](https://img.shields.io/badge/Развертывание-00C853?style=for-the-badge&logo=rocket&logoColor=white)
![Platform](https://img.shields.io/badge/Платформа-Android-green?style=for-the-badge)
![Version](https://img.shields.io/badge/Версия-1.0.0-blue?style=for-badge)

**Пошаговое руководство по развертыванию проекта на различных платформах**

[📋 Предварительные требования](#-предварительные-требования) • [🔑 Настройка API](#-настройка-api) • [📱 Android](#-android) • [🍎 iOS](#-ios) • [🌐 Web](#-web)

</div>

---

## 📋 Предварительные требования

### 🛠 **Необходимые инструменты**

- **Android Studio** (последняя версия)
- **JDK 17+** (Java Development Kit)
- **Android SDK** (API 26+)
- **Git** для управления версиями
- **Gradle** (встроен в Android Studio)

### 💻 **Системные требования**

- **ОС**: Windows 10+, macOS 10.15+, Ubuntu 18.04+
- **RAM**: минимум 8 GB (рекомендуется 16 GB)
- **Хранилище**: минимум 10 GB свободного места
- **Интернет**: стабильное соединение для загрузки зависимостей

---

## 🔑 Настройка API

### 1️⃣ **Получение API ключей**

#### **Yandex Cloud:**
1. Зарегистрируйтесь на [cloud.yandex.ru](https://cloud.yandex.ru)
2. Создайте новый проект или выберите существующий
3. Получите `folderId` из настроек проекта

#### **YaGPT API:**
1. В Yandex Cloud перейдите в раздел "ИИ-сервисы"
2. Активируйте YaGPT
3. Создайте API ключ в разделе "Сервисные аккаунты"

#### **Yandex MapKit:**
1. Перейдите на [yandex.ru/dev/maps](https://yandex.ru/dev/maps)
2. Зарегистрируйтесь как разработчик
3. Получите MapKit токен

### 2️⃣ **Настройка конфигурации**

#### **Обновите `gradle.properties`:**
```properties
# Yandex Cloud
folderId=your_folder_id_here

# YaGPT API
gptToken=your_yagpt_token_here

# Yandex MapKit
mapkitToken=your_mapkit_token_here
```

#### **Создайте `local.properties`:**
```properties
# Android SDK путь (Windows)
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk

# Android SDK путь (macOS/Linux)
sdk.dir=/Users/YourUsername/Library/Android/sdk
```

---

## 📱 Android

### 🏗️ **Сборка проекта**

#### **1. Клонирование репозитория:**
```bash
git clone https://github.com/kramlex/MapsWorkShop.git
cd MapsWorkShop
```

#### **2. Синхронизация Gradle:**
```bash
# Windows
.\gradlew.bat --refresh-dependencies

# macOS/Linux
./gradlew --refresh-dependencies
```

#### **3. Сборка Debug версии:**
```bash
# Windows
.\gradlew.bat :composeApp:assembleDebug

# macOS/Linux
./gradlew :composeApp:assembleDebug
```

#### **4. Сборка Release версии:**
```bash
# Windows
.\gradlew.bat :composeApp:assembleRelease

# macOS/Linux
./gradlew :composeApp:assembleRelease
```

### 📲 **Установка на устройство**

#### **1. Подключение устройства:**
- Включите режим разработчика на Android устройстве
- Активируйте USB отладку
- Подключите устройство к компьютеру

#### **2. Установка APK:**
```bash
# Windows
.\gradlew.bat :composeApp:installDebug

# macOS/Linux
./gradlew :composeApp:installDebug
```

#### **3. Запуск приложения:**
```bash
# Windows
.\gradlew.bat :composeApp:installDebug

# macOS/Linux
./gradlew :composeApp:installDebug
```

### 🔧 **Отладка и тестирование**

#### **Просмотр логов:**
```bash
adb logcat | grep "MapsWorkShop"
```

#### **Очистка проекта:**
```bash
# Windows
.\gradlew.bat clean

# macOS/Linux
./gradlew clean
```

---

## 🍎 iOS

### ⚠️ **Примечание**
iOS поддержка находится в разработке. Текущая версия включает базовую структуру для iOS.

### 🏗️ **Подготовка к iOS разработке**

#### **1. Требования:**
- **macOS** (обязательно для iOS разработки)
- **Xcode** (последняя версия)
- **CocoaPods** или **Swift Package Manager**

#### **2. Настройка iOS модуля:**
```bash
# Синхронизация iOS зависимостей
./gradlew :composeApp:iosSimulatorArm64Test
```

#### **3. Открытие в Xcode:**
```bash
# Генерация Xcode проекта
./gradlew :composeApp:generateXcodeProject
```

---

## 🌐 Web

### ⚠️ **Примечание**
Web версия планируется в будущих релизах.

### 🔮 **Планируемая функциональность:**
- Адаптивный веб-интерфейс
- Поддержка всех основных функций
- Оптимизация для мобильных браузеров

---

## 🚀 Production развертывание

### 📦 **Подготовка к релизу**

#### **1. Обновление версии:**
```kotlin
// В build.gradle.kts
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0.0"
    }
}
```

#### **2. Подписание APK:**
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("keystore/release.keystore")
            storePassword = "your_store_password"
            keyAlias = "your_key_alias"
            keyPassword = "your_key_password"
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

#### **3. Сборка релизной версии:**
```bash
# Windows
.\gradlew.bat :composeApp:bundleRelease

# macOS/Linux
./gradlew :composeApp:bundleRelease
```

### 📱 **Публикация в Google Play**

#### **1. Создание аккаунта разработчика:**
- Зарегистрируйтесь на [play.google.com/console](https://play.google.com/console)
- Оплатите регистрационный взнос ($25)

#### **2. Подготовка материалов:**
- **Иконка приложения** (512x512 px)
- **Скриншоты** (минимум 2)
- **Описание** на русском и английском языках
- **Политика конфиденциальности**

#### **3. Загрузка APK/AAB:**
- Войдите в Google Play Console
- Создайте новое приложение
- Загрузите собранный AAB файл
- Заполните всю необходимую информацию

---

## 🔧 CI/CD интеграция

### 🚀 **GitHub Actions**

#### **Создайте `.github/workflows/android.yml`:**
```yaml
name: Android CI/CD

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    
    - name: Build with Gradle
      run: ./gradlew build
    
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: composeApp/build/outputs/apk/debug/composeApp-debug.apk
```

### 🔄 **Автоматизация сборки**

#### **1. Автоматическая сборка при push:**
- Каждый push в main ветку автоматически собирает проект
- Результаты доступны в GitHub Actions

#### **2. Автоматическое тестирование:**
- Запуск unit тестов при каждом PR
- Проверка качества кода

---

## 🐛 Решение проблем развертывания

### ❌ **Частые ошибки и решения**

#### **Ошибка: "SDK location not found"**
**Решение:**
```bash
# Создайте local.properties в корне проекта
echo "sdk.dir=$ANDROID_HOME" > local.properties
```

#### **Ошибка: "Gradle sync failed"**
**Решение:**
```bash
# Очистите кэш Gradle
./gradlew clean
./gradlew --refresh-dependencies
```

#### **Ошибка: "Build failed"**
**Решение:**
1. Проверьте версию JDK (должна быть 17+)
2. Обновите Android Studio
3. Синхронизируйте Gradle файлы

#### **Ошибка: "API key invalid"**
**Решение:**
1. Проверьте правильность API ключей в `gradle.properties`
2. Убедитесь, что ключи активны в Yandex Cloud
3. Проверьте права доступа для API ключей

### 📊 **Мониторинг развертывания**

#### **Логи сборки:**
```bash
# Подробные логи сборки
./gradlew :composeApp:assembleDebug --info

# Логи с ошибками
./gradlew :composeApp:assembleDebug --stacktrace
```

#### **Проверка зависимостей:**
```bash
# Список всех зависимостей
./gradlew :composeApp:dependencies
```

---

## 🔒 Безопасность

### 🛡️ **Защита API ключей**

#### **1. Использование переменных окружения:**
```bash
# В .bashrc или .zshrc
export YANDEX_FOLDER_ID="your_folder_id"
export YANDEX_GPT_TOKEN="your_gpt_token"
export YANDEX_MAPKIT_TOKEN="your_mapkit_token"
```

#### **2. Шифрование ключей:**
```kotlin
// В build.gradle.kts
buildkonfig {
    val folderId: String = providers.environmentVariable("YANDEX_FOLDER_ID").get()
    val gptToken: String = providers.environmentVariable("YANDEX_GPT_TOKEN").get()
    val mapkitToken: String = providers.environmentVariable("YANDEX_MAPKIT_TOKEN").get()
    
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "folderId", folderId)
        buildConfigField(FieldSpec.Type.STRING, "gptToken", gptToken)
        buildConfigField(FieldSpec.Type.STRING, "mapkitToken", mapkitToken)
    }
}
```

### 🔐 **Проверка безопасности**

#### **1. Сканирование зависимостей:**
```bash
# Проверка уязвимостей
./gradlew dependencyCheckAnalyze
```

#### **2. Проверка подписи:**
```bash
# Проверка подписи APK
jarsigner -verify -verbose -certs app-release.apk
```

---

## 📈 Масштабирование

### 🚀 **Подготовка к росту**

#### **1. Оптимизация сборки:**
```kotlin
// В gradle.properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.configureondemand=true
```

#### **2. Кэширование:**
```kotlin
// В build.gradle.kts
android {
    buildCache {
        local {
            directory = File(rootDir, "build-cache")
            removeUnusedEntriesAfterDays = 30
        }
    }
}
```

#### **3. Модульная архитектура:**
- Разделение на отдельные модули
- Независимая сборка модулей
- Переиспользование кода

---

## 🔮 Будущие планы

### 🎯 **Краткосрочные цели (1-2 месяца):**
- [ ] **Автоматизация CI/CD** для всех платформ
- [ ] **Docker контейнеризация** для сборки
- [ ] **Автоматическое тестирование** на реальных устройствах

### 🚀 **Среднесрочные цели (3-6 месяцев):**
- [ ] **Multi-platform сборка** (Android + iOS + Web)
- [ ] **Cloud сборка** в Yandex Cloud
- [ ] **Автоматическое обновление** зависимостей

### 🌟 **Долгосрочные цели (6+ месяцев):**
- [ ] **Kubernetes развертывание** для web версии
- [ ] **Глобальное CDN** для статических ресурсов
- [ ] **A/B тестирование** новых функций

---

## 📞 Поддержка

### 💬 **Получение помощи**

#### **Документация:**
- [README.md](../README.md) - основная документация
- [TECHNICAL_DOCUMENTATION.md](TECHNICAL_DOCUMENTATION.md) - технические детали
- [USER_GUIDE.md](USER_GUIDE.md) - руководство пользователя

#### **Сообщество:**
- **GitHub Issues**: [Создайте issue](https://github.com/kramlex/MapsWorkShop/issues)
- **Discord**: Присоединитесь к серверу разработчиков
- **Telegram**: Группа для обсуждений

#### **Прямая поддержка:**
- **Email**: support@mapsworkshop.dev
- **Slack**: Рабочее пространство для команды

---

## 🎉 Заключение

**Руководство по развертыванию** поможет вам:

✅ **Быстро настроить** проект для разработки  
✅ **Собрать и установить** приложение на устройство  
✅ **Настроить CI/CD** для автоматизации  
✅ **Безопасно развернуть** в production  
✅ **Масштабировать** проект по мере роста  

### 🚀 **Следующие шаги:**

1. **Настройте окружение** согласно предварительным требованиям
2. **Получите API ключи** от Yandex Cloud
3. **Соберите проект** для Android
4. **Протестируйте** на реальном устройстве
5. **Настройте CI/CD** для автоматизации

---

<div align="center">

**⭐ Если руководство было полезным, поставьте звездочку проекту! ⭐**

**🚀 Успешного развертывания! 🚀**

**💬 Делитесь опытом и предложениями! 💬**

</div>

---

*Руководство обновлено: Декабрь 2024*
