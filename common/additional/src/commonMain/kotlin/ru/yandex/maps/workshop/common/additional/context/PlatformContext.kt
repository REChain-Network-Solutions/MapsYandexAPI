package ru.yandex.maps.workshop.common.additional.context

import ru.yandex.maps.workshop.common.additional.settings.ObservableSettingsFactory

expect class PlatformContext

expect fun PlatformContext.observableSettingsFactory(): ObservableSettingsFactory
