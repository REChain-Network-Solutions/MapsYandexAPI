@echo off
echo Запуск локального сервера для Яндекс Карт...
echo.
echo Откройте браузер и перейдите по адресу: http://localhost:8000
echo.
echo Для остановки сервера нажмите Ctrl+C
echo.
python -m http.server 8000
pause
