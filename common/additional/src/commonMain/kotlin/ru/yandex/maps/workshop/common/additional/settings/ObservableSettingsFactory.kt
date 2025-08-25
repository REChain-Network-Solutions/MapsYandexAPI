package ru.yandex.maps.workshop.common.additional.settings

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings

interface ObservableSettingsFactory: Settings.Factory {
    override fun create(name: String?): ObservableSettings
}
