package ru.yandex.maps.workshop.common.additional.context

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import ru.yandex.maps.workshop.common.additional.settings.ObservableSettingsFactory

actual class PlatformContext(val context: Context)

actual fun PlatformContext.observableSettingsFactory(): ObservableSettingsFactory = object : ObservableSettingsFactory {
    override fun create(name: String?): ObservableSettings {
        return SharedPreferencesSettings.Factory(context).create(name)
    }
}
