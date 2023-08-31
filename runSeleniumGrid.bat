@echo off

set SELENIUM_VERSION=4.0
set SELENIUM_SUB_VERSION=alpha-2
set GRID_PORT=4444
set NODE_PORT=5555

echo Запуск Selenium Hub...
start java -jar src/test/java/utils/seleniumGrid/selenium-server-standalone-%SELENIUM_VERSION%-%SELENIUM_SUB_VERSION%.jar -role hub -port %GRID_PORT%
echo Запуск Selenium Node...
start java -Dwebdriver.chrome.driver="src\test\java\utils\chromedriver\chromedriver.exe" -jar src/test/java/utils/seleniumGrid/selenium-server-standalone-%SELENIUM_VERSION%-%SELENIUM_SUB_VERSION%.jar -role node -hub http://localhost:%GRID_PORT%/grid/register -port %NODE_PORT%