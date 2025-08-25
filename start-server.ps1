Write-Host "Запуск локального сервера для Яндекс Карт..." -ForegroundColor Green
Write-Host ""
Write-Host "Откройте браузер и перейдите по адресу: http://localhost:8000" -ForegroundColor Yellow
Write-Host ""
Write-Host "Для остановки сервера нажмите Ctrl+C" -ForegroundColor Red
Write-Host ""

try {
    # Пробуем Python 3
    python -m http.server 8000
} catch {
    try {
        # Пробуем Python 2
        python -m SimpleHTTPServer 8000
    } catch {
        try {
            # Пробуем Node.js
            npx http-server -p 8000
        } catch {
            Write-Host "Ошибка: Не найден Python или Node.js" -ForegroundColor Red
            Write-Host "Установите Python 3 или Node.js для запуска сервера" -ForegroundColor Red
            Read-Host "Нажмите Enter для выхода"
        }
    }
}
