# 📱 Мобильная разработка

Руководство по разработке мобильных приложений для проекта Яндекс Карт.

## 📋 Содержание

- [Обзор](#-обзор)
- [Android разработка](#-android-разработка)
- [iOS разработка](#-ios-разработка)
- [Kotlin Multiplatform](#-kotlin-multiplatform)
- [Архитектура](#-архитектура)
- [UI/UX](#-uiux)
- [Тестирование](#-тестирование)
- [Сборка и развертывание](#-сборка-и-развертывание)

## 🎯 Обзор

Проект поддерживает разработку для двух основных мобильных платформ:

- **Android** - нативное приложение на Kotlin с Jetpack Compose
- **iOS** - нативное приложение на Swift с SwiftUI
- **Общий код** - Kotlin Multiplatform для переиспользования логики

## 🤖 Android разработка

### Требования

- **Android Studio** Arctic Fox или новее
- **Kotlin** 1.8+
- **Android SDK** API 21+ (Android 5.0)
- **Java** 11+

### Структура проекта

```
composeApp/
├── src/main/kotlin/
│   ├── ui/           # UI компоненты
│   ├── viewmodel/    # ViewModels
│   ├── repository/   # Репозитории данных
│   ├── network/      # Сетевой слой
│   └── utils/        # Утилиты
├── src/main/res/
│   ├── layout/       # Layout файлы
│   ├── values/       # Ресурсы
│   └── drawable/     # Изображения
└── build.gradle.kts  # Конфигурация сборки
```

### Основные зависимости

```kotlin
dependencies {
    // Compose
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Maps
    implementation("com.yandex.android:maps.mobile:4.4.0-full")
}
```

### Пример экрана карты

```kotlin
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Карта
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    map.move(CameraPosition(
                        Point(55.7558, 37.6176), 10.0f, 0.0f, 0.0f
                    ))
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        
        // UI элементы поверх карты
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Button(onClick = { viewModel.addRandomMarker() }) {
                Text("Добавить маркер")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Button(onClick = { viewModel.clearAll() }) {
                Text("Очистить все")
            }
        }
        
        // Информационная панель
        if (uiState.objects.isNotEmpty()) {
            InfoPanel(
                objects = uiState.objects,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}
```

## 🍎 iOS разработка

### Требования

- **Xcode** 14+
- **iOS** 13.0+
- **Swift** 5.7+
- **macOS** для разработки

### Структура проекта

```
iosApp/
├── MapsYandexAPI/
│   ├── Views/        # SwiftUI представления
│   ├── ViewModels/   # ViewModels
│   ├── Models/       # Модели данных
│   ├── Services/     # Сервисы
│   └── Utils/        # Утилиты
├── Podfile           # CocoaPods зависимости
└── MapsYandexAPI.xcodeproj
```

### Основные зависимости

```ruby
# Podfile
target 'MapsYandexAPI' do
  use_frameworks!
  
  # Yandex Maps
  pod 'YandexMapsMobile', '4.4.0'
  
  # Дополнительные библиотеки
  pod 'Alamofire', '~> 5.8'
  pod 'SwiftyJSON', '~> 5.0'
end
```

### Пример экрана карты

```swift
import SwiftUI
import YandexMapsMobile

struct MapScreen: View {
    @StateObject private var viewModel = MapViewModel()
    
    var body: some View {
        ZStack {
            // Карта
            MapViewRepresentable(
                center: viewModel.center,
                zoom: viewModel.zoom,
                onMapTap: viewModel.handleMapTap
            )
            
            // UI элементы
            VStack {
                Spacer()
                
                HStack {
                    Button("Добавить маркер") {
                        viewModel.addRandomMarker()
                    }
                    .buttonStyle(.borderedProminent)
                    
                    Button("Очистить все") {
                        viewModel.clearAll()
                    }
                    .buttonStyle(.bordered)
                }
                .padding()
            }
        }
        .onAppear {
            viewModel.initializeMap()
        }
    }
}

struct MapViewRepresentable: UIViewRepresentable {
    let center: CLLocationCoordinate2D
    let zoom: Float
    let onMapTap: (CLLocationCoordinate2D) -> Void
    
    func makeUIView(context: Context) -> YMKMapView {
        let mapView = YMKMapView()
        mapView.mapWindow.map.move(
            to: YMKCameraPosition(
                target: YMKPoint(latitude: center.latitude, longitude: center.longitude),
                zoom: zoom,
                azimuth: 0,
                tilt: 0
            )
        )
        return mapView
    }
    
    func updateUIView(_ uiView: YMKMapView, context: Context) {
        // Обновление карты при изменении состояния
    }
}
```

## 🔄 Kotlin Multiplatform

### Общая архитектура

```
common/
├── src/commonMain/kotlin/
│   ├── models/       # Общие модели данных
│   ├── repository/   # Интерфейсы репозиториев
│   ├── usecase/      # Use cases
│   └── utils/        # Общие утилиты
├── src/androidMain/kotlin/   # Android-специфичные реализации
└── src/iosMain/kotlin/       # iOS-специфичные реализации
```

### Пример общего кода

```kotlin
// common/src/commonMain/kotlin/models/Coordinates.kt
data class Coordinates(
    val latitude: Double,
    val longitude: Double
) {
    init {
        require(latitude in -90.0..90.0) { "Широта должна быть в диапазоне -90..90" }
        require(longitude in -180.0..180.0) { "Долгота должна быть в диапазоне -180..180" }
    }
}

// common/src/commonMain/kotlin/repository/MapRepository.kt
interface MapRepository {
    suspend fun addMarker(coords: Coordinates, options: MarkerOptions? = null): Marker
    suspend fun removeMarker(marker: Marker)
    suspend fun clearAll()
    suspend fun getObjects(): List<MapObject>
}

// common/src/commonMain/kotlin/usecase/AddMarkerUseCase.kt
class AddMarkerUseCase(
    private val repository: MapRepository
) {
    suspend operator fun invoke(coords: Coordinates, options: MarkerOptions? = null): Marker {
        return repository.addMarker(coords, options)
    }
}
```

### Платформо-специфичные реализации

```kotlin
// Android реализация
// android/src/main/kotlin/repository/AndroidMapRepository.kt
class AndroidMapRepository : MapRepository {
    private val mapView: MapView
    
    override suspend fun addMarker(coords: Coordinates, options: MarkerOptions?): Marker {
        return withContext(Dispatchers.Main) {
            val point = Point(coords.latitude, coords.longitude)
            val placemark = mapView.map.mapObjects.addPlacemark(point)
            
            // Настройка маркера
            options?.let { opts ->
                placemark.setText(opts.title ?: "")
                placemark.setIcon(ImageProvider.fromResource(context, opts.iconRes))
            }
            
            AndroidMarker(placemark)
        }
    }
}

// iOS реализация
// ios/src/main/kotlin/repository/IosMapRepository.kt
class IosMapRepository : MapRepository {
    private val mapView: YMKMapView
    
    override suspend fun addMarker(coords: Coordinates, options: MarkerOptions?): Marker {
        return withContext(Dispatchers.Main) {
            val point = YMKPoint(latitude: coords.latitude, longitude: coords.longitude)
            val placemark = mapView.mapWindow.map.mapObjects.addPlacemark(point)
            
            // Настройка маркера
            options?.let { opts ->
                placemark.setText(opts.title ?: "")
                if (opts.icon != null) {
                    placemark.setIcon(UIImage(named: opts.icon))
                }
            }
            
            IosMarker(placemark)
        }
    }
}
```

## 🏗️ Архитектура

### MVVM паттерн

```kotlin
// ViewModel
class MapViewModel(
    private val addMarkerUseCase: AddMarkerUseCase,
    private val clearAllUseCase: ClearAllUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()
    
    fun addRandomMarker() {
        viewModelScope.launch {
            try {
                val coords = generateRandomCoordinates()
                val marker = addMarkerUseCase(coords)
                _uiState.update { it.copy(
                    objects = it.objects + marker,
                    isLoading = false
                ) }
            } catch (error: Exception) {
                _uiState.update { it.copy(
                    error = error.message,
                    isLoading = false
                ) }
            }
        }
    }
    
    fun clearAll() {
        viewModelScope.launch {
            try {
                clearAllUseCase()
                _uiState.update { it.copy(objects = emptyList()) }
            } catch (error: Exception) {
                _uiState.update { it.copy(error = error.message) }
            }
        }
    }
}

// UI State
data class MapUiState(
    val objects: List<MapObject> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
```

### Dependency Injection

```kotlin
// Hilt модуль
@Module
@InstallIn(SingletonComponent::class)
object MapModule {
    
    @Provides
    @Singleton
    fun provideMapRepository(): MapRepository {
        return AndroidMapRepository()
    }
    
    @Provides
    @Singleton
    fun provideAddMarkerUseCase(repository: MapRepository): AddMarkerUseCase {
        return AddMarkerUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideClearAllUseCase(repository: MapRepository): ClearAllUseCase {
        return ClearAllUseCase(repository)
    }
}
```

## 🎨 UI/UX

### Material Design 3 (Android)

```kotlin
@Composable
fun MapButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp
        )
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }
}
```

### SwiftUI (iOS)

```swift
struct MapButton: View {
    let title: String
    let action: () -> Void
    let icon: String?
    
    var body: some View {
        Button(action: action) {
            HStack {
                if let icon = icon {
                    Image(systemName: icon)
                        .font(.system(size: 16, weight: .medium))
                }
                Text(title)
                    .font(.system(size: 16, weight: .medium))
            }
            .foregroundColor(.white)
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .background(Color.blue)
            .cornerRadius(8)
            .shadow(radius: 4)
        }
    }
}
```

### Адаптивный дизайн

```kotlin
@Composable
fun ResponsiveLayout(
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    when {
        screenWidth < 600.dp -> {
            // Мобильная версия
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                content()
            }
        }
        screenWidth < 840.dp -> {
            // Планшетная версия
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                content()
            }
        }
        else -> {
            // Десктопная версия
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                content()
            }
        }
    }
}
```

## 🧪 Тестирование

### Unit тесты

```kotlin
class MapViewModelTest {
    
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    
    private lateinit var viewModel: MapViewModel
    private lateinit var mockAddMarkerUseCase: AddMarkerUseCase
    private lateinit var mockClearAllUseCase: ClearAllUseCase
    
    @Before
    fun setup() {
        mockAddMarkerUseCase = mockk()
        mockClearAllUseCase = mockk()
        viewModel = MapViewModel(mockAddMarkerUseCase, mockClearAllUseCase)
    }
    
    @Test
    fun `addRandomMarker should add marker to state`() = runTest {
        // Given
        val coords = Coordinates(55.7558, 37.6176)
        val marker = Marker("1", coords)
        coEvery { mockAddMarkerUseCase(any()) } returns marker
        
        // When
        viewModel.addRandomMarker()
        
        // Then
        val state = viewModel.uiState.value
        assertThat(state.objects).contains(marker)
        assertThat(state.isLoading).isFalse()
    }
}
```

### UI тесты

```kotlin
@RunWith(AndroidJUnit4::class)
class MapScreenTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun addMarkerButton_shouldAddMarker() {
        // Given
        composeTestRule.setContent {
            MapScreen()
        }
        
        // When
        composeTestRule.onNodeWithText("Добавить маркер").performClick()
        
        // Then
        composeTestRule.onNodeWithText("Маркер добавлен").assertIsDisplayed()
    }
}
```

## 🚀 Сборка и развертывание

### Android

```bash
# Debug сборка
./gradlew assembleDebug

# Release сборка
./gradlew assembleRelease

# Установка на устройство
./gradlew installDebug
```

### iOS

```bash
# Установка зависимостей
cd iosApp
pod install
cd ..

# Сборка в Xcode
xcodebuild -workspace iosApp/MapsYandexAPI.xcworkspace \
           -scheme MapsYandexAPI \
           -configuration Release \
           -destination generic/platform=iOS \
           build
```

### CI/CD

```yaml
# .github/workflows/mobile.yml
name: Mobile CI/CD

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  android:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '11'
          distribution: 'temurin'
      - run: ./gradlew assembleDebug
      - run: ./gradlew test

  ios:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v3
      - run: |
          cd iosApp
          pod install
          cd ..
      - run: |
          xcodebuild -workspace iosApp/MapsYandexAPI.xcworkspace \
                     -scheme MapsYandexAPI \
                     -configuration Debug \
                     -destination generic/platform=iOS \
                     build
```

## 📱 Особенности платформ

### Android

- **Jetpack Compose** - современный UI фреймворк
- **Material Design 3** - система дизайна
- **Kotlin Coroutines** - асинхронность
- **Hilt** - dependency injection
- **Navigation Compose** - навигация

### iOS

- **SwiftUI** - декларативный UI фреймворк
- **Combine** - реактивное программирование
- **Swift Concurrency** - async/await
- **Swift Package Manager** - управление зависимостями

## 🔧 Отладка

### Android

```kotlin
// Логирование
Log.d("MapKit", "Добавление маркера: $coords")

// Отладка в Chrome DevTools
if (BuildConfig.DEBUG) {
    Stetho.initializeWithDefaults(this)
}
```

### iOS

```swift
// Логирование
print("Добавление маркера: \(coords)")

// Отладка в Safari Web Inspector
#if DEBUG
    // Включить отладку веб-представлений
#endif
```

---

**Успешной разработки!** 🎉

Для получения дополнительной помощи обратитесь к [CONTRIBUTING.md](./CONTRIBUTING.md) или создайте Issue.
