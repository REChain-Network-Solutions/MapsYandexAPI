package ru.yandex.maps.workshop.common.additional.context

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import ru.yandex.maps.workshop.common.additional.settings.ObservableSettingsFactory

actual class PlatformContext

actual fun PlatformContext.observableSettingsFactory(): ObservableSettingsFactory = object : ObservableSettingsFactory {
    override fun create(name: String?): ObservableSettings {
        return NSUserDefaultsSettings.Factory().create(name)
    }
}

