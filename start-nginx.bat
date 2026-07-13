@echo off
set NGINX_DIR=C:\Users\baishanxing\AppData\Local\Microsoft\WinGet\Packages\nginxinc.nginx_Microsoft.Winget.Source_8wekyb3d8bbwe\nginx-1.31.0
echo Stopping old nginx...
taskkill /f /im nginx.exe >nul 2>&1
timeout /t 2 >nul
echo Starting nginx from: %NGINX_DIR%
cd /d "%NGINX_DIR%"
start /b nginx -c conf\health-ai-nginx.conf
echo Nginx started. Open http://localhost
