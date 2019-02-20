FROM jenkins/jenkins:lts
# if we want to install via apt
USER root

RUN apt-get update && apt-get install -y gconf-service libasound2 libatk1.0-0 libcairo2 libcups2 libfontconfig1 libgdk-pixbuf2.0-0 libgtk-3-0 libnspr4 libpango-1.0-0 libxss1 fonts-liberation libappindicator1 libnss3 lsb-release xdg-utils

RUN apt-get update && apt-get install -y git \
    && apt-get install -y xvfb


# install maven
RUN apt-get update && apt-get install -y maven

# configure webdriver and selenium server

# Install Dependencies
RUN apt-get install -y apt-transport-https
RUN apt-get update -y
RUN apt-get install -y unzip openjdk-8-jre-headless xvfb libxi6 libgconf-2-4

# Set Google Chrome, ChromeDriver and Selenium Versions
ENV CHROME_DRIVER_VERSION 3.4.0
ENV SELENIUM_STANDALONE_VERSION 3.4.0
ENV SELENIUM_SUBDIR 3.4

# Install Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update && apt-get install -y google-chrome-stable

# chromeDriver v2.35
RUN wget -q "https://chromedriver.storage.googleapis.com/2.35/chromedriver_linux64.zip" -O /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /usr/bin/ \
    && rm /tmp/chromedriver.zip

# Install Selenium
RUN wget -N http://selenium-release.storage.googleapis.com/$SELENIUM_SUBDIR/selenium-server-standalone-$SELENIUM_STANDALONE_VERSION.jar -P ~/
RUN mv -f ~/selenium-server-standalone-$SELENIUM_STANDALONE_VERSION.jar /usr/local/bin/selenium-server-standalone.jar
RUN chown root:root /usr/local/bin/selenium-server-standalone.jar
RUN chmod 0755 /usr/local/bin/selenium-server-standalone.jar

#=========
# Firefox
#=========
RUN apt-get update && apt-get install -y iceweasel

# GeckoDriver v0.19.1
RUN wget -q "https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-linux64.tar.gz" -O /tmp/geckodriver.tgz \
    && tar zxf /tmp/geckodriver.tgz -C /usr/bin/ \
    && rm /tmp/geckodriver.tgz

# xvfb - X server display
ADD /xvfb-chromium.sh /usr/bin/xvfb-chromium.sh
RUN ln -sf /usr/bin/xvfb-chromium /usr/bin/google-chrome \
    && chmod 777 /usr/bin/xvfb-chromium.sh

# create symlinks to chromedriver and geckodriver (to the PATH)
RUN ln -sf /usr/bin/geckodriver /usr/bin/chromium-browser \
    && chmod 777 /usr/bin/geckodriver \
    && chmod 777 /usr/bin/chromium-browser

# drop back to the regular jenkins user - good practice
USER jenkins

# Installing the plugins we need using the in-built install-plugins.sh script
RUN /usr/local/bin/install-plugins.sh git blueocean

 # Setting up environment variables for Jenkins admin user
ENV JENKINS_USER admin
ENV JENKINS_PASS admin

# Skip the initial setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Start-up scripts to set number of executors and creating the admin user
COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy
#COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/
