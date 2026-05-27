@echo off
cd /d "%~dp0"
echo Running automation tests...
call mvnw.cmd clean test
pause
