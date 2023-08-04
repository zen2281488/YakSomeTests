
SELENIUM_VERSION="4.0"
SELENIUM_SUB_VERSION="alpha-2"
GRID_PORT="4444"
NODE_PORT="5555"


echo "Запуск Selenium Hub..."
java -jar "src/test/java/utils/seleniumGrid/selenium-server-standalone-${SELENIUM_VERSION}-${SELENIUM_SUB_VERSION}.jar" -role hub -port ${GRID_PORT} &
sleep 5

echo "Запуск Selenium Node..."
java -Dwebdriver.chrome.driver="src/test/java/utils/chromedriver/chromedriver" -jar "src/test/java/utils/seleniumGrid/selenium-server-standalone-${SELENIUM_VERSION}-${SELENIUM_SUB_VERSION}.jar" -role node -hub "http://localhost:${GRID_PORT}/grid/register" -port ${NODE_PORT} &