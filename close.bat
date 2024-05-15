@echo off
echo "Stopping API..."
taskkill /f /im java.exe

echo "Stopping Web App..."
taskkill /f /im node.exe
